[![Continuous Integration](https://github.com/mor4thii/humlib-be/actions/workflows/ci.yml/badge.svg)](https://github.com/mor4thii/humlib-be/actions/workflows/ci.yml)

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

For commit messages, we use [GitMoji](https://gitmoji.dev/). There is a handy IntelliJ plugin to help you choose the
right emoji. Other than that, a commit message should follow the pattern `:emoji: Short description (#:issue number:)`.

## SDKman

For automatically using Java 17 when entering this repository, simply set `sdkman_auto_env=true`
in `~/.sdkman/etc/config`
This makes sdkman switch to the java version defined in `.sdkmanrc` automatically when you `cd` into this repo.

## elasticstack
version: [8.0.0](https://www.elastic.co/guide/en/elastic-stack-get-started/8.0/get-started-stack-docker.html#run-docker-secure "documentation")

### prerequisites
- docker desktop
- at least 4GB available memory

### setup
- open a terminal
- navigate to the directory `humlib-be/docker`
- copy `humlib.profiles` to `humlib.profiles.overrides` and set the absolute path to your "humlib-be"-project
- execute `source humlib.profiles.overrides`

### start and stop commands
- initialize and start the elasticstack via `humlib-be-elasticstack-start`
- if you only want to run elasticsearch, use the command `humlib-be-elasticstack-start-min`
- stop the docker setup with the command `humlib-be-elasticstack-stop` or stop and delete all volumes via `humlib-be-elasticstack-delete`

### usage
- elasticsearch: [https://localhost:9200](https://localhost:9200 'https://localhost:9200')
- kibana: [localhost:5601](localhost:5601 'localhost:5601')
- logstash: listens for beats input on port 5044 ([localhost:5044](localhost:5044 'localhost:5044')) and reads `*.log`-files from the directory `humlib-be/logs`

### built-in users
- superuser: elastic - password: [USER_ELASTIC_PASSWORD](docker/.env.local 'USER_ELASTIC_PASSWORD')
- user: kibana_systems - password: [USER_KIBANA_SYSTEMS_PASSWORD](docker/.env.local 'USER_KIBANA_SYSTEMS_PASSWORD')