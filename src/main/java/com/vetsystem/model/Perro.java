
package com.vetsystem.model;

public class Perro extends Paciente {
    private String raza;

    public Perro() {}

    public Perro(int id, String nombre, String raza, int edad, double peso, int idDueno) {
        super(id, nombre, "Perro", raza, edad, peso, idDueno);
        this.raza = raza;
    }

    public void ladrar() {
        System.out.println(getNombre() + " dice: Guau!");
    }

    @Override
    public String toString() {
        return "Perro: " + getNombre() + " | Raza: " + raza;
    }
}
