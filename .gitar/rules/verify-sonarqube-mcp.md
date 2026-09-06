---
title: "Verify SonarQube Cloud MCP"
description: "Verify that Gitar can retrieve quality-gate and issue data for the javulna project."
when: "Any pull request"
actions: "Use the Sonarcloud MCP integration to check the current pull request for SonarQube Cloud project justin-chi-sonarsource_javulna. Retrieve its quality-gate status and issues, then post a short PR summary."
---

# Verify SonarQube Cloud MCP

Use the `Sonarcloud` MCP integration to retrieve SonarQube Cloud data for project key `justin-chi-sonarsource_javulna`.

For every pull request, use the Sonarcloud MCP integration to check the current PR for project `justin-chi-sonarsource_javulna`.

* Confirm that the project is accessible.
* Identify the current pull request or matching pull-request analysis.
* Retrieve the pull request's quality-gate status.
* Retrieve issues raised on the pull request and count blocker and critical issues.
* Post a short PR summary containing:
  * Project key
  * Pull request quality-gate status
  * Number of new blocker issues
  * Number of new critical issues
  * A short list of the most important issues, including file and line when available

If no SonarQube Cloud analysis exists for the current pull request, state that clearly and do not report main-branch results as PR results. If the project cannot be found or the integration cannot authenticate, post the exact error message and state whether it appears to be an organization-key, project-key, or authorization problem.
