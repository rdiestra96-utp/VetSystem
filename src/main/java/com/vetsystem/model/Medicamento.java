
package com.vetsystem.model;

public class Medicamento {
    private int id;
    private String nombre;
    private String dosis;
    private int stock;

    public Medicamento() {}

    public Medicamento(int id, String nombre, String dosis, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.dosis = dosis;
        this.stock = stock;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return nombre + " | Dosis: " + dosis + " | Stock: " + stock;
    }
}
