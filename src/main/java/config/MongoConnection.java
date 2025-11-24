package config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static final String connection= "mongodb+srv://luanloreto_db_user:6tNQKfLwajCsGFIi@clustermy.acolqyr.mongodb.net/";

    private static final String bdNome= "eduConnect";

    private static MongoClient cliente = null;

    public MongoConnection() {

    }

    public MongoDatabase getConexao(){

        if(cliente == null){
            cliente= MongoClients.create(connection);
        }

        return cliente.getDatabase(bdNome);

    }
}
