package by.epam.cherednik.lines.util;

public class IDManager {
    private static int ID;

    public static int obtainID() {
        return ID++;
    }
}
