package by.epam.cherednik.lines.creator;


import by.epam.cherednik.lines.entity.Line;
import by.epam.cherednik.lines.entity.Point;
import by.epam.cherednik.lines.exception.DataFileException;
import by.epam.cherednik.lines.exception.LineException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

public class LineCreator {
    private static final Logger LOG = LogManager.getLogger(LineCreator.class);

    private static final int REQUIRED_LINES = 2;

    public static Line createLine(Point a, Point b) throws LineException {
        if (a.equals(b)) {
            LOG.error("Can't create line, passed points are same");
            throw new LineException("Can't create line, passed points are same");
        } else {
            return new Line(a, b);
        }
    }

    public static Line createLine(double ax, double ay, double bx, double by) throws LineException {
        return createLine(new Point(ax, ay), new Point(bx, by));
    }

    public static Line createLine(String resource) throws LineException {
        LOG.debug("Trying to parse Line data from string");
        ArrayList<Point> list = PointCreator.createPoints(resource);
        if (list.size() < REQUIRED_LINES) {
            LOG.warn("Insufficient number of coordinates to create a line, source=\"" + resource + "\"");
            throw new LineException("Insufficient number of coordinates to create a line, source=\"" + resource + "\"");
        }
        Line line = createLine(list.get(0), list.get(1));
        LOG.debug("Successfully parsed " + line);
        return line;
    }

    public static ArrayList<Line> readLinesFromFile(String pathname) throws DataFileException {
        LOG.debug("Reading lines from file: " + pathname);
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(pathname)))) {
            ArrayList<Line> list = new ArrayList<>();
            reader.lines().forEach(line -> {
                try {
                    Line object = LineCreator.createLine(line);
                    list.add(object);
                }catch (LineException e){
                    LOG.debug("Couldn't create Line object, skipping string");
                }
            });
            LOG.debug("Successfully created " + list.size() + " lines");
            return list;
        } catch (FileNotFoundException e) {
            LOG.error("File not found: " + pathname);
            throw new DataFileException("No such file: " + pathname, e);
        } catch (IOException e) {
            LOG.error("Error occurred while reading file: " + pathname);
            throw new DataFileException("Error occurred while reading file: " + pathname, e);
        }
    }
}
