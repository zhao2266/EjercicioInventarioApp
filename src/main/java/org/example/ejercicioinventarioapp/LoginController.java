package org.example.ejercicioinventarioapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void onLogin() {
        String user = txtUsuario.getText().trim();
        String pass = txtPassword.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "Introduce usuario y contraseña.");
            return;
        }
        try {
            if (usuarioDAO.validar(user, pass)) {
                HelloController.goToMain();
            } else {
                alerta(Alert.AlertType.ERROR, "Usuario o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            alerta(Alert.AlertType.ERROR, "Error de base de datos: " + e.getMessage());
        }
    }

    private void alerta(Alert.AlertType tipo, String msg) {
        new Alert(tipo, msg).showAndWait();
    }
}