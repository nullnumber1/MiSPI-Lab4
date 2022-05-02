import nullnumber1.entity.Point;
import org.junit.Test;
import static org.junit.Assert.*;

public class HitTest {
    private final Point point = new Point();

    @Test
    public void middleTest(){
        assertTrue(point.calculate(0, 0, 1));
    }
}
