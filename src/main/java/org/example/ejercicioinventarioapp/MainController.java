package org.example.ejercicioinventarioapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;

public class MainController {

    @FXML private TableView<Registro> tabla;
    @FXML private TableColumn<Registro, Integer> colId;
    @FXML private TableColumn<Registro, String> colNombre;
    @FXML private TableColumn<Registro, LocalDate> colFecha;
    @FXML private TableColumn<Registro, String> colEstado;

    private final RegistroDAO registroDAO = new RegistroDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        cargarDatos();
    }

    private void cargarDatos() {
        try {
            tabla.setItems(FXCollections.observableArrayList(registroDAO.findAll()));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "No se pudieron cargar los datos: " + e.getMessage()).showAndWait();
        }
    }

    @FXML private void onNuevo()  { HelloController.goToCreate(); }
    @FXML private void onLogout() { HelloController.goToLogin(); }
}