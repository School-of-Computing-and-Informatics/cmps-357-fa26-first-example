import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class StageTestRunner {
    private static final Class<?> SUPPLY = Supply.class;
    private static final Class<?> WORKSHOP = Workshop.class;
    private static int completedStages;

    private StageTestRunner() {
    }

    public static void main(String[] args) throws Exception {
        if (!supplyStarted()) {
            require(!workshopStarted() && !mainProducesOutput(),
                    "A later stage has started before Stage 1");
            reportBaseline();
            return;
        }

        testStage1();
        if (!workshopStarted()) {
            require(!hasMethod(WORKSHOP, "addSupply") && !mainProducesOutput(),
                    "A later stage has started before Stage 2");
            report();
            return;
        }

        testStage2();
        if (!hasMethod(WORKSHOP, "addSupply")) {
            require(!hasMethod(WORKSHOP, "totalSupplyCount") && !mainProducesOutput(),
                    "A later stage has started before Stage 3");
            report();
            return;
        }

        testStage3();
        if (!hasMethod(WORKSHOP, "totalSupplyCount")) {
            require(!hasMethod(WORKSHOP, "scaleToAttendees") && !mainProducesOutput(),
                    "A later stage has started before Stage 4");
            report();
            return;
        }

        testStage4();
        if (!hasMethod(WORKSHOP, "scaleToAttendees")) {
            require(findFormattingHelper() == null && !hasDeclaredToString()
                            && !hasMethod(WORKSHOP, "toPrettyString") && !mainProducesOutput(),
                    "A later stage has started before Stage 5");
            report();
            return;
        }

        testStage5();
        Method helper = findFormattingHelper();
        if (helper == null) {
            require(!hasDeclaredToString() && !hasMethod(WORKSHOP, "toPrettyString")
                            && !mainProducesOutput(),
                    "A later stage has started before Stage 6");
            report();
            return;
        }

        testStage6(helper);
        if (!hasDeclaredToString() && !hasMethod(WORKSHOP, "toPrettyString")) {
            require(!mainProducesOutput(), "Stage 8 has started before Stage 7");
            report();
            return;
        }

        testStage7();
        if (!mainProducesOutput()) {
            report();
            return;
        }

        testStage8();
        report();
    }

    private static void testStage1() throws Exception {
        Field name = declaredField(SUPPLY, "name", String.class);
        Field amount = declaredField(SUPPLY, "amount", double.class);
        require(Modifier.isFinal(name.getModifiers()), "Supply.name must be final");
        require(!Modifier.isFinal(amount.getModifiers()), "Supply.amount must be mutable");

        Object supply = supply("notebooks", 2.5);
        requireEqual("notebooks", invoke(supply, "getName"), "getName constructor value");
        requireDouble(2.5, number(invoke(supply, "getAmount")), "getAmount constructor value");
        invoke(supply, "setAmount", new Class<?>[]{double.class}, 4.75);
        requireDouble(4.75, number(invoke(supply, "getAmount")), "setAmount update");
        requireEqual("notebooks", invoke(supply, "getName"), "name after amount update");
        require(!hasMethod(SUPPLY, "setName"), "Supply must not expose setName");
        pass(1, "Supply model");
    }

    private static void testStage2() throws Exception {
        Field title = declaredField(WORKSHOP, "title", String.class);
        Field attendees = declaredField(WORKSHOP, "attendees", int.class);
        Field supplies = declaredField(WORKSHOP, "supplies", List.class);
        require(Modifier.isFinal(title.getModifiers()), "Workshop.title must be final");
        require(!Modifier.isFinal(attendees.getModifiers()), "Workshop.attendees must be mutable");
        require(Modifier.isFinal(supplies.getModifiers()), "Workshop.supplies must be final");

        Object workshop = workshop("Reflection Lab", 10);
        requireEqual("Reflection Lab", invoke(workshop, "getTitle"), "getTitle constructor value");
        requireEqual(10, invoke(workshop, "getAttendees"), "getAttendees constructor value");
        List<?> first = supplies(workshop);
        require(first.isEmpty(), "A new workshop must have no supplies");
        @SuppressWarnings("unchecked")
        List<Object> mutableCopy = (List<Object>) first;
        mutableCopy.add(supply("temporary", 1));
        require(supplies(workshop).isEmpty(), "getSupplies must return a defensive copy");
        pass(2, "Workshop structure");
    }

    private static void testStage3() throws Exception {
        Object workshop = workshop("Order Lab", 12);
        addSupply(workshop, "cards", 2);
        addSupply(workshop, "markers", 3.5);
        addSupply(workshop, "cards", 7);
        List<?> values = supplies(workshop);
        requireEqual(3, values.size(), "addSupply entry count");
        assertSupply(values.get(0), "cards", 2);
        assertSupply(values.get(1), "markers", 3.5);
        assertSupply(values.get(2), "cards", 7);
        require(values.get(0) != values.get(2), "Duplicate names must remain separate objects");
        values.clear();
        requireEqual(3, supplies(workshop).size(), "Defensive copy after additions");
        pass(3, "addSupply");
    }

    private static void testStage4() throws Exception {
        Object workshop = workshop("Count Lab", 8);
        requireEqual(0, invoke(workshop, "totalSupplyCount"), "empty supply count");
        addSupply(workshop, "same", 1000);
        requireEqual(1, invoke(workshop, "totalSupplyCount"), "one supply count");
        addSupply(workshop, "same", 0.25);
        requireEqual(2, invoke(workshop, "totalSupplyCount"), "duplicate-name count");
        addSupply(workshop, "other", 99999);
        requireEqual(3, invoke(workshop, "totalSupplyCount"), "three supply count");
        pass(4, "totalSupplyCount");
    }

    private static void testStage5() throws Exception {
        Object workshop = workshop("Scale Lab", 24);
        addSupply(workshop, "cards", 48);
        addSupply(workshop, "tape", 3);
        addSupply(workshop, "paper", 0.75);
        invoke(workshop, "scaleToAttendees", new Class<?>[]{int.class}, 36);
        requireEqual(36, invoke(workshop, "getAttendees"), "scaled attendee count");
        List<?> scaled = supplies(workshop);
        requireEqual(3, scaled.size(), "scaled entry count");
        assertSupply(scaled.get(0), "cards", 72);
        assertSupply(scaled.get(1), "tape", 4.5);
        assertSupply(scaled.get(2), "paper", 1.125);

        Object down = workshop("Downscale Lab", 10);
        addSupply(down, "items", 5);
        invoke(down, "scaleToAttendees", new Class<?>[]{int.class}, 5);
        assertSupply(supplies(down).get(0), "items", 2.5);

        Object invalid = workshop("Atomicity Lab", 10);
        addSupply(invalid, "items", 5);
        assertInvalidScaleIsAtomic(invalid, 0);
        assertInvalidScaleIsAtomic(invalid, -2);
        pass(5, "scaleToAttendees");
    }

    private static void testStage6(Method helper) throws Exception {
        require(Modifier.isPrivate(helper.getModifiers()), "Amount formatter must be private");
        Object target = Modifier.isStatic(helper.getModifiers()) ? null : workshop("Format Lab", 1);
        helper.setAccessible(true);
        assertFormatted(helper, target, 48.0, "48");
        assertFormatted(helper, target, 4.5, "4.5");
        assertFormatted(helper, target, 1.125, "1.13");
        assertFormatted(helper, target, 3.50, "3.5");
        assertFormatted(helper, target, 0.75, "0.75");
        assertFormatted(helper, target, 1_000_000_000_000.0, "1000000000000");
        pass(6, "amount formatting helper");
    }

    private static void testStage7() throws Exception {
        Object workshop = workshop("Format Lab", 5);
        addSupply(workshop, "whole", 48.0);
        addSupply(workshop, "half", 4.5);
        addSupply(workshop, "rounded", 1.125);
        addSupply(workshop, "trimmed", 3.50);
        addSupply(workshop, "fraction", 0.75);
        String expected = String.join("\n",
                "Format Lab (5 attendees)",
                "- 48 whole",
                "- 4.5 half",
                "- 1.13 rounded",
                "- 3.5 trimmed",
                "- 0.75 fraction");
        ByteArrayOutputStream printed = new ByteArrayOutputStream();
        PrintStream original = System.out;
        String actual;
        try {
            System.setOut(new PrintStream(printed, true, StandardCharsets.UTF_8));
            actual = workshop.toString();
        } finally {
            System.setOut(original);
        }
        requireEqual(expected, actual, "Workshop.toString exact value");
        requireEqual("", printed.toString(StandardCharsets.UTF_8), "toString console output");
        requireEqual(actual, invoke(workshop, "toPrettyString"), "toPrettyString equality");
        require(!actual.startsWith("\n") && !actual.endsWith("\n") && !actual.endsWith("\r"),
                "Workshop string must not have leading or trailing newlines");
        requireEqual("Empty Lab (2 attendees)", workshop("Empty Lab", 2).toString(),
                "empty workshop representation");
        pass(7, "Workshop string representations");
    }

    private static void testStage8() throws Exception {
        String expected = String.join(System.lineSeparator(),
                "AI-Assisted Prototyping Workshop (24 attendees)",
                "- 48 index cards",
                "- 6 marker packs",
                "- 3 rolls of painter's tape",
                "- 0.75 reams of printer paper",
                "- 24 feedback forms",
                "",
                "Supply entries: 5",
                "",
                "After scaling to 36 attendees:",
                "",
                "AI-Assisted Prototyping Workshop (36 attendees)",
                "- 72 index cards",
                "- 9 marker packs",
                "- 4.5 rolls of painter's tape",
                "- 1.13 reams of printer paper",
                "- 36 feedback forms") + System.lineSeparator();
        requireEqual(expected, captureMain(), "Main exact output");
        pass(8, "Main example sequence");
    }

    private static boolean supplyStarted() {
        return SUPPLY.getDeclaredFields().length > 0
                || hasMethod(SUPPLY, "getName") || hasMethod(SUPPLY, "getAmount")
                || hasMethod(SUPPLY, "setAmount") || hasConstructor(SUPPLY, String.class, double.class);
    }

    private static boolean workshopStarted() {
        return WORKSHOP.getDeclaredFields().length > 0
                || hasConstructor(WORKSHOP, String.class, int.class)
                || hasMethod(WORKSHOP, "getTitle") || hasMethod(WORKSHOP, "getAttendees")
                || hasMethod(WORKSHOP, "getSupplies");
    }

    private static Method findFormattingHelper() {
        List<Method> candidates = new ArrayList<>();
        for (Method method : WORKSHOP.getDeclaredMethods()) {
            Class<?>[] parameters = method.getParameterTypes();
            if (Modifier.isPrivate(method.getModifiers()) && method.getReturnType() == String.class
                    && parameters.length == 1 && parameters[0] == double.class) {
                candidates.add(method);
            }
        }
        require(candidates.size() <= 1,
                "Expected at most one private String helper accepting one double");
        return candidates.isEmpty() ? null : candidates.get(0);
    }

    private static boolean hasDeclaredToString() {
        try {
            return WORKSHOP.getDeclaredMethod("toString").getDeclaringClass() == WORKSHOP;
        } catch (NoSuchMethodException exception) {
            return false;
        }
    }

    private static Object supply(String name, double amount) throws Exception {
        return constructor(SUPPLY, String.class, double.class).newInstance(name, amount);
    }

    private static Object workshop(String title, int attendees) throws Exception {
        return constructor(WORKSHOP, String.class, int.class).newInstance(title, attendees);
    }

    private static void addSupply(Object workshop, String name, double amount) throws Exception {
        invoke(workshop, "addSupply", new Class<?>[]{String.class, double.class}, name, amount);
    }

    private static List<?> supplies(Object workshop) throws Exception {
        Object value = invoke(workshop, "getSupplies");
        require(value instanceof List<?>, "getSupplies must return a List");
        return (List<?>) value;
    }

    private static void assertSupply(Object supply, String name, double amount) throws Exception {
        requireEqual(name, invoke(supply, "getName"), "supply name");
        requireDouble(amount, number(invoke(supply, "getAmount")), "supply amount");
    }

    private static void assertInvalidScaleIsAtomic(Object workshop, int target) throws Exception {
        int attendees = (Integer) invoke(workshop, "getAttendees");
        List<?> before = supplies(workshop);
        List<Double> amounts = new ArrayList<>();
        for (Object supply : before) {
            amounts.add(number(invoke(supply, "getAmount")));
        }
        try {
            invoke(workshop, "scaleToAttendees", new Class<?>[]{int.class}, target);
            throw new AssertionError("scaleToAttendees(" + target + ") must throw IllegalArgumentException");
        } catch (InvocationTargetException exception) {
            require(exception.getCause() instanceof IllegalArgumentException,
                    "Invalid scale must throw IllegalArgumentException");
        }
        requireEqual(attendees, invoke(workshop, "getAttendees"), "attendees after invalid scale");
        List<?> after = supplies(workshop);
        requireEqual(before.size(), after.size(), "entry count after invalid scale");
        for (int index = 0; index < after.size(); index++) {
            requireDouble(amounts.get(index), number(invoke(after.get(index), "getAmount")),
                    "amount after invalid scale at index " + index);
        }
    }

    private static void assertFormatted(Method helper, Object target, double input, String expected)
            throws Exception {
        requireEqual(expected, helper.invoke(target, input), "format " + input);
    }

    private static Field declaredField(Class<?> type, String name, Class<?> expectedType)
            throws Exception {
        Field field = type.getDeclaredField(name);
        require(field.getType() == expectedType,
                type.getSimpleName() + "." + name + " must have type " + expectedType.getSimpleName());
        return field;
    }

    private static Constructor<?> constructor(Class<?> type, Class<?>... parameters) throws Exception {
        return type.getDeclaredConstructor(parameters);
    }

    private static boolean hasConstructor(Class<?> type, Class<?>... parameters) {
        try {
            type.getDeclaredConstructor(parameters);
            return true;
        } catch (NoSuchMethodException exception) {
            return false;
        }
    }

    private static boolean hasMethod(Class<?> type, String name) {
        for (Method method : type.getDeclaredMethods()) {
            if (method.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static Object invoke(Object target, String name) throws Exception {
        return invoke(target, name, new Class<?>[0]);
    }

    private static Object invoke(Object target, String name, Class<?>[] parameterTypes,
                                 Object... arguments) throws Exception {
        Method method = target.getClass().getMethod(name, parameterTypes);
        return method.invoke(target, arguments);
    }

    private static double number(Object value) {
        require(value instanceof Number, "Expected a numeric value but found " + value);
        return ((Number) value).doubleValue();
    }

    private static boolean mainProducesOutput() throws Exception {
        return !captureMain().isEmpty();
    }

    private static String captureMain() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            Main.main(new String[0]);
        } finally {
            System.setOut(original);
        }
        return output.toString(StandardCharsets.UTF_8);
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void requireEqual(Object expected, Object actual, String label) {
        if (!expected.equals(actual)) {
            throw new AssertionError(label + ": expected <" + expected + "> but was <" + actual + ">");
        }
    }

    private static void requireDouble(double expected, double actual, String label) {
        if (Double.compare(expected, actual) != 0) {
            throw new AssertionError(label + ": expected <" + expected + "> but was <" + actual + ">");
        }
    }

    private static void pass(int stage, String description) {
        completedStages = stage;
        System.out.println("PASS Stage " + stage + ": " + description);
    }

    private static void reportBaseline() {
        System.out.println("PASS starter baseline: no implementation stage detected");
        System.out.println("Completed stages detected: 0/8");
    }

    private static void report() {
        System.out.println("Completed stages detected: " + completedStages + "/8");
    }
}
