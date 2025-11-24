package RedisService;

import com.google.gson.Gson;
import config.RedisConnection;
import entidades.Nota;
import redis.clients.jedis.Jedis;

public class NotaRedis {

    private final Gson gson = new Gson();


    public void salvarNota(Nota nota) {
        try (Jedis jedis = RedisConnection.getConexao()) {
            String key = "nota:" + nota.getIdNota();
            String json = gson.toJson(nota);
            jedis.set(key, json);
        }
    }

    // BUSCA A NOTA COMPLETA PELO ID
    public Nota buscarNota(int idNota) {
        try (Jedis jedis = RedisConnection.getConexao()) {
            String key = "nota:" + idNota;
            String json = jedis.get(key);

            if (json == null) return null;

            return gson.fromJson(json, Nota.class);
        }
    }




    public void atualizarRanking(Nota nota) {
        double total = nota.getNotaLinguagens()
                + nota.getNotaCienciasHumanas()
                + nota.getNotaCienciasNatureza()
                + nota.getNotaRedacao()
                + nota.getNotaMatematica();

        try (Jedis jedis = RedisConnection.getConexao()) {
            jedis.zadd("ranking:simulado:" + nota.getIdSimulado(),
                    total,
                    "aluno:" + nota.getIdAluno());
        }
    }

    // histórico de notas por aluno
    public void adicionarHistorico(Nota nota) {
        try (Jedis jedis = RedisConnection.getConexao()) {
            jedis.rpush("notas:aluno:" + nota.getIdAluno(), String.valueOf(nota.getIdNota()));
        }
    }




}
