package entidades;

public class Professor extends Pessoa {

    private float salario;

    public Professor() {

    }

    public Professor(int id, String nome, String email, String cpf, String telefone, String senha_hash, float salario) {
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
