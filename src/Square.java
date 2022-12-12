import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square {
    private Rectangle shape;
    private Color color;
    private boolean occupied = false;
    private double x;
    private double y;

    public Square(double x, double y) {
        this.x = x;
        this.y = y;
        this.shape = new Rectangle(Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH,
                x < 1 || x > 12 || y < 1 || y > 24 ? Color.LIGHTCYAN : Color.BLACK);
        this.shape.setX(175 + x * 25);
        this.shape.setY(y * 25);
        this.shape.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
    }

    public Rectangle getShape() {
        return this.shape;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
        this.shape.setFill(newColor);
    }

    public boolean isOccupied() {
        return this.occupied;
    }
}
