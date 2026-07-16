
package com.vetsystem.model;

public class Gato extends Paciente {
    private boolean esIndoor;

    public Gato() {}

    public Gato(int id, String nombre, String raza, int edad, double peso, int idDueno, boolean esIndoor) {
        super(id, nombre, "Gato", raza, edad, peso, idDueno);
        this.esIndoor = esIndoor;
    }

    public boolean isEsIndoor() { return esIndoor; }
    public void setEsIndoor(boolean esIndoor) { this.esIndoor = esIndoor; }

    public void ronronear() {
        System.out.println(getNombre() + " dice: Purr...");
    }

    @Override
    public String toString() {
        return "Gato: " + getNombre() + " | Indoor: " + (esIndoor ? "Sí" : "No");
    }
}
