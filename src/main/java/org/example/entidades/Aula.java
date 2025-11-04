package org.example.entidades;

import java.sql.Date;

public class Aula {
    private int id;


    private String conteudo;
    private Date data;
    private String observacoes;

    public Aula() {
    }

    public Aula(int id, String conteudo, Date data, String observacoes) {
        this.id = id;
        this.conteudo = conteudo;
        this.data = data;
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
