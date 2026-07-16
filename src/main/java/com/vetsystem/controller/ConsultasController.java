
package com.vetsystem.controller;

import com.vetsystem.dao.ConsultaDAO;
import com.vetsystem.model.Consulta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ConsultasController implements Initializable {
    @FXML private TextField txtMotivo;
    @FXML private TextField txtDiagnostico;
    @FXML private TextField txtTratamiento;
    @FXML private TextField txtIdPaciente;
    @FXML private TextField txtIdVeterinario;
    @FXML private TextField txtFiltro;
    @FXML private Label lblMensaje;
    @FXML private TableView<Consulta> tablaConsultas;
    @FXML private TableColumn<Consulta, Integer> colId;
    @FXML private TableColumn<Consulta, Date> colFecha;
    @FXML private TableColumn<Consulta, String> colMotivo;
    @FXML private TableColumn<Consulta, String> colDiagnostico;
    @FXML private TableColumn<Consulta, String> colTratamiento;
    @FXML private TableColumn<Consulta, Integer> colIdPaciente;
    @FXML private TableColumn<Consulta, Integer> colIdVeterinario;

    private ConsultaDAO dao = new ConsultaDAO();
    private ObservableList<Consulta> lista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colDiagnostico.setCellValueFactory(new PropertyValueFactory<>("diagnostico"));
        colTratamiento.setCellValueFactory(new PropertyValueFactory<>("tratamiento"));
        colIdPaciente.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
        colIdVeterinario.setCellValueFactory(new PropertyValueFactory<>("idVeterinario"));
        cargarTabla();

        // Lambda — seleccionar fila y cargar en formulario
        tablaConsultas.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> {
                if (newVal != null) {
                    txtMotivo.setText(newVal.getMotivo());
                    txtDiagnostico.setText(newVal.getDiagnostico());
                    txtTratamiento.setText(newVal.getTratamiento());
                    txtIdPaciente.setText(String.valueOf(newVal.getIdPaciente()));
                    txtIdVeterinario.setText(String.valueOf(newVal.getIdVeterinario()));
                }
            }
        );
    }

    private void cargarTabla() {
        lista.clear();
        lista.addAll(dao.listarTodos());
        tablaConsultas.setItems(lista);
    }

    @FXML
    public void registrar() {
        try {
            Consulta c = new Consulta(
                0,
                new Date(),
                txtMotivo.getText(),
                txtDiagnostico.getText(),
                txtTratamiento.getText(),
                Integer.parseInt(txtIdPaciente.getText()),
                Integer.parseInt(txtIdVeterinario.getText())
            );
            dao.insertar(c);
            cargarTabla();
            limpiar();
            lblMensaje.setText("Consulta registrada correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            lblMensaje.setText("Error al registrar: " + e.getMessage());
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void filtrar() {
        // Lambda — filtrar por ID paciente
        try {
            int idPaciente = Integer.parseInt(txtFiltro.getText());
            List<Consulta> filtradas = dao.listarTodos()
                .stream()
                .filter(c -> c.getIdPaciente() == idPaciente)
                .collect(Collectors.toList());
            lista.clear();
            lista.addAll(filtradas);
            tablaConsultas.setItems(lista);
            lblMensaje.setText("Mostrando " + filtradas.size() + " consulta(s) del paciente ID: " + idPaciente);
            lblMensaje.setStyle("-fx-text-fill: blue;");
        } catch (Exception e) {
            lblMensaje.setText("Ingrese un ID válido.");
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void limpiar() {
        txtMotivo.clear();
        txtDiagnostico.clear();
        txtTratamiento.clear();
        txtIdPaciente.clear();
        txtIdVeterinario.clear();
        txtFiltro.clear();
        cargarTabla();
        lblMensaje.setText("");
    }
}
