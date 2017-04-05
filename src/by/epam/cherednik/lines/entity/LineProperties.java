package by.epam.cherednik.lines.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LineProperties {
    private static final Logger LOG = LogManager.getLogger(LineProperties.class);

    private boolean valid;
    private double gradient;
    private double yIntercept;
    private double xIntercept;

    public void update(Line observable) {
        Point a = observable.getA();
        Point b = observable.getB();

        if (a.equals(b)) {
            valid = false;
            gradient = Double.NaN;
            xIntercept = Double.NaN;
            yIntercept = Double.NaN;
        } else {
            valid = true;
            double x = a.getX();
            double y = a.getY();
            double dx = b.getX() - a.getX();
            double dy = b.getY() - a.getY();

            if (dx == 0.0) {
                gradient = Double.POSITIVE_INFINITY;
                xIntercept = x;
                yIntercept = Double.NaN;
            } else {
                gradient = dy / dx;
                yIntercept = y - (gradient * x);
                xIntercept = -yIntercept / gradient;
            }
        }
        LOG.debug("Updated " + this);
    }

    public boolean isValid() {
        return valid;
    }

    public double getGradient() {
        return gradient;
    }

    public double getYIntercept() {
        return yIntercept;
    }

    public double getXIntercept() {
        return xIntercept;
    }

    public boolean isVertical() {
        return gradient == Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineProperties that = (LineProperties) o;

        if (valid != that.valid) return false;
        if (Double.compare(that.gradient, gradient) != 0) return false;
        if (Double.compare(that.yIntercept, yIntercept) != 0) return false;
        return Double.compare(that.xIntercept, xIntercept) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (valid ? 1 : 0);
        temp = Double.doubleToLongBits(gradient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yIntercept);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(xIntercept);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "LineProperties{" +
                "valid=" + valid +
                ", gradient=" + gradient +
                ", yIntercept=" + yIntercept +
                ", xIntercept=" + xIntercept +
                '}';
    }
}