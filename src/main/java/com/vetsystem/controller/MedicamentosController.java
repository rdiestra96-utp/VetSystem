
package com.vetsystem.controller;

import com.vetsystem.dao.MedicamentoDAO;
import com.vetsystem.model.Medicamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MedicamentosController implements Initializable {
    @FXML private TextField txtNombre;
    @FXML private TextField txtDosis;
    @FXML private TextField txtStock;
    @FXML private TextField txtFiltro;
    @FXML private Label lblMensaje;
    @FXML private TableView<Medicamento> tablaMedicamentos;
    @FXML private TableColumn<Medicamento, Integer> colId;
    @FXML private TableColumn<Medicamento, String> colNombre;
    @FXML private TableColumn<Medicamento, String> colDosis;
    @FXML private TableColumn<Medicamento, Integer> colStock;

    private MedicamentoDAO dao = new MedicamentoDAO();
    private ObservableList<Medicamento> lista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDosis.setCellValueFactory(new PropertyValueFactory<>("dosis"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        cargarTabla();

        // Lambda — seleccionar fila y cargar en formulario
        tablaMedicamentos.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> {
                if (newVal != null) {
                    txtNombre.setText(newVal.getNombre());
                    txtDosis.setText(newVal.getDosis());
                    txtStock.setText(String.valueOf(newVal.getStock()));
                }
            }
        );
    }

    private void cargarTabla() {
        lista.clear();
        lista.addAll(dao.listarTodos());
        tablaMedicamentos.setItems(lista);
    }

    @FXML
    public void agregar() {
        try {
            Medicamento m = new Medicamento(
                0,
                txtNombre.getText(),
                txtDosis.getText(),
                Integer.parseInt(txtStock.getText())
            );
            dao.insertar(m);
            cargarTabla();
            limpiar();
            lblMensaje.setText("Medicamento agregado correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            lblMensaje.setText("Error al agregar: " + e.getMessage());
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void actualizar() {
        Medicamento seleccionado = tablaMedicamentos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblMensaje.setText("Seleccione un medicamento de la tabla.");
            lblMensaje.setStyle("-fx-text-fill: red;");
            return;
        }
        try {
            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setDosis(txtDosis.getText());
            seleccionado.setStock(Integer.parseInt(txtStock.getText()));
            dao.actualizar(seleccionado);
            cargarTabla();
            limpiar();
            lblMensaje.setText("Medicamento actualizado correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            lblMensaje.setText("Error al actualizar: " + e.getMessage());
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void eliminar() {
        Medicamento seleccionado = tablaMedicamentos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblMensaje.setText("Seleccione un medicamento de la tabla.");
            lblMensaje.setStyle("-fx-text-fill: red;");
            return;
        }
        dao.eliminar(seleccionado.getId());
        cargarTabla();
        limpiar();
        lblMensaje.setText("Medicamento eliminado correctamente.");
        lblMensaje.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void buscar() {
        // Lambda — buscar por nombre
        String nombre = txtFiltro.getText();
        List<Medicamento> filtrados = dao.buscarPorNombre(nombre);
        lista.clear();
        lista.addAll(filtrados);
        tablaMedicamentos.setItems(lista);
        lblMensaje.setText("Mostrando " + filtrados.size() + " medicamento(s).");
        lblMensaje.setStyle("-fx-text-fill: blue;");
    }

    @FXML
    public void limpiar() {
        txtNombre.clear();
        txtDosis.clear();
        txtStock.clear();
        txtFiltro.clear();
        cargarTabla();
        lblMensaje.setText("");
    }
}
