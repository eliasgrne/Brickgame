import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Ball class controls the movement and behavior of the ball in the game
public class Ball {
    private double x, y; // ball's position
    private double speedX = 3, speedY = -3; // ball's speed in x and y directions
    private static final int SIZE = 10; // ball's size

    // constructor sets the starting position of the ball
    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // moves the ball by adding speed to its position
    public void move() {
        x += speedX;
        y += speedY;
    }

    // checks if the ball hits the left, right, or top wall and makes it bounce
    public void checkWallCollision(double width, double height) {
        if (x <= 0 || x >= width - SIZE) speedX *= -1; // bounce off left and right walls
        if (y <= 0) speedY *= -1; // bounce off the top wall
    }

    // checks if the ball hits the paddle and makes it bounce
    public void checkPaddleCollision(Paddle paddle) {
        if (y + SIZE >= 370 && x >= paddle.getX() && x <= paddle.getX() + paddle.getWidth()) {
            double hitPos = (x - paddle.getX()) / paddle.getWidth(); // where the ball hit the paddle
            speedX = (hitPos - 0.5) * 6; // changes angle based on hit position
            speedY *= -1; // makes the ball bounce upward
        }
    }

    // checks if the ball hits a brick and makes it disappear
    public void checkBrickCollision(Brick[][] bricks) {
        for (Brick[] row : bricks) {
            for (Brick brick : row) {
                if (brick.isVisible() && x >= brick.getX() && x <= brick.getX() + 60 &&
                        y >= brick.getY() && y <= brick.getY() + 20) {
                    brick.setVisible(false); // makes the brick disappear
                    speedY *= -1; // makes the ball bounce
                }
            }
        }
    }

    // checks if the ball falls below the paddle (game over)
    public boolean missedPaddle(double height) {
        return y > height; // if ball goes past the bottom of the screen, return true
    }

    // draws the ball on the screen
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GOLD);
        gc.fillOval(x, y, SIZE, SIZE); // draws a gold-colored circle
    }
}
