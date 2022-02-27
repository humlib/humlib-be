# Set up elastic stack
documentation: [8.0.0](https://www.elastic.co/guide/en/elastic-stack-get-started/8.0/get-started-stack-docker.html#run-docker-secure "here")

## prerequisites
- docker desktop
- at least 4GB available memory

### bashrc/zshrc
- open a terminal
- navigate to `humlib-be/docker`
- copy `humlib.profiles` to `humlib.profiles.overrides` and set the absolute path to your "humlib-be"-project
- execute `source humlib.profiles.overrides`