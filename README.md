# CMPS 357 Fall 2026 First Example

This repository contains the first in-class example for **CMPS 357: Accelerated Software Development Using AI Tools**.

The example uses a small **Workshop Supply Planner** to introduce specification-driven development with an AI coding assistant. Students begin with a compiling Java project containing an empty main driver and empty domain-class shells. The goal is to build the required model and behavior incrementally until the program output matches the written specification.

## Learning goals

During this exercise, students will practice:

- reading a software specification before changing code;
- identifying the smallest relevant context for an AI assistant;
- implementing and verifying one behavior at a time;
- reviewing generated code instead of accepting it automatically;
- comparing actual output with exact required output; and
- making small Git commits with meaningful messages.

## Day 1 workflow

1. Clone the repository and open it in a supported Java IDE.
2. Read [`docs/SPEC.md`](docs/SPEC.md) before prompting an AI assistant.
3. Run `Main` and observe that the project compiles but produces no output.
4. Add the data model and required methods in small, reviewable steps.
5. Implement one behavior at a time.
6. Run the program and inspect the diff after every meaningful change.
7. Compare the final output character-for-character with the specification.
8. Record important prompts, decisions, and verification steps in [`docs/AI-WORKLOG.md`](docs/AI-WORKLOG.md).
9. Commit each understandable improvement separately.

## Suggested implementation order

1. `Supply` fields, constructor, and accessors
2. `Workshop` fields, constructor, and accessors
3. `addSupply`
4. `totalSupplyCount`
5. `scaleToAttendees`
6. private amount-formatting helper
7. `toString` and `toPrettyString`
8. the example sequence in `Main`

Students should not ask an AI assistant to finish the entire project in one request. The exercise is designed around focused prompts, short feedback cycles, and deliberate verification.

## Documentation

- [`docs/SPEC.md`](docs/SPEC.md) — source of truth for required behavior
- [`docs/AI-WORKLOG.md`](docs/AI-WORKLOG.md) — student record of AI-assisted work
- [`docs/STARTER-CODE-PLAN.md`](docs/STARTER-CODE-PLAN.md) — required shape of the starter project
- [`docs/INSTRUCTOR-GUIDE.md`](docs/INSTRUCTOR-GUIDE.md) — suggested classroom sequence and discussion points
- [`docs/IDE-SETUP.md`](docs/IDE-SETUP.md) — setup and run instructions for VS Code, IntelliJ IDEA, Eclipse, and NetBeans
- [`docs/TRANSCRIPT.md`](docs/TRANSCRIPT.md) — summary and shared conversation for the repository's development

## IDE support

Project files are included for:

- Visual Studio Code, using explicit Java CLI compile and run tasks;
- IntelliJ IDEA;
- Eclipse; and
- NetBeans, using the included Ant build file.

See [`docs/IDE-SETUP.md`](docs/IDE-SETUP.md) for instructions. All configurations target Java 17 and share the same `src` source directory and `out` build directory.

If generated code and the specification disagree, the specification wins.
