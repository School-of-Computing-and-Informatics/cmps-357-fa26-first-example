# CMPS 357 Fall 2026 First Example

This repository contains the first in-class example for **CMPS 357: Accelerated Software Development Using AI Tools**.

The exercise uses a small **Workshop Supply Planner**. Students start with three empty Java classes in a project that already compiles. During class, they will use written requirements and an AI coding assistant to add the program's data and actions in small steps, checking each change until the output exactly matches the requirements.

## Learning goals

During this exercise, students will practice:

- reading a software [specification](docs/TECHNICAL-REFERENCE.md#specification) before changing code;
- identifying the smallest relevant [context](docs/TECHNICAL-REFERENCE.md#ai-context-and-feedback-cycle) for an AI assistant;
- implementing and verifying one behavior at a time;
- reviewing generated code instead of accepting it automatically;
- comparing actual output with exact required output; and
- making small Git [commits](docs/TECHNICAL-REFERENCE.md#clone-fork-branch-and-commit) with meaningful messages.

## Day 1 workflow

1. [Clone](docs/TECHNICAL-REFERENCE.md#clone-fork-branch-and-commit) the repository and open it in a supported Java [IDE](docs/TECHNICAL-REFERENCE.md#ide).
    1. If a student is familiar with Git, they may consider making their own [fork](docs/TECHNICAL-REFERENCE.md#clone-fork-branch-and-commit).
    2. Otherwise, student changes can be made in a local [branch](docs/TECHNICAL-REFERENCE.md#clone-fork-branch-and-commit) as demonstrated in class.
2. Read [`docs/SPEC.md`](docs/SPEC.md) before prompting an AI assistant.
3. Run `Main` and observe that the project [compiles](docs/TECHNICAL-REFERENCE.md#compile-build-and-run) but produces no output.
4. Add the data [model](docs/TECHNICAL-REFERENCE.md#model-and-domain-model) and required [methods](docs/TECHNICAL-REFERENCE.md#api) in small, reviewable steps.
5. Implement one behavior at a time.
6. Run the program and inspect the [diff](docs/TECHNICAL-REFERENCE.md#diff) after every meaningful change.
7. Compare the final output character-for-character with the specification.
8. Record important prompts, decisions, and verification steps in [`docs/AI-WORKLOG.md`](docs/AI-WORKLOG.md).
9. Commit each understandable improvement separately.

## Suggested implementation order

1. `Supply` [fields, constructor, and accessors](docs/TECHNICAL-REFERENCE.md#field-constructor-and-accessor)
2. `Workshop` fields, constructor, and accessors
3. `addSupply`
4. `totalSupplyCount`
5. `scaleToAttendees`
6. private amount-formatting [helper](docs/TECHNICAL-REFERENCE.md#helper-method)
7. `toString` and `toPrettyString`
8. the example sequence in `Main`

Students should not ask an AI assistant to finish the entire project in one request. The exercise is designed around focused prompts, short [feedback cycles](docs/TECHNICAL-REFERENCE.md#ai-context-and-feedback-cycle), and deliberate verification.

## Documentation

- [`docs/SPEC.md`](docs/SPEC.md) — source of truth for required behavior
- [`docs/AI-WORKLOG.md`](docs/AI-WORKLOG.md) — student record of AI-assisted work
- [`docs/STARTER-CODE-PLAN.md`](docs/STARTER-CODE-PLAN.md) — required shape of the starter project
- [`docs/INSTRUCTOR-GUIDE.md`](docs/INSTRUCTOR-GUIDE.md) — suggested classroom sequence and discussion points
- [`docs/IDE-SETUP.md`](docs/IDE-SETUP.md) — setup and run instructions for VS Code, IntelliJ IDEA, Eclipse, and NetBeans
- [`docs/TECHNICAL-REFERENCE.md`](docs/TECHNICAL-REFERENCE.md) — refresher for Java, Git, IDE, build, and AI-development terminology
- [`docs/KINDLE.html`](docs/KINDLE.html) — Kindle-ready compilation of every Markdown document
- [`docs/TRANSCRIPT.md`](docs/TRANSCRIPT.md) — summary and shared conversation for the repository's development

## IDE support

Project files are included for:

- Visual Studio Code, using explicit Java [CLI](docs/TECHNICAL-REFERENCE.md#cli) compile and run tasks;
- IntelliJ IDEA;
- Eclipse; and
- NetBeans, using the included [Ant build file](docs/TECHNICAL-REFERENCE.md#ant-and-build-file).

See [`docs/IDE-SETUP.md`](docs/IDE-SETUP.md) for instructions. All configurations target Java 17 and share the same `src` [source directory](docs/TECHNICAL-REFERENCE.md#source-root-and-output-directory) and `out` build directory.

## Repository structure

```text
cmps-fa26-first-example/
├── .idea/                              # Shared IntelliJ IDEA project configuration
│   ├── runConfigurations/              # IntelliJ run configurations available to all users
│   │   └── Main.xml                    # Builds the module and launches the Main class
│   ├── compiler.xml                    # Sends IntelliJ compiler output to the out directory
│   ├── misc.xml                        # Sets the IntelliJ project language level to Java 17
│   └── modules.xml                     # Registers the committed IntelliJ module file
├── .settings/                          # Shared Eclipse workspace-independent settings
│   ├── org.eclipse.core.resources.prefs # Uses UTF-8 for Eclipse project resources
│   └── org.eclipse.jdt.core.prefs      # Sets Eclipse compiler compliance to Java 17
├── .vscode/                            # Shared Visual Studio Code workspace configuration
│   ├── extensions.json                 # Recommends the Extension Pack for Java
│   ├── settings.json                   # Declares src and out as the Java source and output paths
│   └── tasks.json                      # Defines javac compilation and java execution tasks
├── docs/                               # Specifications, setup guidance, and process records
│   ├── AI-WORKLOG.md                   # Template for prompts, reviews, verification, and reflection
│   ├── IDE-SETUP.md                    # Instructions for the four supported IDEs and command line
│   ├── INSTRUCTOR-GUIDE.md             # Suggested 75-minute lesson plan and review questions
│   ├── KINDLE.html                     # Kindle-ready compilation with contents links and page breaks
│   ├── SPEC.md                         # Source of truth for required behavior and exact output
│   ├── STARTER-CODE-PLAN.md            # Defines the intended empty starting state and responsibilities
│   ├── TECHNICAL-REFERENCE.md           # Refreshes Java, Git, IDE, build, and AI-development terms
│   └── TRANSCRIPT.md                   # Summarizes the requests and decisions behind the repository
├── nbproject/                          # NetBeans free-form Ant project configuration
│   ├── project.properties              # Defines the src, out, and UTF-8 project properties
│   └── project.xml                     # Maps NetBeans build, clean, and run actions to Ant targets
├── src/                                # Java source root used by every supported environment
│   ├── Main.java                       # Empty command-line driver completed during class
│   ├── Supply.java                     # Empty supply-model class completed during class
│   └── Workshop.java                   # Empty workshop-model class completed during class
├── .classpath                          # Maps Eclipse source, Java 17 runtime, and output paths
├── .gitignore                          # Excludes generated classes, build output, and local IDE state
├── .project                            # Identifies the repository as an Eclipse Java project
├── Main.launch                         # Shared Eclipse launch configuration for Main
├── README.md                           # Introduces the exercise, workflow, documentation, and IDEs
├── build.xml                           # Compiles, runs, and cleans the project for NetBeans and Ant
└── cmps-fa26-first-example.iml         # Defines the IntelliJ Java module and its source root
```

The generated `out/` directory is not shown because it is created locally by the selected build path and excluded from version control.

If generated code and the specification disagree, the specification wins.
