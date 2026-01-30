package main.shapes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ObjectShape extends BaseShape {
    private VBox nameBox, slotsBox;

    public ObjectShape(String name) {
        createVisualComponents(name);
    }

    private void createVisualComponents(String name) {
        nameBox = new VBox();
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setPadding(new Insets(5));
        nameBox.setStyle("-fx-border-color: black; -fx-border-width: 0 0 1 0;");

        Label objectName = new Label(name);
        objectName.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        nameBox.getChildren().add(objectName);

        slotsBox = new VBox();
        slotsBox.setPadding(new Insets(5));

        this.getChildren().addAll(nameBox, slotsBox);
        this.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #FFF8DC;");
    }

    public void addSlot(String slot) {
        slotsBox.getChildren().add(new Label(slot));
    }

    public VBox getNameBox() { return nameBox; }
    public VBox getSlotsBox() { return slotsBox; }
}