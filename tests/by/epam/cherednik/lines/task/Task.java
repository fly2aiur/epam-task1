package by.epam.cherednik.lines.task;


import by.epam.cherednik.lines.entity.Line;
import by.epam.cherednik.lines.entity.Point;
import by.epam.cherednik.lines.observer.LineObserver;
import by.epam.cherednik.lines.util.LineUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Task {
    @Test
    public void isParallelLines() throws Exception {
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
        Line first = new Line(a, b);
        first.addObserver(LineObserver.getInstance());

        Point c = new Point(1, 3);
        Point d = new Point(3, 5);
        Line second = new Line(c, d);
        second.addObserver(LineObserver.getInstance());

        assertTrue(LineUtil.checkParallel(first, second));
    }

    @Test
    public void isParallelLines() throws Exception {
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
        Line first = new Line(a, b);
        first.addObserver(LineObserver.getInstance());

        Point c = new Point(1, 3);
        Point d = new Point(3, 5);
        Line second = new Line(c, d);
        second.addObserver(LineObserver.getInstance());

        assertTrue(LineUtil.checkParallel(first, second));
    }

}
