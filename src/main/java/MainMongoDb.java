import config.MongoConnection;
import MongoDbService.PresencaMongoDb;

import java.time.LocalDate;

public class MainMongoDb {

    public static void main(String[] args) {


        MongoConnection mongoConnection= new MongoConnection();

        PresencaMongoDb mongoRepository = new PresencaMongoDb(mongoConnection);

        //envia uma presença de teste
        mongoRepository.salvar(22, 101, "PRESENTE", LocalDate.of(2025,11,06));


    }
}
