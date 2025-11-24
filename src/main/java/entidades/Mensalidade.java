package entidades;

import java.time.LocalDate;

public class Mensalidade {

    private int idMensalidade;

    private LocalDate data;

    private double valor;

    private LocalDate vencimento;

    private String status;

    private int idAluno;

    public Mensalidade() {}

    public Mensalidade(int idMensalidade, LocalDate data, double valor, LocalDate vencimento, String status, int idAluno) {
        this.idMensalidade = idMensalidade;

        this.data = data;

        this.valor = valor;

        this.vencimento = vencimento;

        this.status = status;

        this.idAluno = idAluno;

    }

    public int getIdMensalidade() {
        return idMensalidade;
    }

    public void setIdMensalidade(int idMensalidade) {
        this.idMensalidade = idMensalidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }
}
