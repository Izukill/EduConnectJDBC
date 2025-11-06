import org.example.entidades.*;
import repository.*;

import java.sql.Date;
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









        //CRIAR E SALVAR TURMA

        Turma turma= new Turma();
        TurmaRepository turmaRepository= new TurmaRepository(conectionBD.Conectar());
        turma.setNome("Turma 1-A");
        turma.setTurno("manhã");
        turmaRepository.salvar(turma);

        // === CRIAR E SALVAR ALUNO ===
        Aluno aluno = new Aluno();
        AlunoRepository alunoRepository=new AlunoRepository(conectionBD.Conectar());
        aluno.setNome("luan loreto");
        aluno.setEmail("ronaldo@gmail.com");
        aluno.setCpf("11111111110");
        aluno.setTelefone("83 9123-1456");
        aluno.setSenha_hash("axcdSGER134fgd");
        aluno.setMatricula(aluno.gerarMatricula());
        aluno.setIdTurma(turma.getId());

        alunoRepository.salvar(aluno);



        // CRIAR E SALVAR PROFESSOR
        Professor professor= new Professor();
        ProfessorRepository professorRepository= new ProfessorRepository(conectionBD.Conectar());
        professor.setNome("Adalberto");
        professor.setCpf("1230910239");
        professor.setEmail("adalberto@gmail.com.br");
        professor.setSenha_hash("sdf!#$%df");
        professor.setTelefone("839912345456");
        professor.setSalario(1.500f);




        // CRIAR E SALVAR AULA

        Aula aula = new Aula();
        aula.setConteudo("MongoDb muito foda");
        aula.setData(new Date(System.currentTimeMillis()));
        aula.setObservacoes("Aula muito pica");














        try{




        } catch (Exception e) {
            throw new RuntimeException(e);
        }





    }
}
