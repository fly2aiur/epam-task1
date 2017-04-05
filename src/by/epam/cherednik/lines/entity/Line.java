package by.epam.cherednik.lines.entity;

import by.epam.cherednik.lines.observer.LineObserver;
import by.epam.cherednik.lines.observer.Observable;
import by.epam.cherednik.lines.util.LineUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.cherednik.lines.util.IDManager.obtainID;

public class Line implements Observable<LineObserver> {
    private static final Logger LOG = LogManager.getLogger(Line.class);

    public static final Line AXIS_X = new Line(new Point(0, 0), new Point(1, 0));
    public static final Line AXIS_Y = new Line(new Point(0, 0), new Point(0, 1));

    static {
        AXIS_X.addObserver(LineObserver.getInstance());
        AXIS_Y.addObserver(LineObserver.getInstance());
    }

    private final int id;

    private Point a;
    private Point b;

    private LineObserver observer;

    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
        this.id = obtainID();
        LOG.debug("Created new object " + this);
    }

    @Override
    public void addObserver(LineObserver observer) {
        LOG.debug("Adding observer for " + this);
        observer.addObservable(this);
        this.observer = observer;
        notifyObserver();
    }

    @Override
    public void removeObserver() {
        observer.removeObservable(this);
        this.observer = null;
    }

    @Override
    public void notifyObserver() {
        if (observer != null) {
            observer.update(this);
        }
    }

    public int getId() {
        return id;
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
        LOG.debug("Changed A to " + getA());
        if (!LineUtil.isLine(a, b)) {
            LOG.warn("Points are same in " + this);
        }
        notifyObserver();
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
        LOG.debug("Changed B to " + getB());
        if (!LineUtil.isLine(a, b)) {
            LOG.warn("Points are same in " + this);
        }
        notifyObserver();
    }

    public LineProperties getProperties() {
        return observer.getProperties(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (observer != null ? !observer.equals(line.observer) : line.observer != null) return false;
        if (a != null ? !a.equals(line.a) : line.a != null) return false;
        return b != null ? b.equals(line.b) : line.b == null;
    }

    @Override
    public int hashCode() {
        int result = observer != null ? observer.hashCode() : 0;
        result = 31 * result + (a != null ? a.hashCode() : 0);
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Line{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
