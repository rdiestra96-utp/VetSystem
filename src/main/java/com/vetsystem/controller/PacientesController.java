
package com.vetsystem.controller;

import com.vetsystem.dao.PacienteDAO;
import com.vetsystem.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PacientesController implements Initializable {
    @FXML private TextField txtNombre;
    @FXML private TextField txtEspecie;
    @FXML private TextField txtRaza;
    @FXML private TextField txtEdad;
    @FXML private TextField txtPeso;
    @FXML private TextField txtIdDueno;
    @FXML private TextField txtFiltro;
    @FXML private Label lblMensaje;
    @FXML private TableView<Paciente> tablaPacientes;
    @FXML private TableColumn<Paciente, Integer> colId;
    @FXML private TableColumn<Paciente, String> colNombre;
    @FXML private TableColumn<Paciente, String> colEspecie;
    @FXML private TableColumn<Paciente, String> colRaza;
    @FXML private TableColumn<Paciente, Integer> colEdad;
    @FXML private TableColumn<Paciente, Double> colPeso;
    @FXML private TableColumn<Paciente, Integer> colIdDueno;

    private PacienteDAO dao = new PacienteDAO();
    private ObservableList<Paciente> lista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colIdDueno.setCellValueFactory(new PropertyValueFactory<>("idDueno"));
        cargarTabla();

        // Lambda — seleccionar fila y cargar en formulario
        tablaPacientes.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> {
                if (newVal != null) {
                    txtNombre.setText(newVal.getNombre());
                    txtEspecie.setText(newVal.getEspecie());
                    txtRaza.setText(newVal.getRaza());
                    txtEdad.setText(String.valueOf(newVal.getEdad()));
                    txtPeso.setText(String.valueOf(newVal.getPeso()));
                    txtIdDueno.setText(String.valueOf(newVal.getIdDueno()));
                }
            }
        );
    }

    private void cargarTabla() {
        lista.clear();
        lista.addAll(dao.listarTodos());
        tablaPacientes.setItems(lista);
    }

    @FXML
    public void agregar() {
        try {
            Paciente p = new Paciente(
                0,
                txtNombre.getText(),
                txtEspecie.getText(),
                txtRaza.getText(),
                Integer.parseInt(txtEdad.getText()),
                Double.parseDouble(txtPeso.getText()),
                Integer.parseInt(txtIdDueno.getText())
            );
            dao.insertar(p);
            cargarTabla();
            limpiar();
            lblMensaje.setText("Paciente agregado correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            lblMensaje.setText("Error al agregar: " + e.getMessage());
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void actualizar() {
        Paciente seleccionado = tablaPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblMensaje.setText("Seleccione un paciente de la tabla.");
            lblMensaje.setStyle("-fx-text-fill: red;");
            return;
        }
        try {
            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setEspecie(txtEspecie.getText());
            seleccionado.setRaza(txtRaza.getText());
            seleccionado.setEdad(Integer.parseInt(txtEdad.getText()));
            seleccionado.setPeso(Double.parseDouble(txtPeso.getText()));
            dao.actualizar(seleccionado);
            cargarTabla();
            limpiar();
            lblMensaje.setText("Paciente actualizado correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            lblMensaje.setText("Error al actualizar: " + e.getMessage());
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void eliminar() {
        Paciente seleccionado = tablaPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblMensaje.setText("Seleccione un paciente de la tabla.");
            lblMensaje.setStyle("-fx-text-fill: red;");
            return;
        }
        dao.eliminar(seleccionado.getId());
        cargarTabla();
        limpiar();
        lblMensaje.setText("Paciente eliminado correctamente.");
        lblMensaje.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void filtrar() {
        // Lambda — filtrar por especie
        String especie = txtFiltro.getText();
        List<Paciente> filtrados = dao.listarTodos()
            .stream()
            .filter(p -> p.getEspecie().equalsIgnoreCase(especie))
            .collect(Collectors.toList());
        lista.clear();
        lista.addAll(filtrados);
        tablaPacientes.setItems(lista);
        lblMensaje.setText("Mostrando " + filtrados.size() + " paciente(s) de especie: " + especie);
        lblMensaje.setStyle("-fx-text-fill: blue;");
    }

    @FXML
    public void limpiar() {
        txtNombre.clear();
        txtEspecie.clear();
        txtRaza.clear();
        txtEdad.clear();
        txtPeso.clear();
        txtIdDueno.clear();
        txtFiltro.clear();
        cargarTabla();
        lblMensaje.setText("");
    }
}
