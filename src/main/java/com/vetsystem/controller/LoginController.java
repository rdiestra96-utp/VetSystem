
package com.vetsystem.controller;

import com.vetsystem.dao.Conexion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensaje;

    @FXML
    public void login() {
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        if (usuario.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Complete todos los campos.");
            return;
        }

        try {
            Connection con = Conexion.getConexion();
            String sql = "SELECT * FROM usuario WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Login exitoso - abrir Dashboard
                Parent root = FXMLLoader.load(getClass().getResource("/com/vetsystem/view/Dashboard.fxml"));
                Scene scene = new Scene(root, 700, 500);
                Stage stage = (Stage) txtUsuario.getScene().getWindow();
                stage.setTitle("VetSystem - Dashboard");
                stage.setScene(scene);
                stage.show();
            } else {
                lblMensaje.setText("Usuario o contraseña incorrectos.");
            }
        } catch (Exception e) {
            lblMensaje.setText("Error de conexión.");
            System.out.println(e.getMessage());
        }
    }
}
