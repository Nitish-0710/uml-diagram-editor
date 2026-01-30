package main.core;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public abstract class BaseDiagramEditor extends Application {
    protected Pane canvas;
    protected DiagramElement selectedElement;
    protected double offsetX, offsetY;
    protected boolean connectionMode = false;
    protected Connectable connectionSource = null;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = createBaseUI();
        setupCanvas();
        primaryStage.setTitle(getEditorTitle());
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    protected BorderPane createBaseUI() {
        BorderPane root = new BorderPane();

        // Top: Toolbar
        ToolBar topToolbar = createToolbar();
        topToolbar.setMaxWidth(Double.MAX_VALUE); // allow toolbar to expand across the window
        root.setTop(topToolbar);

        // Center: Canvas
        canvas = new Pane();
        canvas.setStyle("-fx-background-color: white; -fx-border-color: #E0E0E0; -fx-border-width: 1;");
        TabPane tabPane = new TabPane();
        Tab page1 = new Tab("Page 1", canvas);
        page1.setClosable(false);
        tabPane.getTabs().add(page1);
        root.setCenter(tabPane);

        // Bottom: Zoom Bar
        HBox bottomBar = createBottomBar();
        root.setBottom(bottomBar);

        return root;
    }

    protected HBox createBottomBar() {
        HBox bottomBar = new HBox(7);
        bottomBar.setPadding(new Insets(16, 5, 16, 5));
        bottomBar.setStyle("-fx-background-color: #F8F9FA; -fx-border-color: #E0E0E0; -fx-border-width: 1 0 0 0;");

        Label createdByLabel = new Label("Created by Nitish Sahu, Palash Sahuji, Manthan Sali, Aditya Rana");
        createdByLabel.setStyle("-fx-text-fill: #666666; -fx-font-size: 14px;");


        bottomBar.getChildren().addAll(createdByLabel);
        return bottomBar;
    }


    protected abstract ToolBar createToolbar();
    protected abstract String getEditorTitle();
    protected abstract void setupCanvas();

    // Common connection handling - protected for subclasses
    protected void handleConnectionClick(Connectable element) {
        if (connectionSource == null) {
            connectionSource = element;
        } else if (connectionSource != element) {
            ConnectionUtils.connectWithArrow(this, connectionSource, element);
            connectionSource = null;
            connectionMode = false;
        }
    }

    // Public method for external classes to handle connection clicks
    public void performConnectionClick(Connectable element) {
        handleConnectionClick(element);
    }

    // Public accessor methods
    public Pane getCanvas() { return canvas; }
    public boolean isConnectionMode() { return connectionMode; }
    public void setConnectionMode(boolean mode) { this.connectionMode = mode; }
    public void setConnectionSource(Connectable source) { this.connectionSource = source; }

    // Add accessor methods for selectedElement
    public DiagramElement getSelectedElement() { return selectedElement; }
    public void setSelectedElement(DiagramElement element) { this.selectedElement = element; }

    // Add accessor methods for offset coordinates
    public double getOffsetX() { return offsetX; }
    public void setOffsetX(double offsetX) { this.offsetX = offsetX; }
    public double getOffsetY() { return offsetY; }
    public void setOffsetY(double offsetY) { this.offsetY = offsetY; }
}