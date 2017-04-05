package by.epam.cherednik.lines.util;

import by.epam.cherednik.lines.creator.LineCreator;
import by.epam.cherednik.lines.entity.Line;
import by.epam.cherednik.lines.entity.Point;
import by.epam.cherednik.lines.exception.LineException;
import by.epam.cherednik.lines.observer.LineObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LineUtilTest {
    private Line first;
    private Line second;
    private Point point;
    private boolean containsPoint;
    private boolean isCoinciding;
    private boolean isParallel;
    private boolean isIntersecting;
    private boolean isPerpendicular;
    private double angle;
    private double angleWithX;
    private double angleWWithY;

    public LineUtilTest(Line first,
                        Line second,
                        Point point,
                        boolean containsPoint,
                        boolean isCoinciding,
                        boolean isParallel,
                        boolean isIntersecting,
                        boolean isPerpendicular,
                        double angle,
                        double angleWithX,
                        double angleWithY) {
        this.first = first;
        this.first.addObserver(LineObserver.getInstance());
        this.second = second;
        this.second.addObserver(LineObserver.getInstance());
        this.point = point;
        this.containsPoint = containsPoint;
        this.isCoinciding = isCoinciding;
        this.isParallel = isParallel;
        this.isIntersecting = isIntersecting;
        this.isPerpendicular = isPerpendicular;
        this.angle = angle;
        this.angleWithX = angleWithX;
        this.angleWWithY = angleWithY;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> initializeData() {
        try {
            return Arrays.asList(new Object[][]{
                    {
                            LineCreator.createLine(0, 0, 1, 1),
                            LineCreator.createLine(0, 0, 1, 0),
                            new Point(1, 1),
                            true,
                            false,
                            false,
                            true,
                            false,
                            45.0,
                            45.0,
                            45.0
                    },
                    {
                            LineCreator.createLine(0, 0, 1, 0),
                            LineCreator.createLine(0, 1, 1, 1),
                            new Point(0, 3),
                            false,
                            false,
                            true,
                            false,
                            false,
                            0.0,
                            0.0,
                            90.0
                    },
                    {
                            LineCreator.createLine(0, 0, 1, 0),
                            LineCreator.createLine(0, 0, 0, 1),
                            new Point(0, 1),
                            false,
                            false,
                            false,
                            true,
                            true,
                            90.0,
                            0.0,
                            90.0
                    },
                    {
                            LineCreator.createLine(0, 1, 0, 0),
                            LineCreator.createLine(0, -5, 0, -2),
                            new Point(0, -1),
                            true,
                            true,
                            false,
                            false,
                            false,
                            0.0,
                            90.0,
                            0.0
                    },
            });
        } catch (LineException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void contains() throws Exception {
        assertEquals(containsPoint, LineUtil.contains(first, point));
    }

    @Test
    public void checkCoinciding() throws Exception {
        assertEquals(isCoinciding, LineUtil.checkCoinciding(first, second));
    }

    @Test
    public void checkParallel() throws Exception {
        assertEquals(isParallel, LineUtil.checkParallel(first, second));
    }

    @Test
    public void checkIntersecting() throws Exception {
        assertEquals(isIntersecting, LineUtil.checkIntersecting(first, second));
    }

    @Test
    public void checkPerpendicular() throws Exception {
        assertEquals(isPerpendicular, LineUtil.checkPerpendicular(first, second));
    }

    @Test
    public void angle() throws Exception {
        double actual = LineUtil.angle(first, second);
        assertEquals(angle, actual, 0.001);
    }

    @Test
    public void angleWithYAxis() throws Exception {
        double actual = LineUtil.angle(first, Line.AXIS_X);
        assertEquals(angleWithX, actual, 0.001);
    }

    @Test
    public void angleWithXAxis() throws Exception {
        double actual = LineUtil.angle(first, Line.AXIS_Y);
        assertEquals(angleWWithY, actual, 0.001);
    }
}