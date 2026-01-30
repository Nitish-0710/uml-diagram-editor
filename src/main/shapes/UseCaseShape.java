package main.shapes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import main.core.Connectable;
import main.core.Connection;

import java.util.ArrayList;
import java.util.List;

public class UseCaseShape extends StackPane implements Connectable {
    private VBox nameBox;
    private Ellipse ellipse;
    private String useCaseName;
    private List<Connection> connections = new ArrayList<>();

    public UseCaseShape(String name) {
        this.useCaseName = name;
        createVisualComponents();
    }

    private void createVisualComponents() {
        ellipse = new Ellipse(80, 40);
        ellipse.setFill(Color.LIGHTYELLOW);
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(1.5);

        nameBox = new VBox();
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setPadding(new Insets(10));
        nameBox.setMinSize(160, 80);
        nameBox.setMaxSize(160, 80);

        Label nameLabel = new Label(useCaseName);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12; -fx-text-fill: black;");
        nameBox.getChildren().add(nameLabel);

        this.getChildren().addAll(ellipse, nameBox);
        this.setPrefSize(160, 80);
        this.setMinSize(160, 80);
        this.setMaxSize(160, 80);
        this.setStyle("-fx-background-color: transparent;");
    }

    // Connectable interface methods
    @Override
    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    @Override
    public void removeConnection(Connection connection) {
        connections.remove(connection);
    }

    @Override
    public void removeAllConnections(Pane canvas) {
        for (Connection connection : new ArrayList<>(connections)) {
            connection.removeFrom(canvas);
        }
        connections.clear();
    }

    @Override
    public double getShapeWidth() {
        return 160;
    }

    @Override
    public double getShapeHeight() {
        return 80;
    }

    // Getters and setters
    public VBox getNameBox() { return nameBox; }

}
