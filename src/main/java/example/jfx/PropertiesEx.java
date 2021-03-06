package example.jfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class PropertiesEx extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        TextFlow root = new TextFlow();
        Circle circle = new Circle();
        circle.setFill(Color.BROWN);
        circle.setRadius(100);
        TextField tf = new TextField("Hello input");
        TextField tf2 = new TextField("Hello input");
        Text output = new Text("Hello output");

        output.textProperty().bind(tf.textProperty());
        tf2.textProperty().bindBidirectional(tf.textProperty());
        tf.textProperty().bindBidirectional(circle.radiusProperty(), new NumberStringConverter());

        root.getChildren().addAll(circle, tf, tf2, output);

        Scene sc = new Scene(root, 600, 100);
        stage.setTitle("Binding");
        stage.setScene(sc);
        stage.show();
    }
}
