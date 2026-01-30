package main.ui;

import main.core.BaseDiagramEditor;
import main.core.DiagramElement;
import javafx.scene.input.MouseEvent;

public class ShapeEditingHelper {

    public static void enableEditing(BaseDiagramEditor editor) {
        editor.getCanvas().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getClickCount() == 2 && editor.getSelectedElement() != null) {
                new EditDialog(editor.getSelectedElement()).showAndWait();
            }
        });
    }

    public static void setupShapeMouseHandlers(DiagramElement shape, BaseDiagramEditor editor) {
        if (shape instanceof javafx.scene.Node) {
            javafx.scene.Node node = (javafx.scene.Node) shape;

            node.setOnMousePressed(e -> {
                editor.setSelectedElement(shape);
                editor.setOffsetX(e.getSceneX() - node.getLayoutX());
                editor.setOffsetY(e.getSceneY() - node.getLayoutY());
                if (editor.isConnectionMode() && shape instanceof main.core.Connectable) {
                    editor.performConnectionClick((main.core.Connectable) shape);
                }
                e.consume();
            });

            node.setOnMouseDragged(e -> {
                node.setLayoutX(e.getSceneX() - editor.getOffsetX());
                node.setLayoutY(e.getSceneY() - editor.getOffsetY());
                e.consume();
            });
        }
    }
}