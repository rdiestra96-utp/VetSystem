
package com.vetsystem.model;

import java.util.Date;

public class Consulta {
    private int id;
    private Date fecha;
    private String motivo;
    private String diagnostico;
    private String tratamiento;
    private int idPaciente;
    private int idVeterinario;

    public Consulta() {}

    public Consulta(int id, Date fecha, String motivo, String diagnostico, 
                    String tratamiento, int idPaciente, int idVeterinario) {
        this.id = id;
        this.fecha = fecha;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.idPaciente = idPaciente;
        this.idVeterinario = idVeterinario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public int getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(int idVeterinario) { this.idVeterinario = idVeterinario; }

    @Override
    public String toString() {
        return "Consulta: " + motivo + " | Diagnóstico: " + diagnostico;
    }
}
