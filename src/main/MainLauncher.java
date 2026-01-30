package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainLauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Directly launch the UnifiedDiagramEditor
        new UnifiedDiagramEditor().start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}