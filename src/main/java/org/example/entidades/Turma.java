package org.example.entidades;

public class Turma {
private int id;
private String turno;
private String nome;

    public Turma() {
    }

    public Turma(int id, String turno, String nome) {
        this.id = id;
        this.turno = turno;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
