package main.ui;

import javafx.scene.control.*;

public class ToolbarBuilder {

    public static void addClassDiagramTools(ToolBar toolBar, Runnable onAddClass, Runnable onAddInterface, Runnable onAddRelation, Runnable onDelete) {
        Button classBtn = new Button("Class");
        classBtn.setOnAction(e -> onAddClass.run());

        Button interfaceBtn = new Button("Interface");
        interfaceBtn.setOnAction(e -> onAddInterface.run());

        Button relationBtn = new Button("Relation");
        relationBtn.setOnAction(e -> onAddRelation.run());

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> onDelete.run());

        toolBar.getItems().addAll(classBtn, interfaceBtn, relationBtn, deleteBtn);

        toolBar.requestLayout();
    }

    public static void addActivityDiagramTools(ToolBar toolBar, Runnable onAddStart, Runnable onAddActivity, Runnable onAddDecision, Runnable onAddEnd, Runnable onAddControlFlow, Runnable onDelete) {
        Button startNodeBtn = new Button("Start Node");
        startNodeBtn.setOnAction(e -> onAddStart.run());

        Button activityBtn = new Button("Activity");
        activityBtn.setOnAction(e -> onAddActivity.run());

        Button decisionBtn = new Button("Decision");
        decisionBtn.setOnAction(e -> onAddDecision.run());

        Button endNodeBtn = new Button("End Node");
        endNodeBtn.setOnAction(e -> onAddEnd.run());

        Button controlFlowBtn = new Button("Control Flow");
        controlFlowBtn.setOnAction(e -> onAddControlFlow.run());

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> onDelete.run());

        toolBar.getItems().addAll(startNodeBtn, activityBtn, decisionBtn, endNodeBtn, controlFlowBtn, new Separator(), deleteBtn);
        toolBar.requestLayout();

    }

    public static void addObjectDiagramTools(ToolBar toolBar, Runnable onAddObject, Runnable onAddLink, Runnable onDelete) {
        Button objectBtn = new Button("Object");
        objectBtn.setOnAction(e -> onAddObject.run());

        Button linkBtn = new Button("Link");
        linkBtn.setOnAction(e -> onAddLink.run());

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> onDelete.run());

        toolBar.getItems().addAll(objectBtn, linkBtn, new Separator(), deleteBtn);
        toolBar.requestLayout();

    }

    public static void addUseCaseDiagramTools(ToolBar toolBar, Runnable onAddActor, Runnable onAddUseCase,
                                              Runnable onAddRelation, Runnable onDelete) {
        Button actorBtn = new Button("Actor");
        actorBtn.setOnAction(e -> onAddActor.run());

        Button useCaseBtn = new Button("Use Case");
        useCaseBtn.setOnAction(e -> onAddUseCase.run());

        Button relationBtn = new Button("Relation");
        relationBtn.setOnAction(e -> onAddRelation.run());

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> onDelete.run());

        toolBar.getItems().addAll(actorBtn, useCaseBtn, relationBtn, new Separator(), deleteBtn);
        toolBar.requestLayout();

    }
}