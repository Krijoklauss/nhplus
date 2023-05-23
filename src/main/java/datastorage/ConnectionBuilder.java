package datastorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The ConnectionBuilder 
 */
public class ConnectionBuilder {
    private static Connection conn;

    /**
     * Private constructor to prevent object creation.
     * It creates a new Connection object to the database.
     * If the driver is missing or it can't establish SQL connection, it prints out the error.
     */
    private ConnectionBuilder() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            conn = DriverManager.getConnection("jdbc:hsqldb:db/nursingHomeDB;shutdown=true;user=SA;password=SA");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Treiberklasse konnte nicht gefunden werden!");
        } catch (SQLException e) {
            System.out.println("Verbindung zur Datenbank konnte nicht aufgebaut werden!");
            e.printStackTrace();
        }
    }
    /**
     * Returns a Connection object to the database. If no connection exists, it creates a new one using the private constructor.
     * @return the Connection object to the database.
     */
    public static Connection getConnection() {
        if (conn == null) {
            new ConnectionBuilder();
        }
        return conn;
    }
    /**
     * Closes the connection to the database.
     */
    public static void closeConnection() {
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
