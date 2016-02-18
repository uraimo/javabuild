# JavaBuild

*This is just an experiment i'm making to scratch an itch, feel free to chime in with ideas but don't expect too much from it for now*

What if Java had something similar to the Swift Package Manager, a straightforward extensible Java build tool with a configuration written completely in Java?


### Usage

JavaBuild is a build tool (ANT,Maven,Gradle,...) that uses configuration files written in pure Java.
Every project has a `Build.java` file that contains all the configuration to compile the module (the basic unit that JavaBuild manages, in other words a project with some sources).

Init a project (it creates the standard Maven src directory and a simple Build.java):

```
./javabuild --init
```

Compile the content of a project (you'll find the result .class files in target/):
```
./javabuild --compile
```

Build a jar (you'll find the resulting jar in target/):
```
./javabuild --jar
```

Clean the project removing all artifacts built by JavaBuild:
```
./javabuild --clean
```


### TODO
- [x] It should be able to build itself
- [ ] In dire need of a refactoring
- [ ] Add unit/integration tests
- [ ] Add support for test (specific command)
- [ ] Actually reading and compiling Build.java
- [ ] Build a simple javabuild-ready Hello World
- [ ] Build a project with multiple interdependent java files, solving the dependency graph, incremental build
- [ ] Support external local dependencies (i.e jar in a folder)
- [ ] Support external dependencies via maven central or the similar gradle thing
- [ ] Support external javabuild-ready dependencies from github, get them, build them, use them
- [ ] Allow to include other file types in the resulting jar
- [ ] Configurable output: jar,fatjar,war,whatever
