package figure;

public class Circle extends Figure {

    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", color=" + color +
                "} " + super.toString();
    }

    @Override
    public double calculateSquare() {
        return 0;
    }
}
