import config.MongoConnection;
import repository.PresencaMongoRepository;

public class MainMongoDb {

    public static void main(String[] args) {


        MongoConnection mongoConnection= new MongoConnection();

        PresencaMongoRepository mongoRepository = new PresencaMongoRepository(mongoConnection);

        //envia uma presença de teste
        mongoRepository.salvarPresenca(22, 101, "PRESENTE");


    }
}
