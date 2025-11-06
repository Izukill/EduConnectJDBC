package repository;

import org.example.entidades.Turma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaRepository implements EntityBd<Turma> {
    private Connection conectionBd;

    public TurmaRepository(Connection conectionBD) {
        this.conectionBd = conectionBD;
    }


    @Override
    public void salvar(Turma entidade) {
        String sql = "INSERT INTO turmas (turno, nome) VALUES (?, ?)";

        try (PreparedStatement stmt = conectionBd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setString(1, entidade.getTurno());
            stmt.setString(2, entidade.getNome());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entidade.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM turmas WHERE id = ?";

        try (PreparedStatement stmt = conectionBd.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Turma entidade) {
        String sql = "UPDATE turmas SET turno = ?, nome = ? WHERE id = ?";
        try (PreparedStatement stmt = conectionBd.prepareStatement(sql)) {

            stmt.setString(1, entidade.getTurno());
            stmt.setString(2, entidade.getNome());
            stmt.setInt(3, entidade.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Turma> listar() {
        List<Turma> turmasList = new ArrayList<>();
        String sql = "SELECT id, turno, nome FROM turmas";

        try (PreparedStatement stmt = conectionBd.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                turmasList.add(new Turma(
                        rs.getInt("id"),
                        rs.getString("Turno"),
                        rs.getString("nome")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return turmasList;
    }
}
