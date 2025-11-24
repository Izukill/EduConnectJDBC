package repository;

import entidades.Disciplina;
import entidades.Turma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDisciplinaRepository {

    private Connection connectionBd;

    public TurmaDisciplinaRepository(Connection connectionBd) {
        this.connectionBd = connectionBd;
    }


    public void salvar(int idTurma, int idDisciplina){

        String sql = "INSERT into turma_disciplina (id_turma, id_disciplina) VALUES (?,?)";


        try(PreparedStatement stmt = connectionBd.prepareStatement(sql)){

            stmt.setInt(1, idTurma);
            stmt.setInt(2, idDisciplina);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void deletar(int idTurma, int idDisciplina){

        String sql= "DELETE from turma_disciplina WHERE id_turma = ? AND id_disciplina = ?";

        try(PreparedStatement stmt = connectionBd.prepareStatement(sql)){

            stmt.setInt(1, idTurma);
            stmt.setInt(2, idDisciplina);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public List<Disciplina> listarDisciplinasDaTurma(int idTurma) {

        String sql = "SELECT d.* FROM disciplinas d " +
                "JOIN turma_disciplina td ON d.id = td.id_disciplina " +
                "WHERE td.id_turma = ?";

        List<Disciplina> disciplinas = new ArrayList<>();

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {

            stmt.setInt(1, idTurma);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Disciplina d = new Disciplina(
                            rs.getInt("id"),
                            rs.getInt("ch"),
                            rs.getString("ementa"),
                            rs.getString("nome"),
                            rs.getInt("id_professor")
                    );
                    disciplinas.add(d);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return disciplinas;
    }


    public List<Turma> listarTurmasDaDisciplina(int idDisciplina) {

        String sql = "SELECT t.* FROM turmas t " +
                "JOIN turma_disciplina td ON t.id = td.id_turma " +
                "WHERE td.id_disciplina = ?";

        List<Turma> turmas = new ArrayList<>();

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {

            stmt.setInt(1, idDisciplina);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    Turma t = new Turma(
                            rs.getInt("id"),
                            rs.getString("turno"),
                            rs.getString("nome")
                    );

                    turmas.add(t);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return turmas;
    }






}
