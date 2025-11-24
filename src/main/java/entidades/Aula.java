package entidades;

import java.sql.Date;

public class Aula {
    private int id;

    private int id_turma;
    private int id_professor; //FK
    private String conteudo;
    private Date data;
    private String observacoes;

    public Aula() {
    }

    public Aula(int id, String conteudo, Date data, String observacoes,int id_turma, int id_professor) {
        this.id = id;
        this.conteudo = conteudo;
        this.data = data;
        this.observacoes = observacoes;
        this.id_turma= id_turma;
        this.id_professor = id_professor;
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

    public int getId_turma() {
        return id_turma;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }

    public int getId_professor() {
        return id_professor;
    }

    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }
}
