package main;

import main.core.BaseDiagramEditor;
import main.core.DiagramElement;
import main.shapes.ClassShape;
import main.shapes.ObjectShape;
import main.shapes.ActivityNode;
import main.shapes.ActorShape;
import main.shapes.UseCaseShape;
import main.ui.ShapeEditingHelper;
import main.ui.ToolbarBuilder;
import javafx.scene.control.*;

public class UnifiedDiagramEditor extends BaseDiagramEditor {
    private String currentDiagramType = "Select Diagram Type";
    private ToolBar mainToolbar;

    @Override
    protected ToolBar createToolbar() {
        mainToolbar = new ToolBar();

        // Add diagram type dropdown
        Label diagramTypeLabel = new Label("Diagram Type:");
        ComboBox<String> diagramTypeCombo = new ComboBox<>();
        diagramTypeCombo.getItems().addAll("Class Diagram", "Activity Diagram", "Object Diagram", "UseCase Diagram");
        diagramTypeCombo.setValue(currentDiagramType);
        diagramTypeCombo.setOnAction(e -> {
            switch (diagramTypeCombo.getValue()) {
                case "Class Diagram" -> switchToClassDiagram();
                case "Activity Diagram" -> switchToActivityDiagram();
                case "Object Diagram" -> switchToObjectDiagram();
                case "UseCase Diagram" -> switchToUseCaseDiagram();
            }
        });
        mainToolbar.getItems().addAll(diagramTypeLabel, diagramTypeCombo);

        // Initialize dynamic buttons for current diagram
        updateDynamicToolbarItems(mainToolbar);

        return mainToolbar;
    }

    // Helper method to update toolbar buttons
    private void updateDynamicToolbarItems(ToolBar toolBar) {
        // Clear existing dynamic items
        toolBar.getItems().removeIf(node -> node instanceof Button || node instanceof Separator);

        switch (currentDiagramType) {
            case "Class":
                ToolbarBuilder.addClassDiagramTools(toolBar,
                        this::addClass,
                        this::addInterface,
                        this::enableRelationMode,
                        this::deleteSelected
                );
                break;
            case "Activity":
                ToolbarBuilder.addActivityDiagramTools(toolBar,
                        this::addStartNode,
                        this::addActivity,
                        this::addDecision,
                        this::addEndNode,
                        this::enableControlFlowMode,
                        this::deleteSelected
                );
                break;
            case "Object":
                ToolbarBuilder.addObjectDiagramTools(toolBar,
                        this::addObject,
                        this::enableLinkMode,
                        this::deleteSelected
                );
                break;
            case "UseCase":
                ToolbarBuilder.addUseCaseDiagramTools(toolBar,
                        this::addActor,
                        this::addUseCase,
                        this::enableRelationMode,
                        this::deleteSelected
                );
                break;
        }
    }

    @Override
    protected String getEditorTitle() {
        return "Unified UML Editor - " + currentDiagramType + " Diagram";
    }

    @Override
    protected void setupCanvas() {
        ShapeEditingHelper.enableEditing(this);
        clearCanvas();
    }

    private void switchToClassDiagram() {
        currentDiagramType = "Class";
        updateDynamicToolbarItems(mainToolbar);
        clearCanvas();
    }

    private void switchToActivityDiagram() {
        currentDiagramType = "Activity";
        updateDynamicToolbarItems(mainToolbar);
        clearCanvas();
    }

    private void switchToObjectDiagram() {
        currentDiagramType = "Object";
        updateDynamicToolbarItems(mainToolbar);
        clearCanvas();
    }

    private void switchToUseCaseDiagram() {
        currentDiagramType = "UseCase";
        updateDynamicToolbarItems(mainToolbar);
        clearCanvas();
    }

    // Class Diagram Methods
    private void addClass() {
        ClassShape shape = new ClassShape("Class", "ClassName");
        addShapeToCanvas(shape, 50, 50);
    }

    private void addInterface() {
        ClassShape shape = new ClassShape("Interface", "InterfaceName");
        addShapeToCanvas(shape, 50, 50);
    }

    // Activity Diagram Methods
    private void addStartNode() {
        ActivityNode node = new ActivityNode("Start");
        addShapeToCanvas(node, 100, 100);
    }

    private void addActivity() {
        ActivityNode node = new ActivityNode("Activity");
        addShapeToCanvas(node, 100, 100);
    }

    private void addDecision() {
        ActivityNode node = new ActivityNode("Decision");
        addShapeToCanvas(node, 100, 100);
    }

    private void addEndNode() {
        ActivityNode node = new ActivityNode("End");
        addShapeToCanvas(node, 100, 100);
    }

    // Object Diagram Methods
    private void addObject() {
        ObjectShape shape = new ObjectShape("objectName: ClassName");
        addShapeToCanvas(shape, 50, 50);
    }

    // Use Case Diagram Methods
    private void addActor() {
        ActorShape actor = new ActorShape();
        addShapeToCanvas(actor, 100, 100);
    }

    private void addUseCase() {
        UseCaseShape useCase = new UseCaseShape("UseCaseName");
        addShapeToCanvas(useCase, 200, 200);
    }

    // Connection Modes
    private void enableRelationMode() {
        setConnectionMode(true);
        setConnectionSource(null);
    }

    private void enableControlFlowMode() {
        setConnectionMode(true);
        setConnectionSource(null);
    }

    private void enableLinkMode() {
        setConnectionMode(true);
        setConnectionSource(null);
    }

    // Common Operations
    private void deleteSelected() {
        if (getSelectedElement() != null) {
            getSelectedElement().removeAllConnections(canvas);
            canvas.getChildren().remove(getSelectedElement());
            setSelectedElement(null);
        }
    }

    private void addShapeToCanvas(DiagramElement shape, double x, double y) {
        if (shape instanceof javafx.scene.Node) {
            javafx.scene.Node node = (javafx.scene.Node) shape;
            node.setLayoutX(x);
            node.setLayoutY(y);
            ShapeEditingHelper.setupShapeMouseHandlers(shape, this);
            canvas.getChildren().add(node);
        }
    }

    private void clearCanvas() {
        canvas.getChildren().clear();
        setSelectedElement(null);
        setConnectionSource(null);
        setConnectionMode(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}