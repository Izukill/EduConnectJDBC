package repository;

import org.example.entidades.Aula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AulaRepository implements EntityBd<Aula> {
private ConectionBD conectionBd;

    public AulaRepository(ConectionBD conectionBD) {
        this.conectionBd = conectionBD;
    }


    @Override
    public void salvar(Aula entidade) {
        String sql = "INSERT INTO aulas (conteudo, data, observacoes) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setString(1, entidade.getConteudo());
            stmt.setDate(2, entidade.getData());
            stmt.setString(3, entidade.getObservacoes());

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
        String sql = "DELETE FROM aulas WHERE id = ?";

        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Aula entidade) {
        String sql = "UPDATE aulas SET conteudo = ?, data = ?, obsevacoes = ? WHERE id_mensalidade = ?";
        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql)) {

            stmt.setString(1, entidade.getConteudo());
            stmt.setDate(2, entidade.getData());
            stmt.setString(3, entidade.getObservacoes());
            stmt.setInt(4, entidade.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Aula> listar() {
        List<Aula> aulasList = new ArrayList<>();
        String sql = "SELECT id, conteudo, data, observacoes FROM aulas";

        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                aulasList.add(new Aula(
                        rs.getInt("id"),
                        rs.getString("conteudo"), // Converte java.sql.Date para LocalDate
                        rs.getDate("data"),
                        rs.getString("obsevacoes")


                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return aulasList;
    }
}
