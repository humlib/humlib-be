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

## Local development (Docker)

### Prerequisites

- Docker Desktop
  using [Docker Compose v2](https://docs.docker.com/compose/cli-command/) ([Compose file documentation](https://github.com/compose-spec/compose-spec/blob/master/spec.md))
- at least 6GB available memory
- each docker container uses 1GB memory per default. Change this behaviour per environment via
    - DOCKER_MEM_LIMIT_ELASTICSTACK
    - DOCKER_MEM_LIMIT_KEYCLOAK

### Setup

- open a terminal
- variant 1:
  - set HUMLIB_BE_HOME in your .zshrc or .bashrc `export HUMLIB_BE_HOME="<path>"`
  - also add `source ${HUMLIB_BE_HOME}/docker/humlib.profiles`
- variant 2:
  - navigate to this directory
  - execute `HUMLIB_BE_HOME=$(pwd) source docker/humlib.profiles` from the repository root

### All services

- start all backend services using `humlib-be-start` or `humlib-be-start-min`
- stop the docker setup with the command `humlib-be-stop` or stop and delete all volumes via `humlib-be-delete`
- you may need to follow usage hints as depicted below

### Elasticstack

version: [8.0.0](https://www.elastic.co/guide/en/elastic-stack-get-started/8.0/get-started-stack-docker.html#run-docker-secure "documentation")

#### Start and stop commands

- initialize and start the elasticstack via `humlib-be-elasticstack-start`
- if you only want to run elasticsearch, use the command `humlib-be-elasticstack-start-min`
- stop the docker setup with the command `humlib-be-elasticstack-stop` or stop and delete all volumes
  via `humlib-be-elasticstack-delete`

#### Usage

- copy the autogenerated elasticstack certificate to your
  keychain [docker/elasticstack/volumes/certs/ca/ca.crt](docker/elasticstack/volumes/certs/ca/ca.crt 'docker/elasticstack/volumes/certs/ca/ca.crt')
- elasticsearch: [https://localhost:9200](https://localhost:9200 'https://localhost:9200')
- kibana: [http://localhost:5601](http://localhost:5601 'http://localhost:5601')
- logstash: listens for beats input on port 5044 ([localhost:5044](localhost:5044 'localhost:5044')) and reads `*.log`
  -files from the directory `humlib-be/logs`

#### Built-in users

- elastic:elastic (superuser)
- kibana_system:kibana_system

### Keycloak

#### Start and stop commands

- initialize and start keycloak via `humlib-be-keycloak-start`
- stop the docker setup with the command `humlib-be-keycloak-stop` or stop and delete all volumes
  via `humlib-be-keycloak-delete`

#### Usage

- to export changes made to the keycloak instance locally, use `humlib-be-keycloak-export`, wait until a log
  message `Resuming server` appears, and hit Ctrl-c
- keycloak: [https://localhost:8180](https://localhost:8180 'https://localhost:8180')
- mailhog: [http://localhost:8025](http://localhost:8025 'http://localhost:8025')
- mailhog (SMTP): [http://localhost:1025](http://localhost:1025 'http://localhost:8025')

#### Built-in users

- admin:admin
