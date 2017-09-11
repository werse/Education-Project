package figure;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RectangleTest {

    @Test
    public void getSquareTest() throws Exception {
        Figure rectangle = new Rectangle(10, 10);
        assertThat(rectangle.calculateSquare(), is(100.0));
    }

    @Test
    public void getSquareTestWithZeroParameters() throws Exception {
        Figure rectangle = new Rectangle(0, 0);
        assertThat(rectangle.calculateSquare(), is(0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSquareTestWithNegativeParameters() throws Exception {
        new Rectangle(-10, -10);
    }
}