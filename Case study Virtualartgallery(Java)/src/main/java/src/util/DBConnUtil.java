package src.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {

    public static Connection getConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", PropertyUtil.getProperty("db.user"));
        connectionProps.put("password", PropertyUtil.getProperty("db.password"));

        String connectionString = "jdbc:mysql://" +
                PropertyUtil.getProperty("db.host") + ":" +
                PropertyUtil.getProperty("db.port") + "/" +
                PropertyUtil.getProperty("db.name");

        return DriverManager.getConnection(connectionString, connectionProps);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
