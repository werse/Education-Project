package figure;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SquareTest {

    @Test
    public void getSquareTest() throws Exception {
        Figure square = new Square(10);
        assertThat(square.calculateSquare(), is(100.0));
    }

    @Test
    public void getSquareTestWithZeroRadius() throws Exception {
        Figure square = new Square(0);
        assertThat(square.calculateSquare(), is(0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSquareTestWithNegativeParameters() throws Exception {
        new Circle(-10);
    }
}