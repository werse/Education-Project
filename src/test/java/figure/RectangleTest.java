package figure;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RectangleTest {

    @Test
    public void getSquareTest() throws Exception {
        Figure square = new Rectangle(10, 10);
        assertThat(square.calculateSquare(), is(100.0));
    }

    @Test
    public void getSquareTestWithZeroParameters() throws Exception {
        Figure square = new Rectangle(0, 0);
        assertThat(square.calculateSquare(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSquareTestWithNegativeParameters() throws Exception {
        new Rectangle(-10, -10);
    }
}