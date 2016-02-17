JavaBuild

What if Java had something similar to the Swift Package Manager? A straightforward Java build tool with a simple configuration written in Java.
Just an experiment.

TODO:
- [ ] Build a simple javabuild-ready Hello World
- [ ] Build a project with multiple interdependent java files, solving the dependency graph, incremental build
- [ ] Support external local dependencies (i.e jar in a folder)
- [ ] Support external dependencies via maven central or the similar gradle thing
- [ ] Support external javabuild-ready dependencies from github, get them, build them, use them
- [x] It should be able to build itself
- [ ] Allow to include other file types in the resulting jar
- [ ] Configurable output: jar,fatjar,war,whatever

