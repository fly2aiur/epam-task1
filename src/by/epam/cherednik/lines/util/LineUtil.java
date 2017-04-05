package by.epam.cherednik.lines.util;

import by.epam.cherednik.lines.entity.Line;
import by.epam.cherednik.lines.entity.LineProperties;
import by.epam.cherednik.lines.entity.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LineUtil {
    private static final Logger LOG = LogManager.getLogger(LineUtil.class);

    public static boolean contains(Line line, Point point) {
        boolean value;
        if (line.getProperties().isVertical()) {
            value = point.getX() == line.getProperties().getXIntercept();
        } else {
            value = point.getY() == point.getX() * line.getProperties().getGradient() + line.getProperties().getYIntercept();
        }
        if (value) {
            LOG.debug(line + " contains " + point);
        } else {
            LOG.debug(line + " does not contain " + point);

        }
        return value;
    }

    public static boolean checkCoinciding(Line first, Line second) {
        boolean value = first.getProperties().equals(second.getProperties());

        if (value) {
            LOG.debug(first + " is coinciding with " + second);
        } else {
            LOG.debug(first + " is not coinciding with " + second);
        }
        return value;
    }

    public static boolean checkParallel(Line first, Line second) {
        LineProperties a = first.getProperties();
        LineProperties b = second.getProperties();

        boolean value;
        if (a.isVertical() && b.isVertical()) {
            value = (a.getXIntercept() != b.getXIntercept());
        } else {
            value = (a.getGradient() == b.getGradient()) &&
                    ((a.getYIntercept() != b.getYIntercept()) ||
                            (a.getXIntercept() != b.getXIntercept()));
        }

        if (value) {
            LOG.debug(first + " is parallel with " + second);
        } else {
            LOG.debug(first + " is not parallel with " + second);
        }
        return value;
    }

    public static boolean checkIntersecting(Line first, Line second) {
        LineProperties a = first.getProperties();
        LineProperties b = second.getProperties();

        boolean value = a.getGradient() != b.getGradient();

        if (value) {
            LOG.debug(first + " is intersecting with " + second);
        } else {
            LOG.debug(first + " is not intersecting with " + second);
        }

        return value;
    }

    public static boolean checkPerpendicular(Line first, Line second) {
        boolean value = angle(first, second) == 90;

        if (value) {
            LOG.debug(first + " is perpendicular with " + second);
        } else {
            LOG.debug(first + " is not perpendicular with " + second);
        }

        return value;
    }

    public static double angle(Line first, Line second) {
        LineProperties a = first.getProperties();
        LineProperties b = second.getProperties();

        double thetaA = Math.toDegrees(Math.atan(a.getGradient()));
        double thetaB = Math.toDegrees(Math.atan(b.getGradient()));

        double value = Math.abs(thetaA - thetaB);

        LOG.debug("Angle between " + first + " and  " + second + " is " + value + " degrees");

        return value;
    }

    public static boolean isLine(Point a, Point b) {
        return !a.equals(b);
    }
}
