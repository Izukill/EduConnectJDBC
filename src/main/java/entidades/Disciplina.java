package entidades;

import java.util.List;

public class Disciplina {


    private int id;
    private int id_professor; //fk


    private Integer ch;
    private String ementa;
    private String nome;

    public Disciplina() {
    }

    public Disciplina(int id, Integer ch, String ementa, String nome, int id_professor) {
        this.id = id;
        this.ch = ch;
        this.ementa = ementa;
        this.nome = nome;
        this.id_professor = id_professor;

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

    public int getId_professor() {
        return id_professor;
    }

    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }


    @Override
    public String toString() {
        return "Disciplina{" +
                "ch=" + ch +
                ", id=" + id +
                ", id_professor=" + id_professor +
                ", ementa='" + ementa + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
