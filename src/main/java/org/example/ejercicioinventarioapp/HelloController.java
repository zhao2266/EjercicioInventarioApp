package org.example.ejercicioinventarioapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private static Stage stage;

    public static void init(Stage primaryStage) {
        stage = primaryStage;
    }

    private static void goTo(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    HelloController.class.getResource(fxml));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar la vista " + fxml, e);
        }
    }

    public static void goToLogin()  { goTo("login.fxml",  "Login"); }
    public static void goToMain()   { goTo("main.fxml",   "Listado de registros"); }
    public static void goToCreate() { goTo("create.fxml", "Nuevo registro"); }
}
