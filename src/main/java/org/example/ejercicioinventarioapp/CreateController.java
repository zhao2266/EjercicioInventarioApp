package org.example.ejercicioinventarioapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class CreateController {

    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private DatePicker dpFecha;
    @FXML private ComboBox<String> cbEstado;

    private final RegistroDAO registroDAO = new RegistroDAO();

    @FXML
    public void initialize() {
        cbEstado.getItems().addAll("Activo", "Pendiente", "Inactivo");
    }

    @FXML
    private void onGuardar() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "El nombre es obligatorio.");
            return;
        }
        if (dpFecha.getValue() == null) {
            alerta(Alert.AlertType.WARNING, "Selecciona una fecha.");
            return;
        }
        if (cbEstado.getValue() == null) {
            alerta(Alert.AlertType.WARNING, "Selecciona un estado.");
            return;
        }

        Registro r = new Registro(0, nombre, txtDescripcion.getText().trim(),
                dpFecha.getValue(), cbEstado.getValue());
        try {
            registroDAO.insert(r);
            HelloController.goToMain();
        } catch (SQLException e) {
            alerta(Alert.AlertType.ERROR, "No se pudo guardar: " + e.getMessage());
        }
    }

    @FXML private void onCancelar() { HelloController.goToMain(); }

    private void alerta(Alert.AlertType tipo, String msg) {
        new Alert(tipo, msg).showAndWait();
    }
}