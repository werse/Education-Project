package figure;

public class Circle extends Figure {

  private int radius;

  public Circle(int radius) {

    if (radius < 0) throw new IllegalArgumentException();
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
    return (double) ((int) (Math.PI * radius * radius * 10000)) / 10000;
  }
}
