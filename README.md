# JavaBuild

*This is just an experiment i'm making to scratch an itch, feel free to chime in with ideas but don't expect too much from it for now*

What if Java had something similar to the Swift Package Manager, a straightforward extensible Java build tool with a configuration written completely in Java?

TODO:
- [x] It should be able to build itself
- [ ] Actually reading and compiling Build.java
- [ ] Build a simple javabuild-ready Hello World
- [ ] Build a project with multiple interdependent java files, solving the dependency graph, incremental build
- [ ] Support external local dependencies (i.e jar in a folder)
- [ ] Support external dependencies via maven central or the similar gradle thing
- [ ] Support external javabuild-ready dependencies from github, get them, build them, use them
- [ ] Allow to include other file types in the resulting jar
- [ ] Configurable output: jar,fatjar,war,whatever

