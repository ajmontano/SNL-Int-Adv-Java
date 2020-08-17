package example.jfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class HelloWorld extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        int[] count = {0};
        TextFlow flow = new TextFlow();
        Text text = new Text();

        Button button = new Button("Press Me");
        button.setOnAction(e -> text.setText("Hello JavaFX World " + ++count[0]));

        flow.getChildren().addAll(button, text);

        Scene scene = new Scene(flow, 600, 200);
        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }
}
