package figure;

public enum Color {

    RED("red"), GREEN("green"), BLUE("blue"), WHITE("white"), BLACK("black");

    String color;

    Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
