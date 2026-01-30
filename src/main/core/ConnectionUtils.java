package main.core;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class ConnectionUtils {

    public static void connectWithArrow(BaseDiagramEditor editor, Connectable source, Connectable target) {
        Line line = new Line();
        line.setStroke(Color.GRAY);
        Polygon arrowHead = new Polygon();
        arrowHead.setFill(Color.GRAY);

        Connection connection = new Connection(source, target, line, arrowHead);
        source.addConnection(connection);
        target.addConnection(connection);

        Runnable updater = () -> updateLineToBorders(line, arrowHead, source, target);
        updater.run();

        // Add listeners for movement
        if (source instanceof javafx.scene.Node) {
            javafx.scene.Node sourceNode = (javafx.scene.Node) source;
            sourceNode.layoutXProperty().addListener((a, b, c) -> updater.run());
            sourceNode.layoutYProperty().addListener((a, b, c) -> updater.run());
        }
        if (target instanceof javafx.scene.Node) {
            javafx.scene.Node targetNode = (javafx.scene.Node) target;
            targetNode.layoutXProperty().addListener((a, b, c) -> updater.run());
            targetNode.layoutYProperty().addListener((a, b, c) -> updater.run());
        }

        editor.getCanvas().getChildren().addAll(line, arrowHead);
    }

    /** Compute border intersection and arrow placement (from ClassDiagramEditor) */
    private static void updateLineToBorders(Line line, Polygon arrow, Connectable a, Connectable b) {
        double ax = ((javafx.scene.Node) a).getLayoutX();
        double ay = ((javafx.scene.Node) a).getLayoutY();
        double aw = a.getShapeWidth();
        double ah = a.getShapeHeight();

        double bx = ((javafx.scene.Node) b).getLayoutX();
        double by = ((javafx.scene.Node) b).getLayoutY();
        double bw = b.getShapeWidth();
        double bh = b.getShapeHeight();

        double aCenterX = ax + aw / 2;
        double aCenterY = ay + ah / 2;
        double bCenterX = bx + bw / 2;
        double bCenterY = by + bh / 2;

        double dx = bCenterX - aCenterX;
        double dy = bCenterY - aCenterY;

        double[] start = intersectRectangle(aCenterX, aCenterY, aw, ah, dx, dy);
        double[] end = intersectRectangle(bCenterX, bCenterY, bw, bh, -dx, -dy);

        line.setStartX(start[0]);
        line.setStartY(start[1]);
        line.setEndX(end[0]);
        line.setEndY(end[1]);

        // Arrowhead (filled)
        double angle = Math.atan2(end[1] - start[1], end[0] - start[0]);
        double arrowLength = 15;
        double arrowWidth = 10;

        double x1 = end[0] - arrowLength * Math.cos(angle - Math.PI / 6);
        double y1 = end[1] - arrowLength * Math.sin(angle - Math.PI / 6);
        double x2 = end[0] - arrowLength * Math.cos(angle + Math.PI / 6);
        double y2 = end[1] - arrowLength * Math.sin(angle + Math.PI / 6);

        arrow.getPoints().setAll(end[0], end[1], x1, y1, x2, y2);
    }

    /** Intersection of ray from center with rectangle border */
    private static double[] intersectRectangle(double cx, double cy, double w, double h, double dx, double dy) {
        double hw = w / 2.0;
        double hh = h / 2.0;
        double tX = Double.POSITIVE_INFINITY;
        double tY = Double.POSITIVE_INFINITY;

        if (dx != 0) tX = hw / Math.abs(dx);
        if (dy != 0) tY = hh / Math.abs(dy);
        double t = Math.min(tX, tY);

        double x = cx + dx * t;
        double y = cy + dy * t;

        // Clamp inside border
        x = Math.max(cx - hw, Math.min(x, cx + hw));
        y = Math.max(cy - hh, Math.min(y, cy + hh));

        return new double[]{x, y};
    }
}