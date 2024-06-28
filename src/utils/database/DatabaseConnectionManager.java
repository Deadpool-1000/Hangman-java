package utils.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    public Connection conn;

    private DatabaseConnectionManager(){
        try {
            Dotenv dt = Dotenv.load();
            String DB_URL = dt.get("DB_URL");
            String DB_USERNAME = dt.get("DB_USERNAME");
            String DB_PASSWORD = dt.get("DB_PASSWORD");
            this.conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static DatabaseConnectionManager getInstance(){
        if(instance == null){
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public static void destroyInstance(){
        try{
            if(instance != null){
                instance.conn.close();
                instance = null;
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
