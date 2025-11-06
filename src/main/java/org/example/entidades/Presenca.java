package org.example.entidades;

import jdk.jshell.Snippet;

public class Presenca {
    private int id;
    private int id_turma; //FK
    private int id_aluno; //FK

    private String status;

    public Presenca() {
    }

    public Presenca(int id, String status, int id_turma, int id_aluno) {
        this.id = id;
        this.status = status;
        this.id_turma = id_turma;
        this.id_aluno = id_aluno;
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

    public int getId_turma() {
        return id_turma;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }
}

