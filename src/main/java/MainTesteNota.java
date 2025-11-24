
import RedisService.NotaRedis;
import config.ConectionBD;
import entidades.*;
import repository.*;


public class MainTesteNota {

    public static void main(String[] args) {


        ConectionBD conectionBD = new ConectionBD();



        // --- REPOSITORIES JDBC ---
        AlunoRepository alunoRepo = new AlunoRepository(conectionBD.Conectar());
        SimuladoRepository simuladoRepo = new SimuladoRepository(conectionBD.Conectar());
        NotaRepository notaRepo = new NotaRepository(conectionBD.Conectar());
        TurmaRepository turmaRepository = new TurmaRepository(conectionBD.Conectar());
        TurmaSimuladoRepository turmaSimuladoRepository = new TurmaSimuladoRepository(conectionBD.Conectar());

        // --- REPOSITORY REDIS ---
        NotaRedis notaRedisRepo = new NotaRedis();

        try {

            Turma turma = new Turma();
            turma.setNome("Turma 2-A");
            turma.setTurno("Manhã");

            turmaRepository.salvar(turma);

            // 1) Criar aluno (herda Pessoa)
            Aluno aluno = new Aluno();
            aluno.setMatricula(System.nanoTime());
            aluno.setCpf("12345890103");
            aluno.setNome("Ronaldo da silva filho");
            aluno.setEmail("ronaldo666@gmail.com");
            aluno.setSenha_hash("ASDDg3@$df");
            aluno.setTelefone("8390912343");
            aluno.setIdTurma(turma.getId());

            alunoRepo.salvar(aluno); // JDBC


            // 2) Criar simulado
            Simulado simulado = new Simulado();
            simulado.setDescricao("Simulado enem 2025 -1");
            simulado.setQtdQuestoes(90);

            simuladoRepo.salvar(simulado); // JDBC

            turmaSimuladoRepository.salvar(turma.getId(), simulado.getId());


            // 3) Criar nota e relacionar os 3
            Nota nota = new Nota();
            nota.setIdAluno(aluno.getId());
            nota.setIdSimulado(simulado.getId());
            nota.setNotaRedacao(750.0);
            nota.setNotaLinguagens(800);
            nota.setNotaCienciasHumanas(567);
            nota.setNotaCienciasNatureza(350.0);
            nota.setNotaMatematica(800.0);

            notaRepo.salvar(nota); // JDBC


            // 4) SALVAR NO REDIS
            notaRedisRepo.salvarNota(nota);
            notaRedisRepo.adicionarHistorico(nota);

            System.out.println("Tudo salvo: aluno + simulado + nota + redis!");

            // 5) TESTANDO BUSCA NO REDIS
            Nota recuperada = notaRedisRepo.buscarNota(nota.getIdNota());
            System.out.println("Nota recuperada do Redis: " + recuperada);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
