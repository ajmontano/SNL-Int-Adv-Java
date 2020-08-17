package example.jfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class HelloWorld extends Application {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(Thread.currentThread().getName());

//        Group
        int[] count = {0};
        TextFlow flow = new TextFlow();
        Text text = new Text();

        Button button = new Button("Press Me");
        button.setOnAction(e -> {
            new Thread(() -> {
                System.out.println("Worker thread started, name is " +  Thread.currentThread().getName());
                try {
                    Thread.sleep(6_000); // Simulates massive computation!
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                Runnable r = () -> {
                    System.out.println("in event handler " + Thread.currentThread().getName());
                    text.setText("Hello JavaFX World " + ++count[0]);
                };
                Platform.runLater(r);
            }).start();
        });

        flow.getChildren().addAll(button, text);

        Scene scene = new Scene(flow, 600, 200);
        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }
}
