package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionBD {

    private Connection connection;


    public ConectionBD() {
    }


    public Connection Conectar(){

        try{
            connection = DriverManager.getConnection("jdbc:postgresql://ep-flat-mode-ach20ejx-pooler.sa-east-1.aws.neon.tech/neondb?sslmode=require","neondb_owner","npg_IFMW0KvXfH3j");
            System.out.println("Conectou");
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return connection;

    }




}
