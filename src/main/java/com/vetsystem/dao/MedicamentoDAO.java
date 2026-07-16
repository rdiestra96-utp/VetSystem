
package com.vetsystem.dao;

import com.vetsystem.model.Medicamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MedicamentoDAO {
    public void insertar(Medicamento m) {
        String sql = "INSERT INTO medicamento (nombre, dosis, stock) VALUES (?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getDosis());
            ps.setInt(3, m.getStock());
            ps.executeUpdate();
            System.out.println("Medicamento insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public List<Medicamento> listarTodos() {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicamento";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Medicamento(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("dosis"),
                    rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public void actualizar(Medicamento m) {
        String sql = "UPDATE medicamento SET nombre=?, dosis=?, stock=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getDosis());
            ps.setInt(3, m.getStock());
            ps.setInt(4, m.getId());
            ps.executeUpdate();
            System.out.println("Medicamento actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM medicamento WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Medicamento eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    // Iterator — recorrer medicamentos
    public void recorrerConIterator() {
        List<Medicamento> lista = listarTodos();
        System.out.println("\n=== LISTA DE MEDICAMENTOS (Iterator) ===");
        Iterator<Medicamento> iterator = lista.iterator();
        while (iterator.hasNext()) {
            Medicamento m = iterator.next();
            System.out.println(m.getNombre() + " | Stock: " + m.getStock());
        }
    }

    // Lambda — buscar por nombre
    public List<Medicamento> buscarPorNombre(String nombre) {
        return listarTodos().stream()
            .filter(m -> m.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .collect(java.util.stream.Collectors.toList());
    }
}
