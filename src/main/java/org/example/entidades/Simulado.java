package org.example.entidades;

public class Simulado {

    private int idSimulado;
    private String descricao;
    private int qtdQuestoes;
    private int idTurma; // FK

    public Simulado() {}

    public Simulado(int idSimulado, String descricao, int qtdQuestoes, int idTurma) {
        this.idSimulado = idSimulado;
        this.descricao = descricao;
        this.qtdQuestoes = qtdQuestoes;
        this.idTurma = idTurma;
    }

    public int getIdSimulado() {
        return idSimulado;
    }

    public void setIdSimulado(int idSimulado) {
        this.idSimulado = idSimulado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdQuestoes() {
        return qtdQuestoes;
    }

    public void setQtdQuestoes(int qtdQuestoes) {
        this.qtdQuestoes = qtdQuestoes;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }
}
