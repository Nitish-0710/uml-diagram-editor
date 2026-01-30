package main.shapes;

import main.core.Connectable;
import main.core.Connection;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseShape extends VBox implements Connectable {
    protected List<Connection> connections = new ArrayList<>();

    public BaseShape() {
        this.setSpacing(0);
        setupMouseHandlers();
    }

    protected void setupMouseHandlers() {
        this.setOnMousePressed(e -> {
            // Selection and dragging handled by editor
            e.consume();
        });

        this.setOnMouseDragged(e -> {
            // Dragging handled by editor  
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

    // Use different method names since getWidth() and getHeight() are final in Region
    public double getShapeWidth() {
        return this.getBoundsInParent().getWidth();
    }

    public double getShapeHeight() {
        return this.getBoundsInParent().getHeight();
    }
}