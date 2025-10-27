package repository;

import org.example.entidades.Nota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaRepository implements EntityBd<Nota> {

    private Connection connectionBd;

    public NotaRepository(Connection connectionBd) {
        this.connectionBd = connectionBd;
    }

    @Override
    public void salvar(Nota entidade) {
        String sql = "INSERT INTO notas (nota_linguagens, nota_ciencias_humanas, nota_ciencias_natureza, nota_redacao, aluno_id, simulado_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, entidade.getNotaLinguagens());
            stmt.setDouble(2, entidade.getNotaCienciasHumanas());
            stmt.setDouble(3, entidade.getNotaCienciasNatureza());
            stmt.setDouble(4, entidade.getNotaRedacao());
            stmt.setInt(5, entidade.getIdAluno());
            stmt.setInt(6, entidade.getIdSimulado());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entidade.setIdNota(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM notas WHERE id_nota = ?";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Nota entidade) {
        String sql = "UPDATE notas SET nota_linguagens = ?, nota_ciencias_humanas = ?, nota_ciencias_natureza = ?, nota_redacao = ?, aluno_id = ?, simulado_id = ? WHERE id_nota = ?";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {

            stmt.setDouble(1, entidade.getNotaLinguagens());
            stmt.setDouble(2, entidade.getNotaCienciasHumanas());
            stmt.setDouble(3, entidade.getNotaCienciasNatureza());
            stmt.setDouble(4, entidade.getNotaRedacao());
            stmt.setInt(5, entidade.getIdAluno());
            stmt.setInt(6, entidade.getIdSimulado());
            stmt.setInt(7, entidade.getIdNota()); // ID para o WHERE

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Nota> listar() {
        List<Nota> notasList = new ArrayList<>();
        String sql = "SELECT id_nota, nota_linguagens, nota_ciencias_humanas, nota_ciencias_natureza, nota_redacao, aluno_id, simulado_id FROM notas";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                notasList.add(new Nota(
                        rs.getInt("id_nota"),
                        rs.getDouble("nota_linguagens"),
                        rs.getDouble("nota_ciencias_humanas"),
                        rs.getDouble("nota_ciencias_natureza"),
                        rs.getDouble("nota_redacao"),
                        rs.getInt("aluno_id"),
                        rs.getInt("simulado_id")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return notasList;
    }
}