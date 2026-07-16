
package com.vetsystem.controller;

import com.vetsystem.dao.DuenoDAO;
import com.vetsystem.model.Dueno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DuenosController implements Initializable {
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtDni;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtFiltro;
    @FXML private Label lblMensaje;
    @FXML private TableView<Dueno> tablaDuenos;
    @FXML private TableColumn<Dueno, Integer> colId;
    @FXML private TableColumn<Dueno, String> colNombre;
    @FXML private TableColumn<Dueno, String> colApellido;
    @FXML private TableColumn<Dueno, String> colDni;
    @FXML private TableColumn<Dueno, String> colTelefono;
    @FXML private TableColumn<Dueno, String> colDireccion;

    private DuenoDAO dao = new DuenoDAO();
    private ObservableList<Dueno> lista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        cargarTabla();

        // Lambda — seleccionar fila y cargar en formulario
        tablaDuenos.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> {
                if (newVal != null) {
                    txtNombre.setText(newVal.getNombre());
                    txtApellido.setText(newVal.getApellido());
                    txtDni.setText(newVal.getDni());
                    txtTelefono.setText(newVal.getTelefono());
                    txtDireccion.setText(newVal.getDireccion());
                }
            }
        );
    }

    private void cargarTabla() {
        lista.clear();
        lista.addAll(dao.listarTodos());
        tablaDuenos.setItems(lista);
    }

    @FXML
    public void agregar() {
        try {
            Dueno d = new Dueno(
                0,
                txtNombre.getText(),
                txtApellido.getText(),
                txtDni.getText(),
                txtTelefono.getText(),
                txtDireccion.getText()
            );
            dao.insertar(d);
            cargarTabla();
            limpiar();
            lblMensaje.setText("Dueño agregado correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            lblMensaje.setText("Error al agregar: " + e.getMessage());
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void eliminar() {
        Dueno seleccionado = tablaDuenos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblMensaje.setText("Seleccione un dueño de la tabla.");
            lblMensaje.setStyle("-fx-text-fill: red;");
            return;
        }
        // Lambda — confirmar eliminación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setContentText("¿Eliminar al dueño " + seleccionado.getNombre() + "?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                dao.eliminar(seleccionado.getId());
                cargarTabla();
                limpiar();
                lblMensaje.setText("Dueño eliminado correctamente.");
                lblMensaje.setStyle("-fx-text-fill: green;");
            }
        });
    }

    @FXML
    public void buscar() {
        // Lambda — buscar por nombre
        String nombre = txtFiltro.getText();
        List<Dueno> filtrados = dao.listarTodos()
            .stream()
            .filter(d -> d.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .collect(java.util.stream.Collectors.toList());
        lista.clear();
        lista.addAll(filtrados);
        tablaDuenos.setItems(lista);
        lblMensaje.setText("Mostrando " + filtrados.size() + " dueño(s).");
        lblMensaje.setStyle("-fx-text-fill: blue;");
    }

    @FXML
    public void limpiar() {
        txtNombre.clear();
        txtApellido.clear();
        txtDni.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        txtFiltro.clear();
        cargarTabla();
        lblMensaje.setText("");
    }
}
