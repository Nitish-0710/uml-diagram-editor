package main.ui;

import main.shapes.ClassShape;
import main.shapes.ObjectShape;
import main.shapes.ActivityNode;
import main.shapes.UseCaseShape;
import main.shapes.ActorShape;
import main.core.DiagramElement;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class EditDialog extends Dialog<Void> {

    // Main constructor that takes DiagramElement and routes to appropriate constructor
    public EditDialog(DiagramElement element) {
        if (element instanceof ClassShape) {
            setupForClassShape((ClassShape) element);
        } else if (element instanceof ObjectShape) {
            setupForObjectShape((ObjectShape) element);
        } else if (element instanceof ActivityNode) {
            setupForActivityNode((ActivityNode) element);
        } else if (element instanceof UseCaseShape) {
            setupForUseCaseShape((UseCaseShape) element);
        } else if (element instanceof ActorShape) {
            setupForActorShape((ActorShape) element);
        } else {
            setTitle("Edit Element");
            getDialogPane().setContent(new Label("Editing not supported for this element type."));
            getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        }
    }

    private void setupForClassShape(ClassShape shape) {
        setTitle("Edit UML " + shape.getType());

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField(((Label) shape.getNameBox().getChildren().get(shape.getType().equals("Interface") ? 1 : 0)).getText());

        Label attrLabel = new Label("Attributes (one per line):");
        TextArea attrArea = new TextArea();
        shape.getAttributesBox().getChildren().forEach(node -> {
            if (node instanceof Label) attrArea.appendText(((Label) node).getText() + "\n");
        });

        Label methodLabel = new Label("Methods (one per line):");
        TextArea methodArea = new TextArea();
        shape.getMethodsBox().getChildren().forEach(node -> {
            if (node instanceof Label) methodArea.appendText(((Label) node).getText() + "\n");
        });

        setupDialogGrid(nameLabel, nameField, attrLabel, attrArea, methodLabel, methodArea);

        setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                updateClassShape(shape, nameField.getText(), attrArea.getText(), methodArea.getText());
            }
            return null;
        });
    }

    private void setupForObjectShape(ObjectShape shape) {
        setTitle("Edit Object");

        Label nameLabel = new Label("Object Name:");
        TextField nameField = new TextField(((Label) shape.getNameBox().getChildren().get(0)).getText());

        Label slotLabel = new Label("Slots (one per line):");
        TextArea slotArea = new TextArea();
        shape.getSlotsBox().getChildren().forEach(node -> {
            if (node instanceof Label) slotArea.appendText(((Label) node).getText() + "\n");
        });

        setupDialogGrid(nameLabel, nameField, slotLabel, slotArea, null, null);

        setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                updateObjectShape(shape, nameField.getText(), slotArea.getText());
            }
            return null;
        });
    }

    private void setupForActivityNode(ActivityNode node) {
        setTitle("Edit " + node.getType());

        Label nameLabel = new Label("Label:");
        TextField nameField = new TextField(node.getLabel() != null ? node.getLabel().getText() : "");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        setResultConverter(btn -> {
            if (btn == ButtonType.OK && node.getLabel() != null) {
                node.getLabel().setText(nameField.getText());
            }
            return null;
        });
    }

    private void setupForUseCaseShape(UseCaseShape shape) {
        setTitle("Edit Use Case");

        Label nameLabel = new Label("Use Case Name:");
        TextField nameField = new TextField(((Label) shape.getNameBox().getChildren().get(0)).getText());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                shape.getNameBox().getChildren().clear();
                Label newName = new Label(nameField.getText());
                newName.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                shape.getNameBox().getChildren().add(newName);
            }
            return null;
        });
    }

    private void setupForActorShape(ActorShape shape) {
        setTitle("Actor");
        getDialogPane().setContent(new Label("Actors don't have editable properties."));
        getDialogPane().getButtonTypes().addAll(ButtonType.OK);
    }

    private void setupDialogGrid(Label nameLabel, TextField nameField, Label area1Label, TextArea area1,
                                 Label area2Label, TextArea area2) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        if (area1Label != null && area1 != null) {
            grid.add(area1Label, 0, 1);
            grid.add(area1, 1, 1);
        }

        if (area2Label != null && area2 != null) {
            grid.add(area2Label, 0, 2);
            grid.add(area2, 1, 2);
        }

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    }

    private void updateClassShape(ClassShape shape, String name, String attributes, String methods) {
        shape.getNameBox().getChildren().clear();
        if (shape.getType().equals("Interface")) {
            Label interfaceLabel = new Label("«interface»");
            interfaceLabel.setStyle("-fx-font-style: italic; -fx-font-size: 10;");
            shape.getNameBox().getChildren().add(interfaceLabel);
        }
        Label newName = new Label(name);
        newName.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        shape.getNameBox().getChildren().add(newName);

        shape.getAttributesBox().getChildren().clear();
        for (String line : attributes.split("\\n")) {
            if (!line.trim().isEmpty()) shape.addAttribute(line.trim());
        }

        shape.getMethodsBox().getChildren().clear();
        for (String line : methods.split("\\n")) {
            if (!line.trim().isEmpty()) shape.addMethod(line.trim());
        }
    }

    private void updateObjectShape(ObjectShape shape, String name, String slots) {
        shape.getNameBox().getChildren().clear();
        Label newName = new Label(name);
        newName.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        shape.getNameBox().getChildren().add(newName);

        shape.getSlotsBox().getChildren().clear();
        for (String line : slots.split("\\n")) {
            if (!line.trim().isEmpty()) shape.addSlot(line.trim());
        }
    }
}