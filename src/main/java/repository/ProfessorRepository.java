package repository;

import entidades.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository implements EntityBd<Professor> {

    private Connection connectionbd;

    private PessoaRepository pessoaRepository;

    public ProfessorRepository(Connection connectionbd) {
        this.connectionbd = connectionbd;
        this.pessoaRepository = new PessoaRepository(connectionbd);
    }

    @Override
    public void salvar(Professor entidade) {

        pessoaRepository.salvar(entidade);

        String sql= "INSERT into professores (pessoa_id, salario) VALUES (?, ?) ";

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
    public void atualizar(Professor entidade) {

        pessoaRepository.atualizar(entidade);


        String sql= "UPDATE professores SET salario = ? WHERE pessoa_id = ? ";

        try(PreparedStatement stmt= connectionbd.prepareStatement(sql)){


            stmt.setFloat(1,entidade.getSalario());
            stmt.setInt(2,entidade.getId());
            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public List<Professor> listar() {


        List<Professor> professoresList=new ArrayList<>();

        String sql= "SELECT pr.salario, p.id AS pessoa_id, p.nome, p.email, p.cpf, p.telefone, p.senha_hash " +
                "FROM professores pr " +
                "JOIN pessoas p ON pr.pessoa_id = p.id";

        try(PreparedStatement stmt= connectionbd.prepareStatement(sql)){

            ResultSet rs=stmt.executeQuery();

            while (rs.next()){
                professoresList.add(new Professor(
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

        return professoresList;



    }
}
