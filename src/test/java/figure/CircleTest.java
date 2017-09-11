package figure;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CircleTest {

    @Test
    public void getSquareTest() throws Exception {
        Figure circle = new Circle(10);
        assertThat(circle.calculateSquare(), is(314.1592));
    }

    @Test
    public void getSquareTestWithZeroRadius() throws Exception {
        Figure circle = new Circle(0);
        assertThat(circle.calculateSquare(), is(0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSquareTestWithNegativeParameters() throws Exception {
        new Circle(-10);
    }
}