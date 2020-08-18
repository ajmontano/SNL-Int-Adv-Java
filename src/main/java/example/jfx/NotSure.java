package example.jfx;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NotSure extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();

        Canvas canvas = new Canvas(800, 600);
        GraphicsContext g2d = canvas.getGraphicsContext2D();
        g2d.setFill(Color.RED);
        g2d.setStroke(Color.BLUE);
        g2d.strokeLine(0, 0, 800, 600);

        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Drawing");
        stage.setScene(scene);
        stage.show();

        new Thread(() -> {
            try {
                Thread.sleep(4_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Starting animation...");
            for (int i = 1; i < 100; i++) {
                int offset = i;
                Platform.runLater(() -> {
                    g2d.strokeLine(800 - (8 * (offset - 1)), 6 * (offset - 1),
                            800 - (8 * offset), 6 * offset);
                });
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
