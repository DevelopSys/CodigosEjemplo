package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection = null;


    public static void newConnecion() {
        String url = "jdbc:mysql://localhost:3306/almacen";
        String user = "root";
        String pass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        if (connection == null) {
            newConnecion();
        }
        return connection;
    }
}
