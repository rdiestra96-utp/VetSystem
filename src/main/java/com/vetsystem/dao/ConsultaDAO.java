
package com.vetsystem.dao;

import com.vetsystem.dao.Conexion;
import com.vetsystem.model.Consulta;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConsultaDAO {
    public void insertar(Consulta c) {
        String sql = "INSERT INTO consulta (fecha, motivo, diagnostico, tratamiento, id_paciente, id_veterinario) VALUES (?,?,?,?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(c.getFecha().getTime()));
            ps.setString(2, c.getMotivo());
            ps.setString(3, c.getDiagnostico());
            ps.setString(4, c.getTratamiento());
            ps.setInt(5, c.getIdPaciente());
            ps.setInt(6, c.getIdVeterinario());
            ps.executeUpdate();
            System.out.println("Consulta registrada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public List<Consulta> listarTodos() {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Consulta(
                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getString("motivo"),
                    rs.getString("diagnostico"),
                    rs.getString("tratamiento"),
                    rs.getInt("id_paciente"),
                    rs.getInt("id_veterinario")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // Iterator — recorrer consultas
    public void recorrerConIterator() {
        List<Consulta> lista = listarTodos();
        System.out.println("\n=== LISTA DE CONSULTAS (Iterator) ===");
        Iterator<Consulta> iterator = lista.iterator();
        while (iterator.hasNext()) {
            Consulta c = iterator.next();
            System.out.println("Motivo: " + c.getMotivo() + " | Diagnóstico: " + c.getDiagnostico());
        }
    }

    // Lambda — filtrar por paciente
    public void filtrarPorPaciente(int idPaciente) {
        List<Consulta> lista = listarTodos();
        System.out.println("\n=== CONSULTAS DEL PACIENTE ID: " + idPaciente + " (Lambda) ===");
        lista.stream()
             .filter(c -> c.getIdPaciente() == idPaciente)
             .forEach(c -> System.out.println(c.getMotivo() + " | " + c.getDiagnostico()));
    }
}
