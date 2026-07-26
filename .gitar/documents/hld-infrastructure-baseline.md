# HLD Infrastructure Baseline

## Document control

* System: Customer Digital Platform
* Environment: Production
* HLD version: 1.0
* Owner: Cloud Infrastructure Team
* Last reviewed: YYYY-MM-DD

## Purpose

This document defines mandatory infrastructure architecture for the production environment. Terraform changes must conform unless the PR references an approved architecture exception.

## Account and regional boundaries

* Provision production resources only in AWS account `123456789012`.
* Deploy primary resources only in `ap-southeast-1`.
* Deploy DR replicas only in `ap-southeast-2`.
* Do not create resources in other accounts or regions without an approved exception.

## Network topology

* Use VPC `vpc-customer-prod` with CIDR `10.20.0.0/16`.
* Application private subnets: `10.20.11.0/24`, `10.20.12.0/24`, `10.20.13.0/24`.
* Data private subnets: `10.20.21.0/24`, `10.20.22.0/24`, `10.20.23.0/24`.
* Only approved public subnets may host internet-facing load balancers.
* Compute, databases, caches, and internal services must not have public IP addresses.
* Inbound HTTPS is permitted only through the approved public load balancer.
* Do not allow inbound SSH or RDP from the internet.
* Do not allow `0.0.0.0/0` ingress except TCP 443 on the approved internet-facing load balancer.

## Compute platform

* Run application workloads on EKS cluster `eks-customer-prod` across three Availability Zones.
* Use managed node groups with `m6i` instance family or an approved successor.
* Set cluster autoscaling to a minimum of 3 and maximum of 12 nodes.
* Pull container images only from approved ECR repositories under `customer-prod`.
* Do not deploy privileged containers, host networking, or host-path volumes without approval.

## Data services

* Use RDS PostgreSQL Multi-AZ instance `customer-prod-db` for transactional data.
* Encrypt database storage using KMS key alias `alias/customer-prod-data`.
* Retain automated backups for at least 35 days.
* Enable deletion protection for production databases.
* Permit TCP 5432 only from the application workload security group.
* Enable Redis encryption in transit and at rest.
* Do not expose database or cache endpoints publicly.

## Identity, secrets, and encryption

* Use IAM roles or workload identities; do not use long-lived IAM user access keys for workloads.
* Store secrets only in AWS Secrets Manager or encrypted SSM Parameter Store values.
* Encrypt supported data stores, EBS volumes, S3 buckets, backups, and snapshots with KMS.
* Enforce TLS 1.2 or higher for external endpoints.
* Do not place passwords, tokens, API keys, or connection strings in Terraform source, variables, or plan output.

## Logging and monitoring

* Enable CloudTrail and send logs to the central audit bucket.
* Enable VPC Flow Logs.
* Send EKS, application, database, and load-balancer logs to the central logging platform.
* Configure alarms for availability, error rates, database capacity, CPU saturation, and backup failures.
* Retain production audit logs for at least 365 days.

## Resilience and disaster recovery

* Distribute application nodes across at least three Availability Zones.
* Use Multi-AZ database deployment.
* Replicate critical backups to `ap-southeast-2`.
* Target RPO: 24 hours.
* Target RTO: 4 hours.

## Resource naming and tags

* Use `<system>-<environment>-<component>-<region>` naming.
* Every resource must include:
  * `System=customer-digital-platform`
  * `Environment=prod`
  * `Owner=cloud-infra`
  * `CostCenter=CC-12345`
  * `DataClassification=confidential`
  * `ManagedBy=terraform`

## Explicitly prohibited patterns

* Public IPs on compute, database, cache, or internal service resources.
* Unencrypted production data stores, backups, volumes, or object storage.
* Production infrastructure outside approved accounts or regions.
* Disabled backup, deletion protection, audit logging, or monitoring.
* Broad inbound network access outside the approved load-balancer rule.

## Approved exceptions

A PR may deviate only with an approved exception containing:

* Exception ID
* Affected requirement
* Justification
* Compensating controls
* Approver
* Expiry date

## Review decision criteria

* Important: mandatory/prohibited requirement violated without an approved exception.
* Suggestion: HLD requirement is ambiguous or cannot be verified from Terraform and plan output.
* No finding: Terraform and generated plan conform to this baseline.

## Terraform review inputs

Review, when present:

* Changed `.tf`, `.tfvars`, and module files
* Environment-specific Terraform configuration
* Sanitised `artifacts/tfplan.json`
* Approved architecture exception referenced in the PR
