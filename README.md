# humlib-be
Backend for HumLib - The Human Library in Computer Science

## Development workflow
We use [GitHub flow](https://githubflow.github.io/) as git branching strategy. It is based on the 6 principles below.

- Anything in the master branch is deployable
- To work on something new, create a descriptively named branch off of master (ie: new-oauth2-scopes)
- Commit to that branch locally and regularly push your work to the same named branch on the server
- When you need feedback or help, or you think the branch is ready for merging, open a pull request
- After someone else has reviewed and signed off on the feature, you can merge it into master
- Once it is merged and pushed to 'master', you can and should deploy immediately  

For commit messages, we use [GitMoji](https://gitmoji.dev/). There is a handy IntelliJ plugin to help you choose the right emoji.
Other than that, a commit message should follow the pattern `:emoji: Short description (#:issue number:)`.

## SDKman
For automatically using Java 17 when entering this repository, simply set `sdkman_auto_env=true` in `~/.sdkman/etc/config`
This makes sdkman switch to the java version defined in `.sdkmanrc` automatically when you `cd` into this repo.
