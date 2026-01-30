package main.shapes;

import main.core.Connectable;
import main.core.Connection;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.List;

public class ActorShape extends Pane implements Connectable {
    private List<Connection> connections = new ArrayList<>();

    public ActorShape() {
        // Head
        Circle head = new Circle(15, Color.BLACK);
        head.setCenterX(20);
        head.setCenterY(15);

        // Body
        Line body = new Line(20, 30, 20, 60);
        Line leftArm = new Line(20, 40, 0, 25);
        Line rightArm = new Line(20, 40, 40, 25);
        Line leftLeg = new Line(20, 60, 5, 80);
        Line rightLeg = new Line(20, 60, 35, 80);

        getChildren().addAll(head, body, leftArm, rightArm, leftLeg, rightLeg);
        setPrefSize(40, 85);

        setupMouseHandlers();
    }

    private void setupMouseHandlers() {
        this.setOnMousePressed(e -> e.consume());
        this.setOnMouseDragged(e -> e.consume());
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
}