package main.shapes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ClassShape extends BaseShape {
    private VBox nameBox, attributesBox, methodsBox;
    private String type;

    public ClassShape(String type, String name) {
        this.type = type;
        createVisualComponents(name);
    }

    private void createVisualComponents(String name) {
        nameBox = new VBox();
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setPadding(new Insets(5));
        nameBox.setStyle("-fx-border-color: black; -fx-border-width: 0 0 1 0;");

        if (type.equals("Interface")) {
            Label interfaceLabel = new Label("«interface»");
            interfaceLabel.setStyle("-fx-font-style: italic; -fx-font-size: 10;");
            Label className = new Label(name);
            className.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
            nameBox.getChildren().addAll(interfaceLabel, className);
        } else {
            Label className = new Label(name);
            className.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
            nameBox.getChildren().add(className);
        }

        attributesBox = new VBox();
        attributesBox.setPadding(new Insets(5));
        attributesBox.setStyle("-fx-border-color: black; -fx-border-width: 0 0 1 0;");

        methodsBox = new VBox();
        methodsBox.setPadding(new Insets(5));

        this.getChildren().addAll(nameBox, attributesBox, methodsBox);
        this.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: lightyellow;");
    }

    public void addAttribute(String attr) {
        attributesBox.getChildren().add(new Label(attr));
    }

    public void addMethod(String method) {
        methodsBox.getChildren().add(new Label(method));
    }

    public String getType() { return type; }
    public VBox getNameBox() { return nameBox; }
    public VBox getAttributesBox() { return attributesBox; }
    public VBox getMethodsBox() { return methodsBox; }
}