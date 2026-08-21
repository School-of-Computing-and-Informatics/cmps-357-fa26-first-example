# Starter Code Plan

This document defines the Java files that should be present before students begin the Day 1 exercise. It is an implementation plan, not a completed solution.

## Intended repository structure

```text
cmps-fa26-first-example/
├── .gitignore
├── README.md
├── docs/
│   ├── SPEC.md
│   ├── AI-WORKLOG.md
│   ├── STARTER-CODE-PLAN.md
│   └── INSTRUCTOR-GUIDE.md
└── src/
    ├── Main.java
    ├── Supply.java
    └── Workshop.java
```

## `Supply.java`

Provide this class fully implemented. It represents a single supply entry and contains:

- a final `String name` field;
- a mutable `double amount` field;
- a constructor accepting the name and amount;
- `getName()`;
- `getAmount()`; and
- `setAmount(double amount)`.

Students should not need to modify `Supply.java` on Day 1. Completing it in advance keeps the exercise focused on one primary class.

## `Main.java`

Provide this class fully implemented. It must:

1. Create `AI-Assisted Prototyping Workshop` for 24 attendees.
2. Add these supplies in order:
   - 48 index cards;
   - 6 marker packs;
   - 3 rolls of painter's tape;
   - 0.75 reams of printer paper; and
   - 24 feedback forms.
3. Display the workshop.
4. Display the supply-entry count.
5. Scale the workshop to 36 attendees.
6. Display the scaled workshop.

`Main.java` must not contain TODO comments. Students should be told not to edit its example data.

## `Workshop.java`

Provide the following completed structure:

- a final `String title` field;
- an `int attendees` field;
- a final `List<Supply> supplies` field;
- a constructor that initializes all fields;
- `getTitle()`;
- `getAttendees()`; and
- `getSupplies()`, returning a defensive copy.

Provide compiling placeholders with TODO comments for:

- `addSupply(String supplyName, double amount)`;
- `totalSupplyCount()`;
- `scaleToAttendees(int newAttendeeCount)`;
- `toString()`; and
- `toPrettyString()`.

The initial project must compile and run. Placeholder methods may return neutral values, so the initial output should be incomplete or incorrect without crashing.

## What not to include initially

- a completed `Workshop` implementation;
- external dependencies or a build framework not needed for three Java files;
- collection, search, sorting, persistence, or UI stages;
- an answer key on a visible branch;
- generated IDE output, `.class` files, or an `out/` directory; or
- tests that reveal the complete implementation strategy.

## Pre-class acceptance checklist

- A clean clone opens without missing source files.
- The configured Java version is documented and available in the classroom.
- `Main` compiles and runs before any TODO is implemented.
- Initial output is visibly incomplete but does not throw an unexpected exception.
- Every TODO maps directly to a section of `docs/SPEC.md`.
- The example data produces integer, one-decimal, and rounded two-decimal output.
- The completed instructor version matches the required output exactly.
- The instructor solution is stored privately outside the student-visible repository.

