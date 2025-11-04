package org.example.entidades;

public class Turma {
private int id;
private int id_disciplina;
private String turno;
private String nome;


    public Turma() {
    }

    public Turma(int id, String turno, String nome, int id_disciplina) {
        this.id = id;
        this.turno = turno;
        this.nome = nome;
        this.id_disciplina = id_disciplina;
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

    public int getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }
}
