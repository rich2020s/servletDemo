package dbFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionManager {
    private ConnectionManager() {
    }
    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null) {
            ResourceBundle bundle = ResourceBundle.getBundle("dbConfig");
            String url = bundle.getString("url");
            String username = bundle.getString("username");
            String password = bundle.getString("password");

            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                System.out.println("Something went wrong when creating the connection");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
