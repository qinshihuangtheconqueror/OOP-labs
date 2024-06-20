package gui;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SquareUI extends StackPane {
    public SquareUI(String name, Runnable action,int width, int height, int id) {
        LinearGradient gradient = new LinearGradient(0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0.1, Color.web("black", 0.75)),
                new Stop(1.0, Color.web("black", 0.15))
        );

        Rectangle bg = new Rectangle(width, height);
        Text text = new Text("Square number: "+ id);
        text.setFont(Font.font(12.0));
        text.fillProperty().bind(
                Bindings.when(bg.hoverProperty()).then(Color.GRAY).otherwise(Color.TRANSPARENT)
        );
        bg.fillProperty().bind(
                Bindings.when(hoverProperty()).then(Color.TRANSPARENT).otherwise(Color.TRANSPARENT)
        );
        setOnMouseClicked(e -> action.run());

        getChildren().addAll(bg, text);
    }

}
