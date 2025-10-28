package org.example.entidades;

public class Disciplina {
    private int id;

    private Integer ch;
    private String ementa;
    private String nome;

    public Disciplina() {
    }

    public Disciplina(int id, Integer ch, String ementa, String nome) {
        this.id = id;
        this.ch = ch;
        this.ementa = ementa;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCh() {
        return ch;
    }

    public void setCh(Integer ch) {
        this.ch = ch;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
