package repository;

import entidades.Nota;

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
        String sql = "INSERT INTO notas " +
                "(notalinguagens, notacienciashumanas, notacienciasnatureza, notamatematica, notaredacao, id_aluno, id_simulado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, entidade.getNotaLinguagens());
            stmt.setDouble(2, entidade.getNotaCienciasHumanas());
            stmt.setDouble(3, entidade.getNotaCienciasNatureza());
            stmt.setDouble(4, entidade.getNotaMatematica());
            stmt.setDouble(5, entidade.getNotaRedacao());
            stmt.setInt(6, entidade.getIdAluno());
            stmt.setInt(7, entidade.getIdSimulado());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entidade.setIdNota(generatedKeys.getInt("id"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM notas WHERE id = ?";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Nota entidade) {
        String sql = "UPDATE notas SET " +
                "notalinguagens = ?, notacienciashumanas = ?, notacienciasnatureza = ?, " +
                "notamatematica = ?, notaredacao = ?, id_aluno = ?, id_simulado = ? " +
                "WHERE id = ?";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {

            stmt.setDouble(1, entidade.getNotaLinguagens());
            stmt.setDouble(2, entidade.getNotaCienciasHumanas());
            stmt.setDouble(3, entidade.getNotaCienciasNatureza());
            stmt.setDouble(4, entidade.getNotaMatematica());
            stmt.setDouble(5, entidade.getNotaRedacao());
            stmt.setInt(6, entidade.getIdAluno());
            stmt.setInt(7, entidade.getIdSimulado());
            stmt.setInt(8, entidade.getIdNota());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Nota> listar() {
        List<Nota> notasList = new ArrayList<>();
        String sql = "SELECT * FROM notas";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                notasList.add(new Nota(
                        rs.getInt("id"),
                        rs.getDouble("notalinguagens"),
                        rs.getDouble("notacienciashumanas"),
                        rs.getDouble("notacienciasnatureza"),
                        rs.getDouble("notamatematica"),
                        rs.getDouble("notaredacao"),
                        rs.getInt("id_aluno"),
                        rs.getInt("id_simulado")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return notasList;
    }


    public List<Nota> acharAlunoId(int idAluno) {
        List<Nota> notas = new ArrayList<>();
        String sql = "SELECT * FROM notas WHERE id_aluno = ?";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                notas.add(new Nota(
                        rs.getInt("id"),
                        rs.getDouble("notalinguagens"),
                        rs.getDouble("notacienciashumanas"),
                        rs.getDouble("notacienciasnatureza"),
                        rs.getDouble("notamatematica"),
                        rs.getDouble("notaredacao"),
                        rs.getInt("id_aluno"),
                        rs.getInt("id_simulado")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return notas;
    }


    public List<Nota> acharSimuladoId(int idSimulado) {
        List<Nota> notas = new ArrayList<>();
        String sql = "SELECT * FROM notas WHERE id_simulado = ?";

        try (PreparedStatement stmt = connectionBd.prepareStatement(sql)) {

            stmt.setInt(1, idSimulado);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                notas.add(new Nota(
                        rs.getInt("id"),
                        rs.getDouble("notalinguagens"),
                        rs.getDouble("notacienciashumanas"),
                        rs.getDouble("notacienciasnatureza"),
                        rs.getDouble("notamatematica"),
                        rs.getDouble("notaredacao"),
                        rs.getInt("id_aluno"),
                        rs.getInt("id_simulado")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return notas;
    }


}