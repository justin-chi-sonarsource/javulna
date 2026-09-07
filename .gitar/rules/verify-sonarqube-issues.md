---
title: "Verify and Fix SonarQube Cloud Issues"
description: "Use SonarQube Cloud Embedded MCP to check each pull request and safely fix eligible issues."
when: "Any pull request"
actions: "Use the Sonarcloud MCP integration to check the current pull request for SonarQube Cloud project justin-chi-sonarsource_javulna. Retrieve its quality-gate status and issues. Automatically fix safe, localized issues, push a commit to the PR branch, re-check the PR analysis, and post a summary."
---

# Verify and Fix SonarQube Cloud Issues

Use the Sonarcloud MCP integration to check the current pull request for SonarQube Cloud project `justin-chi-sonarsource_javulna`.

For every pull request:
* Resolve the organization key as follows: if the Sonarcloud MCP integration supplies
     the organization via `SONARQUBE_ORG`, use that value and report the resolved
     organization in the summary. Otherwise fall back to the Sonarcloud MCP integration
     connection defaults for project key `justin-chi-sonarsource_javulna` and organization
     key `justin-chi-sonarsource`.
* Identify the current pull request or matching pull-request analysis.
* Retrieve the pull request's quality-gate status.
* Retrieve issues raised on the pull request and count blocker and critical issues.

For each issue found on the current pull request:

* Apply an automatic fix only when it is safe, localized, and does not change intended behavior.
* Keep the fix within the current PR's scope.
* Commit the fix to the PR branch.
* Re-check the SonarQube Cloud pull-request analysis after the fix, waiting until the
  analysis for the newly pushed commit SHA has completed. If it has not completed,
  report the post-fix quality gate as "analysis pending" rather than reusing the
  pre-fix status.
* Do not auto-fix issues requiring architectural decisions, unclear requirements, dependency upgrades, broad refactoring, or behavioral changes; report these instead.

Post a short PR summary containing:

* Project key
* Pull-request quality-gate status before and after fixes
* Number of blocker and critical issues found
* Issues fixed automatically, including file and line when available
* Remaining issues and why they were not fixed

If no SonarQube Cloud analysis exists for the current pull request, state that clearly and do not report main-branch results as PR results. If the project cannot be found or the integration cannot authenticate, post the exact error message and state whether it appears to be an organization-key, project-key, or authorization problem.
