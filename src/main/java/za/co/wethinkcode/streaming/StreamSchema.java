package za.co.wethinkcode.streaming;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StreamSchema {

    private StreamSchema() {}

    public static void createSchema(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            // TODO Q5.1 – Q5.3: Implement this method.
            //
            // Create the following two tables using CREATE TABLE IF NOT EXISTS:
            //
            // TABLE: viewers
            //   id        INTEGER  PRIMARY KEY
            //   username  TEXT     NOT NULL
            //   email     TEXT     NOT NULL
            //
            // TABLE: watch_history
            //   id          INTEGER  PRIMARY KEY
            //   viewer_id   INTEGER  NOT NULL   (FK -> viewers.id)
            //   title       TEXT     NOT NULL
            //   watched_at  TEXT     NOT NULL
            //   rating      INTEGER  (nullable)
            //
            // IMPORTANT:
            //   - Enable foreign keys first: statement.execute("PRAGMA foreign_keys = ON");
            //   - Create viewers BEFORE watch_history (watch_history references it)
        }
    }
}

