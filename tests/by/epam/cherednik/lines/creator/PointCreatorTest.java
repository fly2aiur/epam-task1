package by.epam.cherednik.lines.creator;

import by.epam.cherednik.lines.entity.Point;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PointCreatorTest {
    @Test
    public void createPoints() throws Exception {
        ArrayList<Point> points = PointCreator.createPoints("  {2.1;4.6}{2.14;5o}d{6.5;  -1}");
        int actual = points.size();
        int expected = 2;
        assertEquals(expected, actual);
    }
}