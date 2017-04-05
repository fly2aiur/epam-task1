package by.epam.cherednik.lines.creator;

import by.epam.cherednik.lines.entity.Line;
import by.epam.cherednik.lines.entity.Point;
import by.epam.cherednik.lines.exception.DataFileException;
import by.epam.cherednik.lines.exception.LineException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LineCreatorTest {
    @Test
    public void createLine() throws Exception {
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        Line line = LineCreator.createLine(a, b);
        Line expected = new Line(a, b);
        assertEquals(expected, line);
    }

    @Test
    public void createLine1() throws Exception {
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        Line line = LineCreator.createLine(0, 0, 3, 4);
        Line expected = new Line(a, b);
        assertEquals(expected, line);
    }

    @Test(expected = LineException.class)
    public void createLineFromInvalidData() throws Exception {
        Point a = new Point(0, 0);
        LineCreator.createLine(a, a);
    }

    @Test
    public void ParseLine() throws Exception {
        String testString = " {-9; 0} {2; 7}";
        Line line = LineCreator.createLine(testString);
        Point a = new Point(-9, 0);
        Point b = new Point(2, 7);
        Line expected = new Line(a, b);
        assertEquals(expected, line);
    }

    @Test
    public void readLinesFromFile() throws Exception {
        ArrayList<Line> lines = LineCreator.readLinesFromFile("resources/data.txt");
        int expected = 3;
        int actual = lines.size();
        assertEquals(expected, actual);
    }

    @Test
    public void readFromFileChecked() throws Exception {
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
        Line expected = new Line(a, b);
        ArrayList<Line> lines = LineCreator.readLinesFromFile("resources/data.txt");
        Line actual = lines.get(1);
        assertEquals(expected, actual);
    }

    @Test(expected = DataFileException.class)
    public void readFromFileError() throws Exception {
        LineCreator.readLinesFromFile("resources/nosuchdata.txt");
    }
}