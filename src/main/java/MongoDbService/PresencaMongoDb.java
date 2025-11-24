package MongoDbService;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import config.MongoConnection;
import org.bson.Document;
import org.bson.conversions.Bson;
import entidades.Presenca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PresencaMongoDb {

    private final MongoCollection<Document> collection;

    private MongoDatabase mongoDb;


    public PresencaMongoDb(MongoConnection conection) {
        this.mongoDb = conection.getConexao();
        this.collection = mongoDb.getCollection("presenca") ;
    }





    public void salvar(int idAluno, int idAula, String status, LocalDate dataAula) {

        Document presenca= new Document("idAluno", idAluno)
                .append("idAula", idAula)
                .append("status", status)
                .append("data", dataAula);

        collection.insertOne(presenca);

    }


    public void deletar(int idAluno, int idAula, LocalDate dataAula) {

        Bson filtro = Filters.and(
                Filters.eq("idAluno", idAluno),
                Filters.eq("idAula", idAula),
                Filters.eq("data", dataAula)
        );

        collection.deleteOne(filtro);


    }


    public void atualizar(int idAluno, int idAula, LocalDate dataAula, String novoStatus) {

        //primeiro um filtro para achar o que atualizar
        Bson filtro = Filters.and(
                Filters.eq("idAluno", idAluno),
                Filters.eq("idAula", idAula),
                Filters.eq("data", dataAula)
        );


        //depois seta o update e o campo que vai atulizar
        Bson update= Updates.set("status", novoStatus);


        //executa o update passando o filtro e a operação
        collection.updateOne(filtro,update);


    }


    public List<Presenca> listarPresencaAluno(int idAluno){

        List<Presenca> presencas= new ArrayList<>();

        Bson filtro= Filters.eq("idAluno", idAluno);

        FindIterable<Document> documentosEncontrados = collection.find(filtro);

        try (MongoCursor<Document> cursor = documentosEncontrados.iterator()) {

            while (cursor.hasNext()) {
                //pega o documento atual do MongoDB
                Document doc = cursor.next();

                //converte o Document para uma instancia de presenca
                Presenca presenca = new Presenca();
                presenca.setId_aluno(doc.getInteger("idAluno"));
                presenca.setId_aula(doc.getInteger("idAula"));
                presenca.setStatus(doc.getString("status"));
                presenca.setObjetoId(doc.getObjectId("_id"));
                presenca.setDataAula(doc.get("dataAula", LocalDate.class));//precisa converter o tipo

                presencas.add(presenca);
            }
        }

        return presencas;


    }


    public List<Presenca> listar() {

        List<Presenca> presencas = new ArrayList<>();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Presenca presenca = new Presenca();
                presencas.add(presenca);
            }
        }
        return presencas;

    }
}
