package entidades;

public class Simulado {

    private int id;
    private String descricao;
    private int qtdQuestoes;

    public Simulado() {}

    public Simulado(int idSimulado, String descricao, int qtdQuestoes) {
        this.id = idSimulado;
        this.descricao = descricao;
        this.qtdQuestoes = qtdQuestoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    @Override
    public String toString() {
        return "Simulado{" +
                "descricao='" + descricao + '\'' +
                ", idSimulado=" + id +
                ", qtdQuestoes=" + qtdQuestoes +
                '}';
    }
}
