package by.epam.cherednik.lines.creator;

import by.epam.cherednik.lines.entity.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PointCreator {
    private static final Logger LOG = LogManager.getLogger(PointCreator.class);

    private static final String POINT_PATTERN = "\\{\\s*([-+]?[0-9]*\\.?[0-9]+);\\s*([-+]?[0-9]*\\.?[0-9]+)\\s*}";

    public static ArrayList<Point> createPoints(String resource) {
        LOG.debug("Reading points data");
        ArrayList<Point> list = new ArrayList<>();
        Pattern p = Pattern.compile(POINT_PATTERN);
        Matcher m = p.matcher(resource);
        while (m.find()) {
            double x = Double.valueOf(m.group(1));
            double y = Double.valueOf(m.group(2));
            list.add(new Point(x, y));
        }
        LOG.debug("Successfully parsed " + list.size() + " points");
        return list;
    }
}
