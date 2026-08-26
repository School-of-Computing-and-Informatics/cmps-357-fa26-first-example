# Day 1 Instructor Guide

## Purpose

This example introduces the course's basic development loop:

> Read the specification, provide focused context, request one change, inspect the result, verify it, and commit it.

The goal is not to demonstrate how quickly an AI assistant can generate an entire program. The goal is to make evidence-based use of generated code visible from the first class meeting.

## Preparation

Before class:

- confirm the three empty Java starter files match `STARTER-CODE-PLAN.md`;
- verify the repository from a clean clone;
- confirm the project runs under Java 17 or later;
- retain the completed solution outside the student-visible repository;
- confirm that the final output matches `SPEC.md` exactly; and
- decide whether students will work individually or in pairs.

## Suggested 75-minute sequence

| Time | Activity | Teaching focus |
|---:|---|---|
| 0–10 min | Introduce the repository, clone it, and run the empty `Main` | A compiling program can still have no implemented behavior. |
| 10–20 min | Read `SPEC.md` as a class | The specification, not the model response, defines correctness. |
| 20–32 min | Build `Supply` and the basic `Workshop` structure | Translate nouns and required APIs from the specification into code. |
| 32–44 min | Implement `addSupply` and `totalSupplyCount` | Start with small, low-risk behaviors and inspect the diff. |
| 44–57 min | Implement `scaleToAttendees` | Supply the formula and invalid-input rule as focused context. |
| 57–67 min | Implement formatting and string output | Exact output exposes rounding, trailing-zero, and newline errors. |
| 67–73 min | Complete `Main`, run it, and compare output | Verification must produce evidence, not confidence alone. |
| 73–75 min | Complete the final reflection | Name what context mattered and what required correction. |

## Recommended live prompts

Prompts should be demonstrated as patterns, not as magic wording.

### Model prompt

> Read the model and required API sections of `docs/SPEC.md`. Implement only the `Supply` class and the basic `Workshop` fields, constructor, and accessors. Do not implement scaling or formatting yet. Explain how each addition maps to the specification.

### First behavior prompt

> Read the `addSupply` and `totalSupplyCount` sections of `docs/SPEC.md`. Implement only those behaviors in `Workshop`. Preserve insertion order and do not change `Main`. Explain the change briefly.

### Scaling prompt

> Implement only `scaleToAttendees` according to the supplied specification. Pay particular attention to floating-point division and the requirement that an invalid attendee count must not partially modify the workshop. Show me what I should verify after the change.

### Formatting prompt

> The output must follow the amount-formatting and `toString` sections exactly. Propose a small private formatting helper and implement `toString` using it. Do not print from `toString` and do not modify `Main`.

## Questions to ask during review

- Which part of the prompt came from the specification?
- Did the assistant change anything outside the requested method?
- Is the attendee ratio calculated using floating-point division?
- Does validation occur before any state is modified?
- Is supply insertion order preserved?
- Does `toString()` return a value or produce a side effect?
- How do we know `1.125` becomes `1.13`?
- Are trailing zeros and final newlines correct?
- Would the implementation work for supplies not present in the example?

## Likely failure modes

| Failure | Observable symptom | Useful response |
|---|---|---|
| Integer division | Amounts become zero or remain unchanged for some scales | Revisit the formula and operand types. |
| Validation after mutation | Invalid scaling leaves some amounts changed | Move validation before all state changes. |
| Hard-coded output | Example works, but new workshops fail | Ask whether behavior derives from stored data. |
| Printing inside `toString` | Duplicate output or an empty returned string | Reinforce return values versus side effects. |
| Uncontrolled decimal formatting | `48.0`, `1.125`, or locale-specific output appears | Compare the formatting helper with every rule. |
| Over-broad AI edit | Unrelated files or APIs change | Revert the unrelated portion and narrow the prompt. |
| One giant commit | The development path cannot be reviewed | Commit after each independently verified behavior. |

## Minimum completion target

By the end of class, students should have:

- complete `Supply` and `Workshop` models;
- a working implementation of all required `Workshop` methods;
- a completed example driver in `Main`;
- output matching `SPEC.md`;
- several small commits;
- at least two meaningful AI work-log entries; and
- a short final reflection identifying both useful context and a verification step.

If time runs short, prioritize correct scaling and formatting over adding any new feature. Do not extend the domain during Day 1.
