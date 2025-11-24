package repository;

import entidades.Simulado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimuladoRepository implements EntityBd<Simulado> {

    private Connection connectionBd;

    public SimuladoRepository(Connection connectionBd) {
        this.connectionBd = connectionBd;
    }

    @Override
    public void salvar(Simulado entidade) {
        String sql = "INSERT INTO simulados (descricao, qtd_questoes) VALUES (?, ?)";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entidade.getDescricao());
            stmt.setInt(2, entidade.getQtdQuestoes());

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
        String sql = "DELETE FROM simulados WHERE id_simulado = ?";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Simulado entidade) {
        String sql = "UPDATE simulados SET descricao = ?, qtd_questoes = ? WHERE id_simulado = ?";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {

            stmt.setString(1, entidade.getDescricao());
            stmt.setInt(2, entidade.getQtdQuestoes());
            stmt.setInt(4, entidade.getId()); // ID para o WHERE

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Simulado> listar() {
        List<Simulado> simuladosList = new ArrayList<>();
        String sql = "SELECT id_simulado, descricao, qtd_questoes FROM simulados";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                simuladosList.add(new Simulado(
                        rs.getInt("id_simulado"),
                        rs.getString("descricao"),
                        rs.getInt("qtd_questoes")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return simuladosList;
    }
}