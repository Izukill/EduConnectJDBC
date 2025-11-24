package Service;

import RedisService.NotaRedis;
import entidades.Nota;
import repository.NotaRepository;

public class NotaService {

    private NotaRedis notaRedis;
    private NotaRepository notaRepository;


    public NotaService(NotaRedis notaRedis, NotaRepository notaRepository) {
        this.notaRedis = notaRedis;
        this.notaRepository = notaRepository;
    }

    public void salvarNota(Nota nota){

        notaRepository.salvar(nota);
        notaRedis.adicionarHistorico(nota);
        notaRedis.atualizarRanking(nota);


    }


}
