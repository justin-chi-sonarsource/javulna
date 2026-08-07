---
title: "Terraform HLD Conformance"
description: "Validate Terraform changes against the approved HLD"
when: "A PR modifies Terraform files, Terraform modules, Terraform variables, environment infrastructure configuration, or artifacts/tfplan.json"
actions: "Evaluate Terraform against the HLD baseline. Post inline findings for deviations, add the hld-conformance-review label, assign @cloud-infra-team as reviewer, and post a summary of compliance status."
---

# Infrastructure governance workflow

Use the requirements in:

@../review/terraform-hld-conformance.md

When Terraform and the plan comply with the HLD baseline, post:

"Terraform HLD conformance check passed."

When a mandatory HLD requirement is violated:

* Add label `hld-conformance-review`.
* Assign `@cloud-infra-team`.
* Post a summary listing the violated requirement and affected resources.
* State that merge requires remediation or an approved architecture exception.
