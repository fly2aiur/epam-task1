package by.epam.cherednik.lines.observer;

import by.epam.cherednik.lines.entity.Line;
import by.epam.cherednik.lines.entity.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineObserverTest {
    @Test
    public void getProperties() throws Exception {
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
        Line line = new Line(a, b);
        line.addObserver(LineObserver.getInstance());
        double expected = 1.0;
        double actual = line.getProperties().getYIntercept();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void propertiesUpdate() throws Exception {
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
        Point c = new Point(0, 4);
        Line line = new Line(a, b);
        line.addObserver(LineObserver.getInstance());
        line.setB(c);
        double expected = 4.0;
        double actual = line.getProperties().getYIntercept();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void propertiesUpdateFirst() throws Exception {
        Point a = new Point(0, 0);
        Point b = new Point(1, -1);
        Point c = new Point(1, 1);
        Line first = new Line(a, b);
        first.addObserver(LineObserver.getInstance());
        Line second = new Line(a, b);
        second.addObserver(LineObserver.getInstance());
        second.setB(c);
        double expected = -1.0;
        double actual = first.getProperties().getGradient();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void propertiesUpdateSecond() throws Exception {
        Point a = new Point(0, 0);
        Point b = new Point(1, -1);
        Point c = new Point(1, 1);
        Line first = new Line(a, b);
        first.addObserver(LineObserver.getInstance());
        Line second = new Line(a, b);
        second.addObserver(LineObserver.getInstance());
        second.setB(c);
        double expected = 1.0;
        double actual = second.getProperties().getGradient();
        assertEquals(expected, actual, 0.001);
    }
}