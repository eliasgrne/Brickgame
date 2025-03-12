import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Brick class represents the bricks that the ball needs to break
public class Brick {
    private double x, y; // brick position
    private boolean visible = true; // keeps track if the brick is still there

    // constructor sets the position of the brick
    public Brick(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // returns the x position of the brick
    public double getX() {
        return x;
    }

    // returns the y position of the brick
    public double getY() {
        return y;
    }

    // checks if the brick is still visible
    public boolean isVisible() {
        return visible;
    }

    // makes the brick disappear when hit by the ball
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    // draws the brick on the screen if it is still visible
    public void draw(GraphicsContext gc) {
        if (visible) {
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, 60, 20); // draws a blue rectangle for the brick
        }
    }
}

