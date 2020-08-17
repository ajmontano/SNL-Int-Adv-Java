package eventexample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class EventStackedPanes extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        CheckBox downUp = new CheckBox("Up/Down");

        EventHandler<MouseEvent> handler = e -> {
            System.out.println("Event Handler: " + e +
                    "\n - Target: " + e.getTarget() +
                    "\n - Source: " + e.getSource());
            if (downUp.isSelected()) {
                var t = e.getSource();
                if (t instanceof Parent p) {
//                    Parent p = (Parent)t;
                    for (Node n : p.getChildrenUnmodifiable()) {
                        if (n instanceof CheckBox c) {
                            if (c.isSelected()) {
                                System.out.println("Consumed in handler!");
                                e.consume();
                            }
                        }
                    }
                }
            }
        };
        EventHandler<MouseEvent> filter = e -> {
            System.out.println("Event Filter: " + e);
            if (!downUp.isSelected()) {
                var t = e.getSource();
                if (t instanceof Parent p) {
                    for (Node n : p.getChildrenUnmodifiable()) {
                        if (n instanceof CheckBox c) {
                            if (c.isSelected()) {
                                System.out.println("Consumed in filter!");
                                e.consume();
                            }
                        }
                    }
                }
            }
        };

        FlowPane stack = new FlowPane();
        stack.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        stack.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
        CheckBox cb1 = new CheckBox();

        FlowPane outer = new FlowPane();
        outer.setPrefSize(150, 150);
        outer.setStyle("-fx-background-color: red;");
//        outer.setOnMouseClicked(handler);
        outer.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        outer.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
        CheckBox cb2 = new CheckBox();

        FlowPane middle = new FlowPane();
        middle.setPrefSize(100, 100);
        middle.setStyle("-fx-background-color: orange;");
//        middle.setOnMouseClicked(handler);
        middle.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        middle.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
        CheckBox cb3 = new CheckBox();

        FlowPane inner = new FlowPane();
        inner.setPrefSize(50, 50);
//        inner.setOnMouseClicked(handler);
        inner.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        inner.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
        inner.setStyle("-fx-background-color: yellow;");
        CheckBox cb4 = new CheckBox();

        stack.getChildren().addAll(outer, cb1, downUp);
        outer.getChildren().addAll(middle, cb2);
        middle.getChildren().addAll(inner, cb3);
        inner.getChildren().addAll(cb4);

        Scene scene = new Scene(stack, 200, 200);
        stage.setScene(scene);
        stage.setTitle("Events");
        stage.show();

    }
}
