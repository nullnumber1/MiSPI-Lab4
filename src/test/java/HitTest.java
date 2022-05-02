import nullnumber1.entity.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class HitTest {
    private final Point point = new Point();

    @Test
    public void circleLeftBottomIn() {
        assertTrue(point.calculate(-1, 0, 2));
    }

    @Test
    public void circleRightBottomIn() {
        assertTrue(point.calculate(0, 0, 1));
    }

    @Test
    public void circleRightTopIn() {
        assertTrue(point.calculate(0, 2, 4));
    }

    @Test
    public void circleLeftTopIn() {
        assertTrue(point.calculate(-Math.sqrt(2) / 4 + 0.0001, Math.sqrt(2) / 4 - 0.0001, 1));
    }

    @Test
    public void circleCenterIn() {
        assertTrue(point.calculate(-1, 1, 4));
    }

    @Test
    public void triangleRightBottomIn() {
        assertTrue(point.calculate(1, 0, 2));
    }

    @Test
    public void triangleCenterIn() {
        assertTrue(point.calculate(0.5, 0.5, 4));
    }

    @Test
    public void triangleRightBorderIn() {
        assertTrue(point.calculate(1, 1, 4));
    }

    @Test
    public void rectangleLeftBottomIn() {
        assertTrue(point.calculate(0, -1, 2));
    }

    @Test
    public void rectangleRightBottomIn() {
        assertTrue(point.calculate(2, -1, 2));
    }

    @Test
    public void rectangleRightTopIn() {
        assertTrue(point.calculate(2, 0, 2));
    }

    @Test
    public void rectangleCenterIn() {
        assertTrue(point.calculate(2, -1, 4));
    }

    @Test
    public void circleLeftBottomOut() {
        assertFalse(point.calculate(-1 - 0.0001, 0, 2));
    }

    @Test
    public void circleRightBottomOut() {
        assertFalse(point.calculate(-0.0001, -0.0001, 1));
    }

    @Test
    public void circleRightTopOut() {
        assertFalse(point.calculate(0, 2 + 0.0001, 4));
    }

    @Test
    public void circleLeftTopOut() {
        assertFalse(point.calculate(-Math.sqrt(2) / 2 - 0.0001, Math.sqrt(2) / 2 + 0.0001, 1));
    }

    @Test
    public void triangleRightBottomOut() {
        assertFalse(point.calculate(1 + 0.0001, 0.0001, 2));
    }

    @Test
    public void triangleRightBorderOut() {
        assertFalse(point.calculate(1 + 0.0001, 1 + 0.0001, 4));
    }

    @Test
    public void rectangleLeftBottomOut() {
        assertFalse(point.calculate(0, -1 - 0.0001, 2));
    }

    @Test
    public void rectangleRightBottomOut() {
        assertFalse(point.calculate(2 + 0.0001, -1 - 0.0001, 2));
    }

    @Test
    public void rectangleRightTopOut() {
        assertFalse(point.calculate(2 + 0.0001, 0, 2));
    }
}