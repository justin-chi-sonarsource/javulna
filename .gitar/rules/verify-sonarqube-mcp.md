# Verify SonarQube Cloud MCP

For every pull request, use the SonarQube MCP integration to:
1. Find the SonarQube Cloud project for this repository.
2. Retrieve the current quality gate status.
3. List the highest-severity open issues on the default branch.
Include the project key, quality-gate result, and issue count in the PR review summary.
