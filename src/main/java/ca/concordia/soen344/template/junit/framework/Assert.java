// 
// Decompiled by Procyon v0.5.36
// 

package ca.concordia.soen344.template.junit.framework;

public class Assert
{
    protected Assert() {
    }

    public static void Assert(final String message, final boolean condition) {
        if (!condition) {
            fail(message);
        }
    }

    public static void Assert(final boolean condition) {
        Assert(null, condition);
    }

    public static void assertEquals(final double expected, final double actual, final double delta) {
        assertEquals(null, expected, actual, delta);
    }

    public static void assertEquals(final long expected, final long actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(final Object expected, final Object actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(final String message, final double expected, final double actual, final double delta) {
        if (Math.abs(expected - actual) > delta) {
            failNotEquals(message, new Double(expected), new Double(actual));
        }
    }

    public static void assertEquals(final String message, final long expected, final long actual) {
        assertEquals(message, new Long(expected), new Long(actual));
    }

    public static void assertEquals(final String message, final Object expected, final Object actual) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && expected.equals(actual)) {
            return;
        }
        failNotEquals(message, expected, actual);
    }

    public static void assertNotNull(final Object object) {
        assertNotNull(null, object);
    }

    public static void assertNotNull(final String message, final Object object) {
        Assert(message, object != null);
    }

    public static void assertNull(final Object object) {
        assertNull(null, object);
    }

    public static void assertNull(final String message, final Object object) {
        Assert(message, object == null);
    }

    public static void assertSame(final Object expected, final Object actual) {
        assertSame(null, expected, actual);
    }

    public static void assertSame(final String message, final Object expected, final Object actual) {
        if (expected == actual) {
            return;
        }
        failNotSame(message, expected, actual);
    }

    public static void fail() {
        fail(null);
    }

    public static void fail(final String message) {
        throw new AssertionFailedError(message);
    }

    private static void failNotEquals(final String message, final Object expected, final Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = String.valueOf(message) + " ";
        }
        fail(String.valueOf(formatted) + "expected:<" + expected + "> but was:<" + actual + ">");
    }

    private static void failNotSame(final String message, final Object expected, final Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = String.valueOf(message) + " ";
        }
        fail(String.valueOf(formatted) + "expected same");
    }
}
