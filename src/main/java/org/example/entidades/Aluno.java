package org.example.entidades;

public class Aluno extends Pessoa {

    private long matricula;

    public Aluno() {
    }

    public Aluno(int id, String nome, String email, String cpf, String telefone, String senha_hash) {
        super(id, nome, email, cpf, telefone, senha_hash);
        this.matricula = gerarMatricula();
    }


    public Aluno(int id, String nome, String email, String cpf, String telefone, String senha_hash, long matricula) {
        super(id, nome, email, cpf, telefone, senha_hash);
        this.matricula = matricula;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }


    public long gerarMatricula(){
        long timeStamp= System.currentTimeMillis() % 100000;
        return timeStamp;
    }
}
