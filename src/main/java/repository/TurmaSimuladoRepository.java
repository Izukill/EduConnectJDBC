package repository;

import entidades.Simulado;
import entidades.Turma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaSimuladoRepository {

    private Connection connectionBd;

    public TurmaSimuladoRepository(Connection connectionBd) {
        this.connectionBd = connectionBd;
    }


    public void salvar(int idTurma, int idSimulado){

        String sql= "INSERT into turma_simulado (id_turma, id_simulado) VALUES (?, ?)";


        try(PreparedStatement stmt = connectionBd.prepareStatement(sql)){

            stmt.setInt(1, idTurma);
            stmt.setInt(2, idSimulado);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



    public void deletar(int idTurma, int idSimulado){

        String sql= "DELETE from turma_simulado WHERE id_turma = ? AND id_simulado = ? ";


        try(PreparedStatement stmt = connectionBd.prepareStatement(sql)){

            stmt.setInt(1, idTurma);
            stmt.setInt(2, idSimulado);

            stmt.executeQuery();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




    public List<Simulado> listarSimuladosDaTurma(int idTurma) {

        String sql = """
            SELECT s.id, s.descricao, s.qtd_questoes
            FROM turma_simulado ts
            JOIN simulados s ON ts.id_simulado = s.id
            WHERE ts.id_turma = ?
        """;

        List<Simulado> simulados = new ArrayList<>();

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {

            stmt.setInt(1, idTurma);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Simulado s = new Simulado(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getInt("qtd_questoes")
                );

                simulados.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return simulados;
    }


    // ----------------------------------------------------------------------
    //  LISTAR TURMAS DE UM SIMULADO
    // ----------------------------------------------------------------------
    public List<Turma> listarTurmasDoSimulado(int idSimulado) {

        String sql = """
            SELECT t.id, t.turno, t.nome
            FROM turma_simulado ts
            JOIN turmas t ON ts.id_turma = t.id
            WHERE ts.id_simulado = ?
        """;

        List<Turma> turmas = new ArrayList<>();

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {

            stmt.setInt(1, idSimulado);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Turma t = new Turma(
                        rs.getInt("id"),
                        rs.getString("turno"),
                        rs.getString("nome")
                );

                turmas.add(t);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return turmas;
    }





}
