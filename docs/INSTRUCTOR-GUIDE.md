# Day 1 Instructor Guide

## Purpose

This example introduces the course's basic development loop:

> Read the specification, provide focused context, request one change, inspect the result, verify it, and commit it.

The goal is not to demonstrate how quickly an AI assistant can generate an entire program. The goal is to make evidence-based use of generated code visible from the first class meeting.

## Preparation

Before class:

- add the Java starter files described in `STARTER-CODE-PLAN.md`;
- verify the repository from a clean clone;
- confirm the project runs under Java 17 or later;
- retain the completed solution outside the student-visible repository;
- confirm that the final output matches `SPEC.md` exactly; and
- decide whether students will work individually or in pairs.

## Suggested 75-minute sequence

| Time | Activity | Teaching focus |
|---:|---|---|
| 0–10 min | Introduce the repository, clone it, and run `Main` | A compiling program can still be behaviorally incomplete. |
| 10–20 min | Read `SPEC.md` as a class | The specification, not the model response, defines correctness. |
| 20–30 min | Implement `addSupply` and `totalSupplyCount` | Start with small, low-risk behaviors and inspect the diff. |
| 30–48 min | Implement `scaleToAttendees` | Supply the formula and invalid-input rule as focused context. |
| 48–63 min | Implement formatting and `toString` | Exact output exposes rounding, trailing-zero, and newline errors. |
| 63–69 min | Implement `toPrettyString` and run the complete example | Prefer reuse over duplicated formatting logic. |
| 69–73 min | Compare output and review commits | Verification must produce evidence, not confidence alone. |
| 73–75 min | Complete the final reflection | Name what context mattered and what required correction. |

## Recommended live prompts

Prompts should be demonstrated as patterns, not as magic wording.

### First focused prompt

> Read the `addSupply` section of `docs/SPEC.md` and the current `Supply.java` and `Workshop.java`. Implement only `addSupply`. Preserve insertion order and do not change other methods. Explain the change briefly.

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

- a working implementation of all required `Workshop` methods;
- output matching `SPEC.md`;
- several small commits;
- at least two meaningful AI work-log entries; and
- a short final reflection identifying both useful context and a verification step.

If time runs short, prioritize correct scaling and formatting over adding any new feature. Do not extend the domain during Day 1.

