
package com.vetsystem.dao;

import com.vetsystem.dao.Conexion;
import com.vetsystem.model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PacienteDAO {
    public void insertar(Paciente p) {
        String sql = "INSERT INTO paciente (nombre, especie, raza, edad, peso, id_dueno) VALUES (?,?,?,?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getEspecie());
            ps.setString(3, p.getRaza());
            ps.setInt(4, p.getEdad());
            ps.setDouble(5, p.getPeso());
            ps.setInt(6, p.getIdDueno());
            ps.executeUpdate();
            System.out.println("Paciente insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    // Listar todos
    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Paciente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especie"),
                    rs.getString("raza"),
                    rs.getInt("edad"),
                    rs.getDouble("peso"),
                    rs.getInt("id_dueno")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // Actualizar
    public void actualizar(Paciente p) {
        String sql = "UPDATE paciente SET nombre=?, especie=?, raza=?, edad=?, peso=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getEspecie());
            ps.setString(3, p.getRaza());
            ps.setInt(4, p.getEdad());
            ps.setDouble(5, p.getPeso());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
            System.out.println("Paciente actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    // Eliminar
    public void eliminar(int id) {
        String sql = "DELETE FROM paciente WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Paciente eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    // Iterator — recorrer lista de pacientes
    public void recorrerConIterator() {
        List<Paciente> lista = listarTodos();
        System.out.println("\n=== LISTA DE PACIENTES (Iterator) ===");
        Iterator<Paciente> iterator = lista.iterator();
        while (iterator.hasNext()) {
            Paciente p = iterator.next();
            System.out.println(p.getNombre() + " | " + p.getEspecie() + " | " + p.getRaza());
        }
    }

    // ListIterator — recorrer en ambas direcciones
    public void recorrerConListIterator() {
        List<Paciente> lista = listarTodos();
        ListIterator<Paciente> listIterator = lista.listIterator();
        System.out.println("\n=== HACIA ADELANTE (ListIterator) ===");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next().getNombre());
        }
        System.out.println("\n=== HACIA ATRÁS (ListIterator) ===");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous().getNombre());
        }
    }

    // Lambda — filtrar por especie
    public void filtrarPorEspecie(String especie) {
        List<Paciente> lista = listarTodos();
        System.out.println("\n=== PACIENTES FILTRADOS POR: " + especie + " (Lambda) ===");
        lista.stream()
             .filter(p -> p.getEspecie().equalsIgnoreCase(especie))
             .forEach(p -> System.out.println(p.getNombre() + " | " + p.getRaza()));
    }

    // Lambda — ordenar por nombre
    public void ordenarPorNombre() {
        List<Paciente> lista = listarTodos();
        System.out.println("\n=== PACIENTES ORDENADOS POR NOMBRE (Lambda) ===");
        lista.stream()
             .sorted((p1, p2) -> p1.getNombre().compareTo(p2.getNombre()))
             .forEach(p -> System.out.println(p.getNombre() + " | " + p.getEspecie()));
    }
}
