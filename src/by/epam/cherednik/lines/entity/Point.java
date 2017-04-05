package by.epam.cherednik.lines.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Point {
    private static final Logger LOG = LogManager.getLogger(Point.class);

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        LOG.debug("Created new object " + this);
    }

    public Point() {
        this(0, 0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        LOG.debug("Changed x to " + x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        LOG.debug("Changed y to " + y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
