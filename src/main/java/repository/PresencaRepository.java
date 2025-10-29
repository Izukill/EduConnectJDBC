package repository;

import org.example.entidades.Presenca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PresencaRepository implements EntityBd<Presenca> {
    private ConectionBD conectionBd;

    public PresencaRepository(ConectionBD conectionBD) {
        this.conectionBd = conectionBD;
    }


    @Override
    public void salvar(Presenca entidade) {
        String sql = "INSERT INTO presenças (status) VALUES (?)";

        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setString(1,entidade.getStatus());


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
        String sql = "DELETE FROM presenças WHERE id = ?";

        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Presenca entidade) {
        String sql = "UPDATE presenças SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql)) {

            stmt.setString(1, entidade.getStatus());
            stmt.setInt(2, entidade.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Presenca> listar() {
        List<Presenca> presencasList = new ArrayList<>();
        String sql = "SELECT id, status FROM presenças";

        try (PreparedStatement stmt = conectionBd.Conectar().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                presencasList.add(new Presenca(
                        rs.getInt("id"),
                        rs.getString("status")


                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return presencasList;
    }
}
