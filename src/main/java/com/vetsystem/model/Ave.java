
package com.vetsystem.model;

public class Ave extends Paciente {
    private double envergadura;

    public Ave() {}

    public Ave(int id, String nombre, String raza, int edad, double peso, int idDueno, double envergadura) {
        super(id, nombre, "Ave", raza, edad, peso, idDueno);
        this.envergadura = envergadura;
    }

    public double getEnvergadura() { return envergadura; }
    public void setEnvergadura(double envergadura) { this.envergadura = envergadura; }

    public void volar() {
        System.out.println(getNombre() + " está volando!");
    }

    @Override
    public String toString() {
        return "Ave: " + getNombre() + " | Envergadura: " + envergadura + "cm";
    }
}
