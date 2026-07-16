
package com.vetsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {
    
    @FXML private Button btnCerrarSesion;

    @FXML
    public void abrirPacientes() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/vetsystem/view/Pacientes.fxml"));
            Scene scene = new Scene(root, 700, 500);
            Stage stage = new Stage();
            stage.setTitle("VetSystem - Pacientes");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void abrirDuenos() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/vetsystem/view/Duenos.fxml"));
            Scene scene = new Scene(root, 700, 500);
            Stage stage = new Stage();
            stage.setTitle("VetSystem - Dueños");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void abrirConsultas() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/vetsystem/view/Consultas.fxml"));
            Scene scene = new Scene(root, 700, 500);
            Stage stage = new Stage();
            stage.setTitle("VetSystem - Consultas");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void abrirMedicamentos() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/vetsystem/view/Medicamentos.fxml"));
            Scene scene = new Scene(root, 700, 500);
            Stage stage = new Stage();
            stage.setTitle("VetSystem - Medicamentos");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void cerrarSesion() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/vetsystem/view/Login.fxml"));
            Scene scene = new Scene(root, 400, 300);

            // Abrir Login
            Stage stageLogin = new Stage();
            stageLogin.setTitle("VetSystem - Login");
            stageLogin.setScene(scene);
            stageLogin.show();

            // Cerrar Dashboard
            Stage stageDashboard = (Stage) btnCerrarSesion.getScene().getWindow();
            stageDashboard.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
