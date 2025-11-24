package entidades;

public class Nota {

    private int idNota;
    private double notaLinguagens;
    private double notaCienciasHumanas;
    private double notaMatematica;
    private double notaCienciasNatureza;
    private double notaRedacao;
    private int idAluno; // FK
    private int idSimulado; // FK

    public Nota() {}

    public Nota(int idNota, double notaLinguagens, double notaCienciasHumanas, double notaCienciasNatureza, double notaRedacao, int idAluno, int idSimulado) {
        this.idNota = idNota;
        this.notaLinguagens = notaLinguagens;
        this.notaCienciasHumanas = notaCienciasHumanas;
        this.notaCienciasNatureza = notaCienciasNatureza;
        this.notaRedacao = notaRedacao;
        this.idAluno = idAluno;
        this.idSimulado = idSimulado;
    }

    public Nota(int id, double notalinguagens, double notacienciashumanas, double notacienciasnatureza, double notamatematica, double notaredacao, int idAluno, int idSimulado) {
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public double getNotaLinguagens() {
        return notaLinguagens;
    }

    public void setNotaLinguagens(double notaLinguagens) {
        this.notaLinguagens = notaLinguagens;
    }

    public double getNotaCienciasHumanas() {
        return notaCienciasHumanas;
    }

    public void setNotaCienciasHumanas(double notaCienciasHumanas) {
        this.notaCienciasHumanas = notaCienciasHumanas;
    }

    public double getNotaCienciasNatureza() {
        return notaCienciasNatureza;
    }

    public void setNotaCienciasNatureza(double notaCienciasNatureza) {
        this.notaCienciasNatureza = notaCienciasNatureza;
    }

    public double getNotaMatematica() {
        return notaMatematica;
    }

    public void setNotaMatematica(double notaMatematica) {
        this.notaMatematica = notaMatematica;
    }

    public double getNotaRedacao() {
        return notaRedacao;
    }

    public void setNotaRedacao(double notaRedacao) {
        this.notaRedacao = notaRedacao;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdSimulado() {
        return idSimulado;
    }

    public void setIdSimulado(int idSimulado) {
        this.idSimulado = idSimulado;
    }
}
