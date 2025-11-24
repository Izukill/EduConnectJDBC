package repository;


import entidades.Coordenador;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoordenadorRepository implements EntityBd<Coordenador> {

    private Connection connectionbd;

    private PessoaRepository pessoaRepository;



    public CoordenadorRepository(Connection connection) {
        this.connectionbd= connection;
        this.pessoaRepository= new PessoaRepository(connection);
    }

    @Override
    public void salvar(Coordenador entidade) {

        pessoaRepository.salvar(entidade);

        String sql= "INSERT into coordenadores (pessoa_id, salario) VALUES (?, ?) ";

        try(PreparedStatement stmt = connectionbd.prepareStatement(sql)){

            stmt.setLong(1,entidade.getId());
            stmt.setFloat(2,entidade.getSalario());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void deletar(int id) {

        String sql= "DELETE FROM coordenadores WHERE pessoa_id= ?";

        try(PreparedStatement stmt= connectionbd.prepareStatement(sql)){

            stmt.setInt(1,id);
            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        pessoaRepository.deletar(id);

    }

    @Override
    public void atualizar(Coordenador entidade) {


        pessoaRepository.atualizar(entidade);


        String sql= "UPDATE coordenadores SET salario = ? WHERE pessoa_id = ? ";

        try(PreparedStatement stmt= connectionbd.prepareStatement(sql)){


            stmt.setFloat(1,entidade.getSalario());
            stmt.setInt(2,entidade.getId());
            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public List<Coordenador> listar() {

        List<Coordenador> coordenadoresList=new ArrayList<>();

        String sql= "SELECT c.salario, p.id AS pessoa_id, p.nome, p.email, p.cpf, p.telefone, p.senha_hash " +
                "FROM coordenadores c " +
                "JOIN pessoas p ON c.pessoa_id = p.id";

        try(PreparedStatement stmt= connectionbd.prepareStatement(sql)){

            ResultSet rs=stmt.executeQuery();

            while (rs.next()){
                coordenadoresList.add(new Coordenador(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("senha_hash"),
                        rs.getLong("salario")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return coordenadoresList;
    }
}
