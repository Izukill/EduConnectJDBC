package org.example.entidades;

import jdk.jshell.Snippet;

public class Presenca {
    private int id;

    private String status;

    public Presenca() {
    }

    public Presenca(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
