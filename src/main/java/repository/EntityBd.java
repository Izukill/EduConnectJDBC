package repository;

import java.util.List;

public interface EntityBd<T> {

    public void salvar(T entidade);

    public void deletar(int id);

    public void atualizar(T entidade);

    public List<T> listar();

}
