package repository;

import org.example.entidades.Aluno;
import org.example.entidades.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository implements EntityBd<Aluno> {


    private Connection connectionBd;

    private PessoaRepository pessoaRepository;

    public AlunoRepository(Connection connectionBd) {
        this.connectionBd = connectionBd;
        this.pessoaRepository= new PessoaRepository(connectionBd);
    }

    @Override
    public void salvar(Aluno entidade) {

        pessoaRepository.salvar(entidade);

        String sql="INSERT into alunos (pessoa_id, matricula) VALUES (?, ?)"; //TODO adicionar fk de turma

        try(PreparedStatement stmt= connectionBd.prepareStatement(sql)){

            stmt.setInt(1,entidade.getId());
            stmt.setLong(2,entidade.getMatricula());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void deletar(int id) {


        String sql= "DELETE FROM alunos WHERE pessoa_id= ?";

        try(PreparedStatement stmt= connectionBd.prepareStatement(sql)){

            stmt.setInt(1,id);
            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        pessoaRepository.deletar(id);

    }

    @Override
    public void atualizar(Aluno entidade) {

        pessoaRepository.atualizar(entidade);


        String sql= "UPDATE alunos SET matricula = ? WHERE pessoa_id = ? ";

        try(PreparedStatement stmt= connectionBd.prepareStatement(sql)){


            stmt.setLong(1,entidade.getMatricula());
            stmt.setInt(2,entidade.getId());
            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Aluno> listar() {
        List<Aluno> alunosList=new ArrayList<>();

        String sql= "SELECT a.matricula, p.id AS pessoa_id, p.nome, p.email, p.cpf, p.telefone, p.senha_hash " +
                    "FROM alunos a " +
                    "JOIN pessoas p ON a.pessoa_id = p.id";

        try(PreparedStatement stmt= connectionBd.prepareStatement(sql)){

            ResultSet rs=stmt.executeQuery();

            while (rs.next()){
                alunosList.add(new Aluno(
                        rs.getInt("pessoa_id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("senha_hash"),
                        rs.getLong("matricula")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunosList;
    }
}
