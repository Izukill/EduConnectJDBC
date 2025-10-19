package org.example.entidades;

public class Coordenador extends Pessoa{

    private float salario;

    public Coordenador() {

    }


    public Coordenador(int id, String nome, String email, String cpf, String telefone, String senha_hash, float salario) {
        super(id, nome, email, cpf, telefone, senha_hash);
        this.salario = salario;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
}

