package main.core;

import javafx.scene.layout.Pane;

public interface DiagramElement {
    void removeAllConnections(Pane canvas);
    double getShapeWidth();
    double getShapeHeight();
}