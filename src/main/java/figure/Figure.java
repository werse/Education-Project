package figure;

public abstract class Figure {

  Color color;

  @Override
  public String toString() {
    return String.format("Figure{color=%s'}", color);
  }

  /**
   * Abstract method, that should calculate square for figure.
   *
   * @return square of figure
   */
  public abstract double calculateSquare();
}
