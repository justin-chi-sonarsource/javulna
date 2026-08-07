---
title: "Font License Governance"
description: "Check approved font usage and involve UI/UX for exceptions"
when: "A PR modifies CSS, SCSS, theme files, design tokens, UI components, @font-face declarations, font imports, or font asset files"
actions: "Evaluate the licensed-font review instruction. If an unapproved or ambiguous font is detected, post inline findings, add the font-license-review label, assign @ui-ux-team as reviewer, and post a summary comment."
---

# Font governance workflow

Use the requirements in:

@../review/font-licensing.md

When all changed font references comply with the approved-font catalogue,
post a short PR comment: "Font licensing check passed."

When a violation is found:

* Add label `font-license-review`.
* Assign `@ui-ux-team`.
* State that merge requires an approved-font replacement or a documented
  licensing exception.
