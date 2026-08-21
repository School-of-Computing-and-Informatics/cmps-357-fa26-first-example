# Workshop Supply Planner Specification

## Goal

Implement the missing behavior in `Workshop.java` so that running `Main` produces the required output exactly.

Do not modify `Main.java` merely to make its output appear correct. The required behavior belongs in `Workshop.java`.

## Workshop model

A workshop has:

- a title stored as a `String`;
- an attendee count stored as an `int`; and
- a list of supplies stored in the order in which they were added.

Each supply has:

- a name stored as a `String`; and
- an amount stored as a `double`.

## Required methods

Implement these methods in `Workshop.java`:

- `addSupply(String supplyName, double amount)`
- `scaleToAttendees(int newAttendeeCount)`
- `totalSupplyCount()`
- `toString()`
- `toPrettyString()`

Private helper methods may be added when they make the implementation clearer.

## Supply-order rules

- Preserve the order in which supplies are added.
- Adding a supply must not replace or reorder an existing supply.
- Two supplies with the same name remain two separate entries in this exercise.

## `addSupply`

Add one new supply entry to the end of the workshop's supply list.

For example:

```java
workshop.addSupply("index cards", 48);
```

adds a supply named `index cards` with an amount of `48.0`.

## `totalSupplyCount`

Return the number of supply entries in the workshop. Count entries, not the sum of their amounts.

A workshop containing index cards, marker packs, and feedback forms has three supply entries.

## `scaleToAttendees`

Scale every supply amount proportionally for a new attendee count.

If the current attendee count is `a` and the new attendee count is `n`, multiply each amount by:

```text
n / a
```

The calculation must use floating-point division. After scaling the supplies, update the workshop's attendee count.

If `newAttendeeCount` is less than or equal to zero, throw an `IllegalArgumentException`. An invalid request must not partially modify the workshop.

## Amount formatting

When displaying an amount:

- display an integer value without a decimal point;
- otherwise display no more than two digits after the decimal point;
- round to the nearest hundredth when necessary;
- remove unnecessary trailing zeros; and
- do not use scientific notation.

| Stored amount | Displayed amount |
|---:|---:|
| `48.0` | `48` |
| `4.5` | `4.5` |
| `1.125` | `1.13` |
| `3.50` | `3.5` |
| `0.75` | `0.75` |

## `toString`

Return a multiline string in this format:

```text
<title> (<attendees> attendees)
- <amount> <supply name>
- <amount> <supply name>
```

Do not add a blank line at the beginning or end of the returned string. `toString()` must return the formatted value; it must not print directly to the console.

## `toPrettyString`

Return a user-friendly multiline representation of the workshop. For this first exercise, `toPrettyString()` returns the same content as `toString()`.

## Required output

Running `Main` must produce exactly:

```text
AI-Assisted Prototyping Workshop (24 attendees)
- 48 index cards
- 6 marker packs
- 3 rolls of painter's tape
- 0.75 reams of printer paper
- 24 feedback forms

Supply entries: 5

After scaling to 36 attendees:

AI-Assisted Prototyping Workshop (36 attendees)
- 72 index cards
- 9 marker packs
- 4.5 rolls of painter's tape
- 1.13 reams of printer paper
- 36 feedback forms
```

The printer-paper amount scales from `0.75` to `1.125`, which displays as `1.13` under the formatting rules.

## Constraints

- Do not change the example data in `Main`.
- Do not hard-code the required output.
- Do not use a separate condition for each example supply.
- Do not add external libraries.
- Do not expose the mutable internal supply list.
- Preserve supply insertion order.

