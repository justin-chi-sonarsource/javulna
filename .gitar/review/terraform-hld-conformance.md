# Terraform to HLD conformance review

@../documents/hld-infrastructure-baseline.md

Apply this review only when Terraform files, Terraform modules, environment
variables, or generated Terraform plan JSON are changed.

Compare the proposed infrastructure against the HLD baseline.

Check:
- Cloud account/subscription/project, region, and environment boundaries
- Network topology, CIDR ranges, subnet roles, routing, and ingress/egress
- Resource types, names, sizes/SKUs, counts, and autoscaling boundaries
- Encryption, secrets handling, IAM roles, logging, monitoring, and tagging
- Availability-zone, backup, and disaster-recovery requirements
- Explicitly prohibited services, public exposure, and architecture exceptions

For each deviation:
- Post an inline finding where possible.
- Quote the applicable HLD requirement and the Terraform/plan evidence.
- Classify as Important when it violates a mandatory requirement; otherwise
  Suggestion when clarification is required.
- Do not infer missing HLD requirements. Report them as “HLD ambiguity”.
