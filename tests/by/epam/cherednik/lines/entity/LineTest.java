package by.epam.cherednik.lines.entity;

import by.epam.cherednik.lines.observer.LineObserver;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineTest {
    @Test
    public void checkObserver() throws Exception {
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Line line = new Line(a, b);
        line.addObserver(LineObserver.getInstance());
        LineProperties lineProperties = line.getProperties();
        line.removeObserver();
    }

    @Test
    public void changePoint() throws Exception {
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Point c = new Point(0, 1);
        Line line = new Line(a, b);
        line.setA(c);
        Line expected = new Line(c, b);
        assertEquals(expected, line);
    }
}