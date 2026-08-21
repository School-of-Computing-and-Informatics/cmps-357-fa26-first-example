# Technical Reference

This reference refreshes software-development terms used throughout the Day 1 example. It is intended as a quick reminder, not as a substitute for the Java, Git, or IDE documentation used in earlier courses.

## Repository

A repository is a project tracked by Git. It contains the project files and the history of committed changes.

## Specification

A specification describes the required behavior of a program. In this exercise, `docs/SPEC.md` is the authoritative description of what the finished program must do.

## Implementation and behavior

An implementation is the code that fulfills a requirement. Behavior is what the program does when that code runs, including its returned values, state changes, errors, and displayed output.

## Model and domain model

A model is a code representation of information and related behavior. A domain model represents concepts from the problem being solved; here, `Workshop` and `Supply` represent the workshop-planning domain.

## IDE

An integrated development environment (IDE) combines source-code editing with tools for compiling, running, debugging, and navigating a project. This repository supports VS Code, IntelliJ IDEA, Eclipse, and NetBeans.

## Compile, build, and run

- **Compile:** translate Java source code into JVM class files.
- **Build:** perform the steps needed to prepare the program, commonly including compilation.
- **Run:** start the compiled program.

## Main method, driver, and entry point

The `main` method is where this Java command-line program begins running. A driver is code that creates objects and calls their methods to demonstrate or coordinate behavior. An entry point is the location where program execution begins; in this project, it is `Main.main`.

## Class and class shell

A class defines a Java type and may contain fields, constructors, and methods. A class shell is a valid but mostly empty class declaration that will be completed later.

## Scaffolding

Scaffolding is prearranged project structure or configuration that supports later development without providing the completed program behavior. The IDE files and empty Java classes serve as scaffolding in this repository.

## API

An application programming interface (API) is the set of constructors, methods, parameters, and return values through which other code uses a class or component.

## Field, constructor, and accessor

- A **field** stores part of an object's state.
- A **constructor** initializes a newly created object.
- An **accessor** is a method that reads stored data, commonly called a getter. A setter changes stored data.

## State and mutability

State is the data currently stored by an object. Mutable state can change after the object is created; immutable state cannot. Java's `final` keyword prevents a field from being assigned a different value after initialization, although an object referenced by that field may still be mutable.

## Defensive copy

A defensive copy is a new collection or object returned in place of mutable internal data. It prevents calling code from changing an object's internal state without using the object's methods.

## Helper method

A helper method performs a focused internal task for other methods. A private helper can be used only inside its own class.

## Clone, fork, branch, and commit

- **Clone:** make a local copy of a repository.
- **Fork:** create a separate GitHub-hosted copy of another repository under a different account or organization.
- **Branch:** maintain a named line of development within a repository.
- **Commit:** record a related set of changes in Git history with a message.

## Diff

A diff shows the lines added, removed, or changed between two versions. Reviewing a diff helps detect unintended edits before a commit is accepted.

## AI context and feedback cycle

Context is the information supplied to an AI assistant, such as requirements, source files, existing output, and error messages. A feedback cycle is one round of requesting a change, reviewing it, running the program, and using the result to guide the next request.

## CLI

A command-line interface (CLI) accepts text commands in a terminal. The VS Code tasks in this repository run the Java compiler and program through their CLIs.

## JDK and SDK

The Java Development Kit (JDK) contains the compiler and other tools required to develop Java programs. IDEs sometimes use the broader term software development kit (SDK) when selecting the JDK for a project.

## Source root and output directory

The source root is the directory an IDE or build tool treats as the beginning of the source-code structure; this project uses `src`. The output directory stores generated build results; this project uses `out` for compiled class files.

## Class file and classpath

A `.class` file contains compiled Java bytecode for the JVM. The classpath is the set of locations Java searches for compiled classes and libraries when compiling or running a program.

## Project metadata, module, and run configuration

Project metadata consists of tool-specific files that describe how an IDE should open and build a project. A module is an IDE grouping of source code and settings. A run configuration records how an IDE should start a program, including its entry point and classpath.

## Ant and build file

Apache Ant is a Java build tool. Its `build.xml` file defines named targets such as compile, run, and clean. NetBeans uses that file for this repository.

## Dependency and build framework

A dependency is external code required by a project. A build framework or build tool automates tasks such as compilation, testing, packaging, and dependency management. This example intentionally has no external dependencies and does not require Maven or Gradle.

## Floating-point division

Floating-point division preserves a fractional result, such as `36.0 / 24.0 = 1.5`. Integer division discards the fractional portion when both operands are integers.

## Exception and validation

Validation checks whether input satisfies required rules before the program uses it. An exception signals that normal execution cannot continue; this specification requires `IllegalArgumentException` for a nonpositive attendee count.

## Insertion order

Insertion order is the sequence in which items were added to a collection. Preserving it means later operations return or display those items in the same sequence.

## Hard-coded value

A hard-coded value is written directly into the implementation instead of being calculated from stored data or supplied input. Hard-coding the example output would make only that one example appear correct.

## Side effect

A side effect is an observable change beyond returning a value, such as printing output or modifying an object's state. In this exercise, `toString()` returns text and must not print it directly.

## Persistence and UI

Persistence saves data so it remains available after the program ends, commonly in a file or database. A user interface (UI) is how a person interacts with a program; examples include command-line, graphical, and web interfaces.

## GUI and virtual machine

A graphical user interface (GUI) uses visual controls such as windows, menus, and buttons. A virtual machine (VM), in the repository transcript, means an isolated computer environment used to install and test an IDE; it does not mean the Java Virtual Machine (JVM).
