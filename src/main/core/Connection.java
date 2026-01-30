package main.core;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Connection {
    protected Line line;
    protected Polygon arrowHead;
    protected Connectable from, to;

    public Connection(Connectable from, Connectable to, Line line, Polygon arrow) {
        this.from = from;
        this.to = to;
        this.line = line;
        this.arrowHead = arrow;
    }

    public void removeFrom(Pane canvas) {
        canvas.getChildren().removeAll(line, arrowHead);
        from.removeConnection(this);
        to.removeConnection(this);
    }

    // Getters
    public Line getLine() { return line; }
    public Polygon getArrowHead() { return arrowHead; }
    public Connectable getFrom() { return from; }
    public Connectable getTo() { return to; }
}