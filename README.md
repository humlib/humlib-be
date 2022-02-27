[![Continuous Integration](https://github.com/mor4thii/humlib-be/actions/workflows/ci.yml/badge.svg)](https://github.com/mor4thii/humlib-be/actions/workflows/ci.yml)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/561b77a871fb48d084975e6e832aea97)](https://www.codacy.com/gh/mor4thii/humlib-be/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mor4thii/humlib-be&amp;utm_campaign=Badge_Coverage)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/561b77a871fb48d084975e6e832aea97)](https://www.codacy.com/gh/mor4thii/humlib-be/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mor4thii/humlib-be&amp;utm_campaign=Badge_Grade)

# humlib-be

Backend for HumLib - The Human Library in Computer Science

## Development workflow

We use [GitHub flow](https://githubflow.github.io/) as git branching strategy. It is based on the 6 principles below.

- Anything in the 'main' branch is deployable
- To work on something new, create a descriptively named branch off of 'main' (ie: new-oauth2-scopes)
- Commit to that branch locally and regularly push your work to the same named branch on the server
- When you need feedback or help, or you think the branch is ready for merging, open a pull request
- After someone else has reviewed and signed off on the feature, you can merge it into 'main'
- Once it is merged and pushed to 'main', you can and should deploy immediately

For commit messages, we use [GitMoji](https://gitmoji.dev/). There is a handy IntelliJ plugin to help you choose the
right emoji. Other than that, a commit message should follow the pattern `:emoji: Short description (#:issue number:)`.

## SDKman

For automatically using Java 17 when entering this repository, simply set `sdkman_auto_env=true`
in `~/.sdkman/etc/config`
This makes sdkman switch to the java version defined in `.sdkmanrc` automatically when you `cd` into this repo.

## CI

You need to have a pull request for your branch to have the defined GitHub workflows executed automatically. On push
to 'main', so basically when a pull request is merged, another run is triggered for 'main'.

The pipeline builds the application and executes tests. It then uploads coverage data and test results as artifacts.
Both are posted as a comment to PRs. Unit test results can also be reviewed as a dedicated check run. Code coverage
results are uploaded to [Codacy](https://app.codacy.com/gh/mor4thii/humlib-be/dashboard?branch=main)
