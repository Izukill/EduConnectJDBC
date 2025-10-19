import org.example.entidades.Aluno;
import org.example.entidades.Coordenador;
import org.example.entidades.Pessoa;
import org.example.entidades.Professor;
import repository.*;

import java.util.List;

public class Main {


    public static void main(String[] args) {

        ConectionBD conectionBD=new ConectionBD();


        Pessoa pessoa=new Pessoa();
        PessoaRepository pessoaRepository=new PessoaRepository(conectionBD.Conectar());

        pessoa.setCpf("12345678911");
        pessoa.setEmail("email@gmail.com");
        pessoa.setNome("ronaldo da silva");
        pessoa.setTelefone("83 9123-1456");
        pessoa.setSenha_hash("axcdSGER134fgd");







        // === CRIAR E SALVAR ALUNO ===
        Aluno aluno = new Aluno();
        AlunoRepository alunoRepository=new AlunoRepository(conectionBD.Conectar());
        aluno.setNome("luan loreto");
        aluno.setEmail("ronaldo@gmail.com");
        aluno.setCpf("12943614483");
        aluno.setTelefone("83 9123-1456");
        aluno.setSenha_hash("axcdSGER134fgd");
        aluno.setMatricula(aluno.gerarMatricula());

        //alunoRepository.salvar(aluno);


        // === CRIAR E SALVAR PROFESSOR ===
        Professor professor = new Professor();
        ProfessorRepository professorRepository= new ProfessorRepository(conectionBD.Conectar());
        professor.setNome("Ana Beatriz");
        professor.setEmail("ana@gmail.com");
        professor.setCpf("98765432100");
        professor.setTelefone("83 99876-5432");
        professor.setSenha_hash("senhaPro123");
        professor.setSalario(1500.00f);

        //professorRepository.salvar(professor);


        // === CRIAR E SALVAR COORDENADOR ===
        Coordenador coordenador = new Coordenador();
        CoordenadorRepository coordenadorRepository= new CoordenadorRepository(conectionBD.Conectar());
        coordenador.setNome("Carlos Souza");
        coordenador.setEmail("carlos@gmail.com");
        coordenador.setCpf("45678912300");
        coordenador.setTelefone("83 99456-7890");
        coordenador.setSenha_hash("coord2024");
        coordenador.setSalario(5830.00f);

        //coordenadorRepository.salvar(coordenador);





        try{

            professorRepository.salvar(professor);
            coordenadorRepository.salvar(coordenador);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }





    }
}
