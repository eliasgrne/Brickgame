import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// main class that runs the game extends Application so JavaFX knows how to start it
public class Main extends Application {

    // start method sets up the game window and everything inside it
    @Override
    public void start(Stage stage) {

        // creates a GameCanvas which is like a drawing board for the game
        // 600 is the width and 400 is the height in pixels
        GameCanvas gameCanvas = new GameCanvas(600, 400);

        // StackPane is a layout that holds the GameCanvas and keeps it centered
        StackPane root = new StackPane(gameCanvas);

        // Scene is like the stage where everything in the game is displayed
        Scene scene = new Scene(root);

        // sets the scene onto the stage which is the game window
        stage.setScene(scene);

        // sets the title of the game window to Brick Breaker
        stage.setTitle("Brick Breaker");

        // makes the game window appear on the screen
        stage.show();
    }

    // main method starts the program and tells JavaFX to run the game
    public static void main(String[] args) {
        launch(); // calls start method automatically
    }
}

