

# Verify SonarQube Cloud MCP

Use the `Sonarcloud` MCP integration to retrieve SonarQube Cloud data for project key `justin-chi-sonarsource_javulna`.

For every pull request:

* Confirm that project `justin-chi-sonarsource_javulna` is accessible.
* Retrieve the current quality-gate status for the project.
* Retrieve open issues for the project and count blocker and critical issues.
* Post a short PR summary containing:
  * Project key
  * Quality-gate status
  * Number of open blocker issues
  * Number of open critical issues

If the project cannot be found or the integration cannot authenticate, post the exact error message and state whether it appears to be an organization-key, project-key, or authorization problem.


