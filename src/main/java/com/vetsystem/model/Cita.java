
package com.vetsystem.model;

import java.util.Date;

public class Cita {
    private int id;
    private Date fecha;
    private String hora;
    private String estado;
    private int idPaciente;
    private int idVeterinario;

    public Cita() {}

    public Cita(int id, Date fecha, String hora, String estado, 
                int idPaciente, int idVeterinario) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.idPaciente = idPaciente;
        this.idVeterinario = idVeterinario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public int getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(int idVeterinario) { this.idVeterinario = idVeterinario; }

    @Override
    public String toString() {
        return "Cita: " + fecha + " " + hora + " | Estado: " + estado;
    }
}
