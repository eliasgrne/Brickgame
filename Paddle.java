import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Paddle class represents the player's paddle in the game
public class Paddle {
    private double x; // x position of the paddle
    private static final int WIDTH = 100, HEIGHT = 10; // paddle size

    // constructor sets the starting x position of the paddle
    public Paddle(double x, double y) {
        this.x = x;
    }

    // updates the paddle's x position when the player moves it
    public void setX(double x) {
        this.x = x;
    }

    // returns the current x position of the paddle
    public double getX() {
        return x;
    }

    // returns the width of the paddle
    public double getWidth() {
        return WIDTH;
    }

    // draws the paddle on the screen
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(x, 370, WIDTH, HEIGHT); // draws a black rectangle as the paddle
    }
}

