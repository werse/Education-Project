package figure;

public class Square extends Rectangle {

    public Square(int width) {
        super(width, width);
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + width +
                ", color=" + color +
                "} " + super.toString();
    }
}
