package DatabaseSetup;

import java.sql.*;

public class Database {

    private static Connection con;
    private static String connectionString = "jdbc:mysql://127.0.0.1:3306/h_m_s";
    private static String username = "root";
    private static String password = "";

    public static Connection getConnection() throws SQLException {
        if (con == null) {
            con = DriverManager.getConnection(connectionString, username, password);
        }
        return con;
    }
}
