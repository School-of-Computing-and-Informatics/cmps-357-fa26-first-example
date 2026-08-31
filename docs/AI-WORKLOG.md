# AI Work Log

Use this document to record the important AI-assisted steps taken during the exercise. Ordinary IDE completion does not need to be recorded.

The purpose is not to preserve every message. Record the interactions that influenced the implementation or exposed a useful mistake.

## Interaction 1

### Goal

Implement the Stage 1 `Supply` model so it matches the specification with the required fields, constructor, and accessors only.

### Context supplied

- `docs/STAGES.md` — Stage 1: build the `Supply` model
- `docs/SPEC.md` — relevant “Workshop model” and “Required `Supply` API” sections
- `src/Supply.java` — originally empty class
- The requirement to avoid adding any behavior beyond the Stage 1 model

### Prompt

Implement only `Supply.java` for Stage 1. Add a final `String` field named `name`, a mutable `double` field named `amount`, the two-argument constructor, `getName()`, `getAmount()`, and `setAmount(double amount)`. Do not add any validation, formatting, or extra behavior.

### Result

The assistant created a minimal `Supply` class with:
- `private final String name;`
- `private double amount;`
- constructor assigning both values
- `getName()` returning the stored name
- `getAmount()` returning the stored amount
- `setAmount(double amount)` updating the amount

### Review

Checked that the implementation matches the Stage 1 requirements:
- `name` is `final` and has no setter
- `amount` is mutable via setter and getter
- the constructor initializes both fields from the supplied arguments
- no extra behavior was added beyond the required API

### Verification

Stage 1 testing requirements before accepting the implementation:

- Compile the project successfully.
- Construct a `Supply` with a non-example name and amount such as `"notebooks"` and `2.5`.
- Confirm `getName()` returns `"notebooks"`.
- Confirm `getAmount()` returns `2.5`.
- Call `setAmount(4.75)` and confirm `getAmount()` returns `4.75`.
- Confirm `getName()` remains unchanged after the mutation.
- Confirm `name` is `final` and there is no `setName` method.
- Run the stage-aware verification suite and confirm it reports `PASS Stage 1: Supply model`.

### Follow-up or correction

No additional refinement was needed beyond confirming the implementation stayed limited to the Stage 1 API.

---

## Interaction 2

### Goal


### Context supplied


### Prompt


### Result


### Review


### Verification


### Follow-up or correction


---

## Final reflection

Answer briefly after completing the exercise:

1. Which part of the specification was most important to include in the AI assistant's context?
2. Identify one generated detail that required human review or correction.
3. What evidence supports the claim that the final implementation is correct?
4. When would restarting or reframing the conversation have been preferable to continuing it?

