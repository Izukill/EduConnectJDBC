package repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import config.MongoConnection;
import org.bson.Document;
import org.example.entidades.Presenca;

import java.util.List;

public class PresencaMongoRepository implements EntityBd<Presenca> {

    private final MongoCollection<Document> collection;

    private MongoDatabase mongoDb;


    public PresencaMongoRepository(MongoConnection conection) {
        this.mongoDb = conection.getConexao();
        this.collection = mongoDb.getCollection("presenca") ;
    }


    public void salvarPresenca(int idAluno,int idAula, String status){

        Document presenca= new Document("idAluno", idAluno)
                .append("idAula", idAula)
                .append("status", status);

        collection.insertOne(presenca);

    }


    @Override
    public void salvar(Presenca entidade) {

    }

    @Override
    public void deletar(int id) {

    }

    @Override
    public void atualizar(Presenca entidade) {

    }

    @Override
    public List<Presenca> listar() {
        return List.of();
    }
}
