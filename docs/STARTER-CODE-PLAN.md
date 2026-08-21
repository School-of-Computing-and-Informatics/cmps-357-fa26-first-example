# Starter Code Plan

This document defines the deliberately minimal Java project students receive at the beginning of the Day 1 exercise.

## Repository structure

```text
cmps-fa26-first-example/
├── .vscode/                 # VS Code tasks and Java settings
├── .idea/                   # IntelliJ project and run configuration
├── .settings/               # Eclipse Java settings
├── nbproject/               # NetBeans free-form project metadata
├── .classpath               # Eclipse classpath
├── .project                 # Eclipse project definition
├── build.xml                # NetBeans/Ant compile and run targets
├── cmps-fa26-first-example.iml
├── Main.launch              # Eclipse run configuration
├── README.md
├── docs/
│   ├── SPEC.md
│   ├── AI-WORKLOG.md
│   ├── STARTER-CODE-PLAN.md
│   ├── INSTRUCTOR-GUIDE.md
│   └── IDE-SETUP.md
└── src/
    ├── Main.java
    ├── Supply.java
    └── Workshop.java
```

## Initial Java files

`Main.java` contains only a valid class and an empty `main` method:

```java
public class Main {
    public static void main(String[] args) {
    }
}
```

`Supply.java` and `Workshop.java` contain empty, compiling class declarations:

```java
public class Supply {
}
```

```java
public class Workshop {
}
```

Running the initial project should succeed and produce no output. The empty files make the starting point explicit while leaving all model and driver decisions visible during the live exercise.

The committed [IDE metadata](TECHNICAL-REFERENCE.md#project-metadata-module-and-run-configuration) is [scaffolding](TECHNICAL-REFERENCE.md#scaffolding) only. It standardizes Java 17, the `src` [source root](TECHNICAL-REFERENCE.md#source-root-and-output-directory), the `out` build directory, and the `Main` [entry point](TECHNICAL-REFERENCE.md#main-method-driver-and-entry-point) without supplying any [domain implementation](TECHNICAL-REFERENCE.md#implementation-and-behavior).

## Intended completed responsibilities

The specification, rather than starter TODO comments, defines what must be built.

### `Supply.java`

Students add the fields, constructor, and accessors required by `SPEC.md`.

### `Workshop.java`

Students add the stored state, constructor, accessors, collection behavior, scaling behavior, and output formatting required by `SPEC.md`.

### `Main.java`

Students complete the [driver](TECHNICAL-REFERENCE.md#main-method-driver-and-entry-point) using the exact workshop and supply data in `SPEC.md`. The driver constructs objects and displays results but does not implement domain behavior itself.

## What not to include initially

- a completed or partially implemented domain model;
- TODO comments that prescribe an implementation strategy;
- external [dependencies or an unnecessary build framework](TECHNICAL-REFERENCE.md#dependency-and-build-framework);
- collection, search, sorting, [persistence, or UI](TECHNICAL-REFERENCE.md#persistence-and-ui) stages;
- an answer key on a student-visible [branch](TECHNICAL-REFERENCE.md#clone-fork-branch-and-commit);
- generated IDE output, [`.class` files](TECHNICAL-REFERENCE.md#class-file-and-classpath), or an `out/` directory; or
- tests that reveal the complete implementation strategy.

## Pre-class acceptance checklist

- A clean clone contains all three Java files.
- The project compiles under Java 17 or later.
- Running `Main` exits successfully and produces no output.
- The specification contains every API and behavior students need to build.
- The example data exercises integer, one-decimal, and rounded two-decimal output.
- A private instructor solution matches the required output exactly.
- No solution or partial solution is visible in the student repository.
