package by.epam.cherednik.lines.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinePropertiesTest {
    @Test
    public void isValid() throws Exception {
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Line line = new Line(a, b);
        LineProperties properties = new LineProperties();
        properties.update(line);
        assertTrue(properties.isValid());
    }

    @Test
    public void getGradient() throws Exception {
        Point a = new Point(0, 0);
        Point b = new Point(1, 1);
        Line line = new Line(a, b);
        LineProperties properties = new LineProperties();
        properties.update(line);
        double actual = properties.getGradient();
        double expected = 1.0;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void getYIntercept() throws Exception {
        Point a = new Point(2, 4);
        Point b = new Point(3, 5);
        Line line = new Line(a, b);
        LineProperties properties = new LineProperties();
        properties.update(line);
        double actual = properties.getYIntercept();
        double expected = 2.0;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void getXIntercept() throws Exception {
        Point a = new Point(2, 4);
        Point b = new Point(3, 5);
        Line line = new Line(a, b);
        LineProperties properties = new LineProperties();
        properties.update(line);
        double actual = properties.getXIntercept();
        double expected = -2.0;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void isVertical() throws Exception {
        Point a = new Point(0, 0);
        Point b = new Point(0, 1);
        Line line = new Line(a, b);
        LineProperties properties = new LineProperties();
        properties.update(line);
        assertTrue(properties.isVertical());
    }

}