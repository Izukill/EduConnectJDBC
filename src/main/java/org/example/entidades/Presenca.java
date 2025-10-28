package org.example.entidades;

public class Presenca {
    private int id;

    public enum tipoStatus{Presente,Faltou,Justificado}

    private tipoStatus Status;

    public Presenca() {
    }

    public Presenca(int id, tipoStatus status) {
        this.id = id;
        Status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public tipoStatus getStatus() {
        return Status;
    }

    public void setStatus(tipoStatus status) {
        Status = status;
    }
}
