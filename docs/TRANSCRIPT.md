# Development Transcript Summary

This document summarizes the requests that shaped the Fall 2026 Day 1 example and its repository scaffolding.

## 1. Review the previous Day 1 example

The completed Spring 2026 recipe example was provided as a reference, and three alternative domains were requested for Fall 2026. The suggested options were a Workshop Supply Planner, a Trail Expedition Planner, and a Community Garden Planner. The Workshop Supply Planner was recommended because it preserves the original learning progression while giving the exercise different content.

**Actual Requests:**
```
This is my day 1 example, completed, from last semester

https://github.com/School-of-Computing-and-Informatics/cmps357-sp26-first-example

Suggest 3 similar examples with different content to use this semester
```
## 2. Define the Workshop Supply Planner content

The next request asked what information should be present in the repository when class begins. This established the workshop model, required methods, scaling and formatting rules, exact expected output, student workflow, AI work log, and intended starter-code structure.

**Actual Requests:**
```
Give the info that should be in the repo for option 1 when class starts
```

## 3. Build the Day 1 documentation

The new Fall 2026 repository was provided and a documentation-only first version was requested. The initial repository content included the student README, behavioral specification, AI work-log template, starter-code plan, and 75-minute instructor guide.

**Actual Requests:**
```
Build out the first day content, documentation only 

https://github.com/School-of-Computing-and-Informatics/cmps-fa26-first-example
```

## 4. Draft the Moodle repository description

An expanded Moodle blurb was requested to explain the live specification-to-implementation exercise, the incremental AI-assisted workflow, and the option for students to follow along or revisit the repository later.

**Actual Requests:**
``````
Suggest an extended blurb in html for the moodle page that looks to the repo. Here my original version

```
This GitHub repository contains the Day 1 spec-to-implementation example for CMPS 357. The implementation will
be completed live in class, with students encouraged to follow along on their own machines or take notes to
revisit the exercise later.
```
``````

## 5. Return the Moodle text as plain paragraphs

The Moodle blurb was revised to contain only paragraph text without HTML tags.

**Actual Requests:**
```
Just give the pars no tags
```

## 6. Add empty Java starter files

An empty main driver and empty domain-class files were requested. `Main.java`, `Supply.java`, and `Workshop.java` were added as compiling shells, and the documentation was revised so that the entire model and driver are constructed live from the specification.

**Actual Requests:**
```
Add an empty main driver, and empty class files
```

## 7. Add multi-IDE project configuration

Project files were requested for VS Code, IntelliJ IDEA, and at least two additional IDEs. Support was added for:

- VS Code using explicit `javac` and `java` CLI tasks;
- IntelliJ IDEA using a module and shared run configuration;
- Eclipse using project, classpath, Java 17, encoding, and launch files; and
- NetBeans using a free-form Ant project.

All configurations use `src` as the source root, `out` as the generated-output directory, Java 17, and `Main` as the entry point.

**Actual Requests:**
```
Add project files for vscode (using a Java cli task), intellij, and at least two others
```

## 8. Attempt IDE execution in virtual machines

The configurations were requested to be opened and run in separate IDE virtual machines. The available workspace did not provide VM or desktop control and did not have the four IDEs installed, so full GUI import-and-run testing was not possible. The available validation instead confirmed JSON and XML syntax, checked cross-file references, compiled all Java sources with the installed Java 17 compiler module, and ran the empty `Main` successfully with no output.

**Actual Requests:**
```
Start a vm with each ide, try to run the configuration 
```

## 9. Evaluate Maven and Gradle

The value of adding Maven or Gradle was considered. The recommendation was to omit both from this Day 1 example because the project has only three source files, no packages, no dependencies, and no tests. Adding either alongside direct Java tasks and the NetBeans Ant file would create competing build paths. If a build tool becomes useful later, one tool—preferably Maven unless Gradle is a course objective—should replace redundant build metadata rather than being added beside it.

**Actual Requests:**
```
Would maven and gradle be good additions or would they interfere or add unnecessary complexity?
```

## 10. Preserve this development summary

The final request was to add this transcript summary and link it to the shared ChatGPT conversation so the design history and major decisions remain discoverable from the repository.

**Actual Requests:**
```
Add a `docs/TRANSCRIPT.md` with a summary of each request in this chat and a link to it:
https://chatgpt.com/share/e/6a887630-c4ac-8012-a768-a2fb96842b85?ogimg=plain
```

## 11. Document the repository structure

An annotated repository tree was requested for the README. The diagram was expanded to cover every committed folder and file and to explain the purpose of the Java sources, documentation, and four IDE configurations.

**Actual Requests:**
```
Add a repo structure diagram of the files and folders, with comments explaining each
```

## 12. Create a Kindle-ready documentation compilation

A single HTML file was requested containing every Markdown file in the repository. The generated document begins with a linked table of contents, assigns a stable anchor to each source file, and uses the Kindle `<mbp:pagebreak />` tag after the table of contents and after every included file.

**Actual Requests:**
```
Create an HTML file suitable for Kindle that has every markdown file in the repo crrently. include a table of contents
at the beginning with links using anchor tags to the individual files later on. Use the next page. HTML tag at the end
of each file and at the end of the table of contents
```

## 13. Add a technical terminology refresher

The README's opening was simplified for students who have completed three software-development courses but may need a refresher. A technical reference was added for Java, Git, IDE, build, and AI-development terminology, and student-facing documents now deep-link technical terms to the relevant definitions instead of removing the terms from the course materials.

**Actual Requests:**
```
Identify sw dev jargon on pr #1, primarily readme but give others a pass as well. 
...
They have 3 courses in swdev, but might need a refresher. Simplify the opening two paragraphs of readme:
`This repository...The example uses... `
but don't remove items later. Instead, create a technical reference file and deep-link into it where jargon is found. 
```

## 14. Simplify technical-reference links in the Kindle edition

After the regenerated HTML displayed as source text on a Kindle device, the previous and current document structures were compared. The Markdown documents retain their technical-reference deep links, while the combined Kindle HTML presents those terms as ordinary text and keeps only its navigation links, reducing the converter-facing link structure.

**Actual Requests:**
```
Check the kindle.html structure. Opening in browser displays correctly, but opening in kindle shows html.
See attached. The previous version didn't do this. 
```

## 15. Plan, test, and track the staged implementation

A second [shared ChatGPT conversation](https://chatgpt.com/share/6a90b9d5-98f0-83ea-adb8-eb90f05c6d6f) expanded the README's suggested implementation order into a detailed, trackable development plan.

The following work was completed:

- added `docs/STAGES.md` with eight sequential implementation stages;
- expanded each stage with its relevant specification sections, implementation boundaries, verification checklist, testing regime, and suggested commit message;
- documented a shared incremental testing approach, including local assertion checks, exact-output comparison, regression testing, and the requirement that pull requests targeting `main` run automated tests before merge;
- checked the current `main` branch and recorded that `Supply.java` and `Workshop.java` remain empty classes, `Main.main` remains empty, and no committed test suite or GitHub Actions workflow is currently present;
- added a status section to every stage, with all eight marked not started on `main`;
- created one GitHub issue for each implementation stage:
  - [Stage 1: Build the Supply model](https://github.com/School-of-Computing-and-Informatics/cmps-357-fa26-first-example/issues/8);
  - [Stage 2: Build the Workshop structure](https://github.com/School-of-Computing-and-Informatics/cmps-357-fa26-first-example/issues/5);
  - [Stage 3: Implement addSupply](https://github.com/School-of-Computing-and-Informatics/cmps-357-fa26-first-example/issues/4);
  - [Stage 4: Implement totalSupplyCount](https://github.com/School-of-Computing-and-Informatics/cmps-357-fa26-first-example/issues/9);
  - [Stage 5: Implement scaleToAttendees](https://github.com/School-of-Computing-and-Informatics/cmps-357-fa26-first-example/issues/6);
  - [Stage 6: Add the amount-formatting helper](https://github.com/School-of-Computing-and-Informatics/cmps-357-fa26-first-example/issues/7);
  - [Stage 7: Implement Workshop string representations](https://github.com/School-of-Computing-and-Informatics/cmps-357-fa26-first-example/issues/11); and
  - [Stage 8: Complete the Main example sequence](https://github.com/School-of-Computing-and-Informatics/cmps-357-fa26-first-example/issues/10).
- created [Epic #12: Implement the Workshop Supply Planner](https://github.com/School-of-Computing-and-Informatics/cmps-357-fa26-first-example/issues/12), with an ordered checklist linking all eight stage issues and shared completion criteria.

**Actual Requests:**
```
For https://github.com/School-of-Computing-and-Informatics/cmps-357-fa26-first-example:

- See the Suggested implementation order in the README and give a STAGES.md file documenting the work order and expanding on the implementation details needed for each step

Move the STAGES file to the docs directory. Add a testing regime for each stage in the STAGES file.

Add in the Testing approach that tests will be run automatically in a pull request that merges onto main.

Update the stages file based on the current repo status in main.

Include status sections in each stage.

Create issues for each stage and an epic tracking the stages.

Update the TRANSCRIPT.md with the link to this conversation and a description of the tasks completed.
```

