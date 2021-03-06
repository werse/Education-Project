package figure;

public class Rectangle extends Figure {

  int width;
  private int length;

  public Rectangle(int width, int length) {
    if (width < 0 || length < 0) {
      throw new IllegalArgumentException();
    }

    this.width = width;
    this.length = length;
  }

  @Override
  public String toString() {
    return "Rectangle{" +
      "width=" + width +
      ", length=" + length +
      ", color=" + color +
      "} " + super.toString();
  }

  @Override
  public double calculateSquare() {
    return width * length;
  }
}
