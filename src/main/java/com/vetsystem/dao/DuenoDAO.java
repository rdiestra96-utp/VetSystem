
package com.vetsystem.dao;

import com.vetsystem.dao.Conexion;
import com.vetsystem.model.Dueno;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DuenoDAO {
    public void insertar(Dueno d) {
        String sql = "INSERT INTO dueno (nombre, apellido, dni, telefono, direccion) VALUES (?,?,?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getApellido());
            ps.setString(3, d.getDni());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getDireccion());
            ps.executeUpdate();
            System.out.println("Dueño insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public List<Dueno> listarTodos() {
        List<Dueno> lista = new ArrayList<>();
        String sql = "SELECT * FROM dueno";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Dueno(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("dni"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // Iterator — recorrer dueños
    public void recorrerConIterator() {
        List<Dueno> lista = listarTodos();
        System.out.println("\n=== LISTA DE DUEÑOS (Iterator) ===");
        Iterator<Dueno> iterator = lista.iterator();
        while (iterator.hasNext()) {
            Dueno d = iterator.next();
            System.out.println(d.getNombre() + " " + d.getApellido() + " | DNI: " + d.getDni());
        }
    }

    // Lambda — buscar por nombre
    public void buscarPorNombre(String nombre) {
        List<Dueno> lista = listarTodos();
        System.out.println("\n=== BÚSQUEDA DE DUEÑO: " + nombre + " (Lambda) ===");
        lista.stream()
             .filter(d -> d.getNombre().equalsIgnoreCase(nombre))
             .forEach(d -> System.out.println(d.getNombre() + " " + d.getApellido() + " | Tel: " + d.getTelefono()));
    }
    
    public void eliminar(int id) {
        String sql = "DELETE FROM dueno WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Dueño eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }    
}
