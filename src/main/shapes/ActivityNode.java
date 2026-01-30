package main.shapes;

import main.core.Connectable;
import main.core.Connection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityNode extends StackPane implements Connectable {
    private String type;
    private Shape shape;
    private Label label;
    private List<Connection> connections = new ArrayList<>();

    public ActivityNode(String type) {
        this.type = type;
        createShape();
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));
        if (shape != null) this.getChildren().add(shape);
        if (label != null) this.getChildren().add(label);

        setupMouseHandlers();
    }

    private void createShape() {
        switch (type) {
            case "Start":
                shape = new Circle(20, Color.BLACK);
                break;
            case "End":
                Circle outer = new Circle(22, Color.TRANSPARENT);
                outer.setStroke(Color.BLACK);
                Circle inner = new Circle(14, Color.BLACK);
                StackPane end = new StackPane(outer, inner);
                this.getChildren().add(end);
                return;
            case "Activity":
                Rectangle rect = new Rectangle(120, 50, Color.LIGHTBLUE);
                rect.setArcWidth(20);
                rect.setArcHeight(20);
                rect.setStroke(Color.BLACK);
                shape = rect;
                label = new Label("Activity");
                break;
            case "Decision":
            case "Merge":
                Polygon diamond = new Polygon(0, -30, 40, 0, 0, 30, -40, 0);
                diamond.setFill(Color.LIGHTGOLDENRODYELLOW);
                diamond.setStroke(Color.BLACK);
                shape = diamond;
                label = new Label(type);
                break;
        }
    }

    private void setupMouseHandlers() {
        this.setOnMousePressed(e -> {
            e.consume();
        });

        this.setOnMouseDragged(e -> {
            e.consume();
        });
    }

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
        return this.getBoundsInParent().getWidth();
    }

    @Override
    public double getShapeHeight() {
        return this.getBoundsInParent().getHeight();
    }

    public String getType() { return type; }
    public Label getLabel() { return label; }
}