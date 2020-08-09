package ru.otus;

public class ApprovalStatements {
    public static void approvalEquals(Object expected, Object actual) {
        if (!objectsAreEqual(expected, actual)) {
            throw new ApprovalError();
        }
    }

    public static void approvalNotNull(Object actual) {
        if (actual == null) {
            throw new ApprovalError();
        }
    }

    private static boolean objectsAreEqual(Object obj1, Object obj2) {
        if (obj1 == null) {
            return (obj2 == null);
        }
        return obj1.equals(obj2);
    }
}
