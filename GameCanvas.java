import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

// GameCanvas class is the main part of the game where everything is drawn and updated
public class GameCanvas extends Canvas {
    private Paddle paddle;
    private Ball ball;
    private Brick[][] bricks;
    private boolean gameOver = false;
    private final int ROWS = 5, COLUMNS = 8; // number of rows and columns of bricks
    private final int BRICK_WIDTH = 70, BRICK_HEIGHT = 25, BRICK_PADDING = 5; // brick size and spacing

    // constructor that sets up the game canvas with width and height
    public GameCanvas(int width, int height) {
        super(width, height);

        // creates the paddle and positions it near the bottom
        paddle = new Paddle(width / 2 - 50, height - 30);

        // creates the ball and places it in the middle
        ball = new Ball(width / 2, height / 2);

        // creates the brick grid and places them in rows and columns
        bricks = new Brick[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                int x = j * (BRICK_WIDTH + BRICK_PADDING); // calculates x position
                int y = i * (BRICK_HEIGHT + BRICK_PADDING); // calculates y position
                bricks[i][j] = new Brick(x, y);
            }
        }

        // listens for mouse movement to move the paddle
        setOnMouseMoved(this::handleMouseMove);

        // runs the game loop that updates and draws the game continuously
        new AnimationTimer() {
            public void handle(long now) {
                if (!gameOver) {
                    update();
                    draw();
                } else {
                    drawGameOver();
                }
            }
        }.start();
    }

    // moves the paddle based on mouse movement
    private void handleMouseMove(MouseEvent e) {
        paddle.setX(e.getX() - paddle.getWidth() / 2);
    }

    // updates game objects like ball movement and collisions
    private void update() {
        ball.move();
        ball.checkWallCollision(getWidth(), getHeight());

        // checks if the ball missed the paddle and ends the game
        if (ball.missedPaddle(getHeight())) {
            gameOver = true;
        }

        // checks if the ball hits the paddle or bricks
        ball.checkPaddleCollision(paddle);
        ball.checkBrickCollision(bricks);
    }

    // draws everything on the screen
    private void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, getWidth(), getHeight()); // clears screen

        paddle.draw(gc);
        ball.draw(gc);

        // loops through all bricks and draws the ones that are still visible
        for (Brick[] row : bricks) {
            for (Brick brick : row) {
                if (brick.isVisible()) {
                    brick.draw(gc);
                }
            }
        }
    }

    // draws the game over screen when the player loses
    private void drawGameOver() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, getWidth(), getHeight()); // fills screen with black

        gc.setFill(Color.RED);
        gc.setFont(new Font("Arial", 40));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("GAME OVER HAHAHAHAHA", getWidth() / 2, getHeight() / 2);
    }
}
