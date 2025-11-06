package repository;

import org.example.entidades.Disciplina;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaRepository implements EntityBd<Disciplina> {
    private ConectionBD conectionBd;

    public DisciplinaRepository(ConectionBD conectionBD) {
        this.conectionBd = conectionBD;
    }


    @Override
    public void salvar(Disciplina entidade) {
        String sql = "INSERT INTO disciplinas (ch, ementa, nome, id_professor) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setInt(1, entidade.getCh());
            stmt.setString(2, entidade.getEmenta());
            stmt.setString(3, entidade.getNome());
            stmt.setInt(4, entidade.getId_professor());

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
        String sql = "DELETE FROM disciplinas WHERE id = ?";

        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Disciplina entidade) {
        String sql = "UPDATE disciplinas SET ch = ?, ementa = ?, nome = ?, id_professor = ? WHERE id = ?";
        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql)) {

            stmt.setInt(1, entidade.getCh());
            stmt.setString(2, entidade.getEmenta());
            stmt.setString(3, entidade.getNome());
            stmt.setInt(4, entidade.getId_professor());
            stmt.setInt(5, entidade.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Disciplina> listar() {
        List<Disciplina> disciplinasList = new ArrayList<>();
        String sql = "SELECT id, ch, ementa, nome, id_professor FROM disciplinas";

        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                disciplinasList.add(new Disciplina(
                        rs.getInt("id"),
                        rs.getInt("ch"),
                        rs.getString("ementa"),
                        rs.getString("nome"),
                        rs.getInt("id_professor")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return disciplinasList;
    }
}
