# Implementation Stages

This guide expands the **Suggested implementation order** in the README into a sequence of small, reviewable changes. Use `docs/SPEC.md` as the source of truth throughout. If this guide, an AI response, and the specification disagree, follow the specification.

For every stage:

1. give the AI assistant only the files and specification sections relevant to the current task;
2. request only the current stage;
3. inspect the diff before running the program;
4. compile and run, then perform the stage-specific checks below;
5. record useful prompts, decisions, and verification evidence in `docs/AI-WORKLOG.md`; and
6. commit the stage once its behavior is understandable and verified.

Do not ask an AI assistant to complete all eight stages at once. Later stages depend on earlier ones, and small changes make incorrect assumptions easier to find and reverse.

## Current status on `main`

This status was checked against `main` at commit `5173c9d` (`Add the Day 1 workshop example starter (#1)`):

- `src/Supply.java` contains only an empty class declaration;
- `src/Workshop.java` contains only an empty class declaration;
- `src/Main.java` contains an empty `main` method;
- the Ant and IDE scaffolding can compile and run the empty starter;
- no stage implementation has begun; and
- no committed test suite or GitHub Actions workflow is present.

Therefore, every implementation stage below is marked **Not started on `main`**. These labels describe the shared `main` branch, not work that may exist in a student's branch or pull request. Update the labels if implementation is later merged into `main`.

## Testing approach

When the implementation is submitted in a pull request targeting `main`, the stage tests will be run automatically as part of the pull-request checks. The pull request must not be merged until those automated checks pass.

At the status point recorded above, `main` does not yet contain the committed test suite or GitHub Actions workflow that supplies those checks. That automation must be added or configured before the implementation pull request is treated as merge-ready. Until an automated test check is visible on the pull request, local results are the only available test evidence.

Local testing during each stage remains necessary even after automation is available because it gives faster feedback and isolates failures before the complete pull-request suite runs.

Testing is incremental: each stage first recompiles all production classes, then exercises only the behavior introduced so far. Use a short-lived `StageCheck.java` in the repository root when a stage needs executable assertions. Keep it outside `src/`, compile it together with the production sources, and delete it before committing:

```bash
javac -d out src/*.java StageCheck.java
java -ea -cp out StageCheck
```

Use Java's `assert` statements or explicit checks that throw `AssertionError`; the `-ea` flag enables assertions. Each check should construct fresh objects so one case cannot accidentally affect another. A passing run should exit with no assertion failure. These stage checks are disposable verification scaffolding, not part of the submitted implementation. Do not commit `StageCheck.java`, generated `.class` files, or `out/`.

The formatting helper in Stage 6 is private, so that stage uses compilation and focused code review. Its observable formatting behavior is tested through the public string API immediately in Stage 7. Do not weaken the helper's visibility merely to test it.

## Before Stage 1: establish the baseline

Read `docs/SPEC.md` completely, then inspect the three files in `src/`. Confirm that `Supply.java` and `Workshop.java` contain empty class declarations and that `Main.java` contains an empty `main` method. Run `Main` once. The project should compile successfully and produce no output.

If the baseline does not compile, resolve the project or IDE setup problem before implementing domain behavior. See `docs/IDE-SETUP.md` or `GETTING_STARTED_INTELLIJ.md` as appropriate.

## Stage 1: build the `Supply` model

**Relevant specification:** “Workshop model” and “Required `Supply` API.”

**Status on `main`: Not started.** `src/Supply.java` is an empty class. None of the required fields, constructor, or accessors exists, and no automated test currently covers this API.

Implement only `Supply.java`:

- add a final `String` field named `name`;
- add a mutable `double` field named `amount`;
- add the required two-argument constructor;
- initialize both fields from the constructor parameters;
- add `getName()` and `getAmount()`; and
- add `setAmount(double amount)`.

The name is immutable after construction, so do not add `setName`. Do not add formatting, scaling, validation, collection behavior, or console output in this stage. The model must store the supplied values rather than substituting example-specific values.

**Verify before committing:**

- the project compiles;
- both constructor arguments are assigned to the intended fields;
- `name` cannot be reassigned after construction;
- `amount` can be read and changed through the required methods; and
- no files outside the intended stage changed unexpectedly.

**Testing regime:**

1. Recompile all files in `src/`.
2. In a temporary `StageCheck.java`, construct a supply with a non-example name and amount, such as `"notebooks"` and `2.5`.
3. Assert that both getters return the constructor values.
4. Call `setAmount(4.75)` and assert that `getAmount()` now returns `4.75` while `getName()` is unchanged.
5. Confirm by code inspection that `name` is `final` and that no name setter exists.
6. Run with assertions enabled, then delete the temporary check.

**Suggested commit:** `Add Supply data model`

## Stage 2: build the basic `Workshop` structure

**Relevant specification:** “Workshop model” and “Required `Workshop` structure.”

**Status on `main`: Not started.** `src/Workshop.java` is an empty class. The workshop fields, constructor, accessors, internal list, and defensive-copy behavior do not yet exist, and no automated test currently covers them.

Implement the state and basic API in `Workshop.java`:

- import `List` and a suitable insertion-ordered list implementation;
- add the final `String title` field;
- add the mutable `int attendees` field;
- add the final `List<Supply> supplies` field;
- add the required constructor;
- initialize the title and attendee count from the constructor parameters;
- initialize the internal supply list as empty; and
- add `getTitle()`, `getAttendees()`, and `getSupplies()`.

`getSupplies()` must return a defensive copy. A caller may modify the returned list without changing the workshop's internal list. Preserve insertion order by using a list; do not use a set or sort the values. The individual `Supply` objects remain the model objects used by the workshop—the requirement at this stage is to protect the internal list itself.

Do not implement the five required workshop behaviors yet.

**Verify before committing:**

- the project compiles;
- a new workshop has the supplied title and attendee count;
- a new workshop begins with no supplies;
- clearing or adding to the list returned by `getSupplies()` does not alter a later result from `getSupplies()`; and
- the fields that should retain their object identity are marked `final`.

**Testing regime:**

1. Recompile all files in `src/`.
2. Construct a workshop with a non-example title and attendee count.
3. Assert that `getTitle()` and `getAttendees()` return those values and that `getSupplies()` is empty.
4. Save the returned list, modify that copy, and assert that a fresh call to `getSupplies()` is still empty. This is the defensive-copy test.
5. Confirm by inspection that `title` and the internal list reference are `final`.
6. Run with assertions enabled, then delete the temporary check.

**Suggested commit:** `Add Workshop data structure`

## Stage 3: implement `addSupply`

**Relevant specification:** “Supply-order rules” and “`addSupply`.”

**Status on `main`: Not started.** Because `Workshop` is empty, `addSupply` and its insertion-order and duplicate-entry behavior are absent. No automated test currently covers this method.

Add `addSupply(String supplyName, double amount)` to `Workshop`. Each call must:

- construct a new `Supply` from the two arguments; and
- append it to the end of the internal list.

Do not search for an existing name, merge entries, replace an entry, sort the list, or add a separate condition for any example supply. Two calls with the same name must create two distinct entries.

**Verify before committing:**

- adding one item increases the returned list's size by one;
- multiple items appear in the order added;
- duplicate names remain separate entries; and
- the stored names and amounts match the arguments.

A small temporary check may be useful, but do not replace the required `Main` sequence before Stage 8. Remove disposable checks before committing unless they belong in a separate test artifact approved for the exercise.

**Testing regime:**

1. Recompile all files in `src/`.
2. Construct a workshop and add three supplies, including two with the same name but different amounts.
3. Retrieve the defensive copy and assert that its size is three.
4. Assert that each position contains the expected name and amount in insertion order.
5. Assert that the duplicate-name entries remain separate objects and separate list entries.
6. Modify or clear the returned list and assert that the workshop still reports all three entries through a fresh copy.
7. Run with assertions enabled, then delete the temporary check.

**Suggested commit:** `Implement supply addition`

## Stage 4: implement `totalSupplyCount`

**Relevant specification:** “`totalSupplyCount`.”

**Status on `main`: Not started.** `totalSupplyCount` is absent from the empty `Workshop` class. No automated test currently distinguishes entry count from quantity totals or distinct names.

Add `totalSupplyCount()` to `Workshop`. Return the number of entries in the supply list. This is not the sum of their numeric amounts and does not count distinct names.

This method should derive its result from current workshop state, so it stays correct after every call to `addSupply`. Do not maintain a separate counter that can become inconsistent with the list.

**Verify before committing:**

- an empty workshop returns `0`;
- a workshop with three added entries returns `3`;
- two entries with the same name are counted twice; and
- large supply amounts do not affect the result.

**Testing regime:**

1. Recompile all files in `src/`.
2. Assert that a new workshop reports `0`.
3. Add entries one at a time and assert counts of `1`, `2`, and `3`.
4. Use duplicate names and very large or fractional amounts to confirm that the method counts entries rather than names or quantities.
5. Run with assertions enabled, then delete the temporary check.

**Suggested commit:** `Count workshop supply entries`

## Stage 5: implement `scaleToAttendees`

**Relevant specification:** “`scaleToAttendees`.”

**Status on `main`: Not started.** `scaleToAttendees` is absent. Floating-point scaling, attendee updates, invalid-input validation, and atomicity are not implemented or covered by automated tests.

Add `scaleToAttendees(int newAttendeeCount)` to `Workshop`. The method has two distinct phases.

First, validate `newAttendeeCount`. If it is zero or negative, throw `IllegalArgumentException` before changing any supply amount or the attendee field. This ordering guarantees that an invalid request cannot partially modify the workshop.

Second, for a valid count:

1. compute the scale factor from the new count and the current attendee count using floating-point division;
2. multiply every stored supply amount by that same factor through the `Supply` API; and
3. update `attendees` only after the amounts have been scaled.

Compute the factor before changing the attendee field; otherwise the old attendee count needed by the formula is lost. Iterate over the stored entries so the behavior works for arbitrary supplies, including duplicate names. Do not round stored amounts during scaling—rounding belongs only to display formatting.

**Verify before committing:**

- scaling from 24 to 36 uses a factor of `1.5`;
- `48` becomes `72`, `3` becomes `4.5`, and `0.75` is stored as `1.125`;
- the workshop attendee count becomes `36`;
- supply order and entry count are unchanged;
- a zero or negative target throws `IllegalArgumentException`; and
- after an invalid request, attendee count and every amount remain unchanged.

Review the division expression carefully. If both operands are treated as integers, the method will silently produce incorrect scale factors for many inputs.

**Testing regime:**

1. Recompile all files in `src/`.
2. Create a 24-attendee workshop with amounts `48`, `3`, and `0.75`; scale to 36.
3. Assert an attendee count of `36`, unchanged supply order and count, and stored amounts of `72`, `4.5`, and `1.125`. For these specified values, direct `double` comparisons are sufficient.
4. Create a separate workshop and scale from a larger attendee count to a smaller one, such as 10 to 5, to catch integer-division errors in the opposite direction.
5. For invalid-input atomicity, snapshot the attendee count and all amounts, call the method with `0` and then a negative value, and assert that each call throws `IllegalArgumentException`.
6. After each exception, assert that the snapshot still matches the workshop exactly.
7. Run with assertions enabled, then delete the temporary check.

**Suggested commit:** `Scale supplies with attendee count`

## Stage 6: add a private amount-formatting helper

**Relevant specification:** “Amount formatting.”

**Status on `main`: Not started.** The empty `Workshop` class has no private amount-formatting helper. Rounding, trailing-zero removal, locale independence, and avoidance of scientific notation are not implemented or covered by automated tests.

Add a private helper in `Workshop` that converts one stored `double` amount into the required display text. Keep it private because it supports workshop presentation rather than adding to the public domain API.

The helper must:

- omit the decimal point for whole-number values;
- show at most two digits after the decimal point;
- round to the nearest hundredth when necessary;
- remove unnecessary trailing zeros; and
- avoid scientific notation.

Use only the Java standard library. Choose a formatting approach whose decimal separator is stable for the required output; output must not change with a machine's locale. Avoid converting the amount to an integer before formatting because that would discard meaningful fractions.

**Verify the helper against every specification example:**

| Stored amount | Required text |
|---:|---:|
| `48.0` | `48` |
| `4.5` | `4.5` |
| `1.125` | `1.13` |
| `3.50` | `3.5` |
| `0.75` | `0.75` |

Also inspect the chosen formatter for scientific-notation and locale risks. Do not add printing to the helper.

**Testing regime:**

1. Recompile all files in `src/`; this catches signature, import, and syntax problems.
2. Review the helper against the five input/output pairs in the specification and trace each branch or formatter rule by hand.
3. Confirm that the implementation uses a locale-independent decimal point, caps output at two fractional digits, removes trailing zeros, and cannot switch to scientific notation.
4. Confirm that the helper returns text and performs no console output.
5. Record the five expected pairs in the work log. They become executable public-API tests in Stage 7.
6. Do not make the helper public or package-private and do not use reflection solely to test it.

**Suggested commit:** `Format supply amounts for display`

## Stage 7: implement `toString` and `toPrettyString`

**Relevant specification:** “`toString`” and “`toPrettyString`.”

**Status on `main`: Not started.** `Workshop` does not override `toString()` or define `toPrettyString()`. Required multiline output and formatting-helper integration are not implemented or covered by automated tests.

Implement `Workshop.toString()` by deriving the complete multiline value from the workshop's current fields and supply list:

1. start with `<title> (<attendees> attendees)`;
2. append one line for each supply in insertion order;
3. begin each supply line with `- `;
4. format its amount through the Stage 6 helper; and
5. append a space and the supply name.

Return the constructed string. Do not print from `toString()`, hard-code the example workshop, or add separate logic for particular supply names. There must be no blank line at the beginning or end of the returned value. Take care not to leave a final newline after the last supply line.

Implement `toPrettyString()` by returning the same content as `toString()` for this exercise. Reuse the existing behavior rather than duplicating the formatting algorithm.

**Verify before committing:**

- an arbitrary workshop title and attendee count appear from stored state;
- supplies appear in insertion order;
- whole, fractional, and rounded amounts use the helper;
- `toString()` has no console side effects;
- the returned text has no leading blank line, trailing blank line, or trailing newline; and
- `toPrettyString()` equals `toString()`.

**Testing regime:**

1. Recompile all files in `src/`.
2. Construct a workshop whose supplies produce all five formatting cases: `48.0`, `4.5`, `1.125`, `3.50`, and `0.75`.
3. Build the complete expected multiline string literally in `StageCheck.java` and assert exact equality with `toString()`. This turns the deferred Stage 6 cases into observable tests.
4. Assert that `toPrettyString()` exactly equals `toString()`.
5. Assert that the result starts with the title rather than a newline, does not end with `\n` or `\r`, and contains supplies in insertion order.
6. Capture or carefully observe standard output while calling `toString()`; the call itself must print nothing.
7. Repeat with an empty workshop to establish the expected header-only behavior implied by the general format.
8. Run with assertions enabled, then delete the temporary check.

**Suggested commit:** `Add workshop string representations`

## Stage 8: complete the example sequence in `Main`

**Relevant specification:** “Required `Main` driver” and “Required output.”

**Status on `main`: Not started.** `Main.main` is empty, so it compiles and produces no output. The required example sequence and character-for-character output check are not implemented or automated.

Complete only the driver in `Main.java`. It should orchestrate the already implemented domain API:

1. construct the specified workshop for 24 attendees;
2. add all five specified supplies in the exact order given;
3. display the workshop;
4. display the supply-entry count;
5. scale the existing workshop to 36 attendees; and
6. display the scaled workshop.

Use the exact labels, punctuation, blank lines, and ordering shown in the required output. `Main` may print returned values, but it must not recreate workshop formatting, manually calculate scaled amounts, or hard-code the complete output. Scaling must happen through `scaleToAttendees`, counting through `totalSupplyCount`, and workshop presentation through the string API.

**Verify before committing:**

- the project compiles and runs under Java 17 or later;
- the initial workshop contains exactly five entries in the specified order;
- the same workshop object shows 36 attendees after scaling;
- printer paper displays as `1.13`, demonstrating that `1.125` was rounded only for display;
- the output contains exactly the specified blank lines; and
- the entire output matches the `docs/SPEC.md` block character-for-character.

A visual comparison is useful, but an exact text diff provides stronger evidence. Check for invisible differences such as trailing spaces, extra newlines, locale-specific decimal commas, or a final blank line.

**Testing regime:**

1. Perform a clean compilation of `src/*.java`.
2. Run `Main` and redirect its output to a temporary file.
3. Create a temporary expected-output file by copying only the required-output block from `docs/SPEC.md`, preserving blank lines and punctuation.
4. Compare the files with an exact diff, for example:

   ```bash
   java -cp out Main > actual.txt
   diff -u expected.txt actual.txt
   ```

5. A passing diff produces no differences. If it fails, inspect spaces, apostrophes, decimal points, blank lines, and the final newline rather than changing domain behavior blindly.
6. Re-run the Stage 5 invalid-input and atomicity checks as a regression test.
7. Delete `actual.txt`, `expected.txt`, any `StageCheck.java`, and generated output before committing.

**Suggested commit:** `Complete workshop planner example`

## Final review

Before considering the exercise complete:

- run the program from a clean build;
- compare its complete output with `docs/SPEC.md`;
- inspect the full Git diff and confirm no IDE output or `.class` files were added;
- confirm domain behavior remains in `Supply` and `Workshop`, not `Main`;
- confirm the internal supply list is not exposed;
- confirm invalid scaling cannot mutate state;
- confirm no behavior is specific to the five example supplies;
- review the commit history for small, meaningful changes; and
- finish the reflection in `docs/AI-WORKLOG.md`.

The finished program is important, but the evidence trail matters too: focused prompts, reviewed diffs, stage-specific verification, and understandable commits are part of the exercise's intended workflow.
