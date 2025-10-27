package repository;

import org.example.entidades.Mensalidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensalidadeRepository implements EntityBd<Mensalidade> {

    private Connection connectionBd;

    public MensalidadeRepository(Connection connectionBd) {
        this.connectionBd = connectionBd;
    }

    @Override
    public void salvar(Mensalidade entidade) {

        String sql = "INSERT INTO mensalidades (data, valor, vencimento, status, aluno_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setDate(1, java.sql.Date.valueOf(entidade.getData()));
            stmt.setDouble(2, entidade.getValor());
            stmt.setDate(3, java.sql.Date.valueOf(entidade.getVencimento()));
            stmt.setString(4, entidade.getStatus());
            stmt.setInt(5, entidade.getIdAluno());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entidade.setIdMensalidade(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM mensalidades WHERE id_mensalidade = ?";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Mensalidade entidade) {
        String sql = "UPDATE mensalidades SET data = ?, valor = ?, vencimento = ?, status = ?, aluno_id = ? WHERE id_mensalidade = ?";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(entidade.getData()));
            stmt.setDouble(2, entidade.getValor());
            stmt.setDate(3, java.sql.Date.valueOf(entidade.getVencimento()));
            stmt.setString(4, entidade.getStatus());
            stmt.setInt(5, entidade.getIdAluno());
            stmt.setInt(6, entidade.getIdMensalidade()); // ID para o WHERE

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Mensalidade> listar() {
        List<Mensalidade> mensalidadesList = new ArrayList<>();
        String sql = "SELECT id_mensalidade, data, valor, vencimento, status, aluno_id FROM mensalidades";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                mensalidadesList.add(new Mensalidade(
                        rs.getInt("id_mensalidade"),
                        rs.getDate("data").toLocalDate(), // Converte java.sql.Date para LocalDate
                        rs.getDouble("valor"),
                        rs.getDate("vencimento").toLocalDate(),
                        rs.getString("status"),
                        rs.getInt("aluno_id")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mensalidadesList;
    }
}