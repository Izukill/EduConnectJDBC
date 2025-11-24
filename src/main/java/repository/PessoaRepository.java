package repository;

import entidades.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepository implements EntityBd<Pessoa> {

    protected Connection connectionBd;

    public PessoaRepository(Connection connectionBd) {
        this.connectionBd = connectionBd;
    }

    @Override
    public void salvar(Pessoa entidade) {

        String sql= "INSERT INTO pessoas (nome, email, cpf, telefone, senha_hash) VALUES (?,?,?,?,?) RETURNING id";


        try(PreparedStatement stmt = connectionBd.prepareStatement(sql)){

            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getEmail());
            stmt.setString(3, entidade.getCpf());
            stmt.setString(4, entidade.getTelefone());
            stmt.setString(5, entidade.getSenha_hash());

            ResultSet rs= stmt.executeQuery();

            if (rs.next()) {
                entidade.setId(rs.getInt("id"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deletar(int id) {

        String sql= "DELETE FROM pessoas WHERE id= ?";

        try(PreparedStatement stmt= connectionBd.prepareStatement(sql)){

            stmt.setInt(1,id);
            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void atualizar(Pessoa entidade) {

        String sql= "UPDATE pessoas SET nome = ?, email= ?, cpf= ?, telefone= ?, senha_hash= ? WHERE id = ? ";

        try(PreparedStatement stmt= connectionBd.prepareStatement(sql)){

            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getEmail());
            stmt.setString(3, entidade.getCpf());
            stmt.setString(4, entidade.getTelefone());
            stmt.setString(5, entidade.getSenha_hash());
            stmt.setInt(6, entidade.getId());

            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Pessoa> listar() {


        List<Pessoa> pessoaList=new ArrayList<>();

        String sql= "SELECT * FROM pessoas";

        try(PreparedStatement stmt= connectionBd.prepareStatement(sql)){

            ResultSet rs=stmt.executeQuery();

            while (rs.next()){
                pessoaList.add(new Pessoa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("senha_hash")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoaList;

    }
}
