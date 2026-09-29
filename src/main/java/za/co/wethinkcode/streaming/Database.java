package za.co.wethinkcode.streaming;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection connect(String path) throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + path);
    }

    public static Connection connectInMemory() throws SQLException {
        return connect(":memory:");
    }
}

