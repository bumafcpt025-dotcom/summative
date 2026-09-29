package za.co.wethinkcode.streaming;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class StreamSchemaTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = Database.connectInMemory();
        StreamSchema.createSchema(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.close();
    }

    // ── viewers table ─────────────────────────────────────────

    @Test
    void viewersTable_hasIdAsPrimaryKey() throws SQLException {
        assertThat(primaryKeyFlags("viewers")).containsEntry("id", true);
    }

    @Test
    void viewersTable_usernameIsNotNull() throws SQLException {
        assertThat(notNullFlags("viewers")).containsEntry("username", true);
    }

    @Test
    void viewersTable_emailIsNotNull() throws SQLException {
        assertThat(notNullFlags("viewers")).containsEntry("email", true);
    }

    @Test
    void viewersTable_acceptsValidInsert() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            assertThat(stmt.executeUpdate(
                "INSERT INTO viewers (username, email) VALUES ('alice', 'alice@sw.com')"
            )).isEqualTo(1);
        }
    }

    @Test
    void viewersTable_rejectsNullUsername() {
        org.junit.jupiter.api.Assertions.assertThrows(SQLException.class, () -> {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("INSERT INTO viewers (email) VALUES ('x@sw.com')");
            }
        });
    }

    @Test
    void viewersTable_rejectsNullEmail() {
        org.junit.jupiter.api.Assertions.assertThrows(SQLException.class, () -> {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("INSERT INTO viewers (username) VALUES ('alice')");
            }
        });
    }

    // ── watch_history table ───────────────────────────────────

    @Test
    void watchHistoryTable_hasIdAsPrimaryKey() throws SQLException {
        assertThat(primaryKeyFlags("watch_history")).containsEntry("id", true);
    }

    @Test
    void watchHistoryTable_viewerIdIsNotNull() throws SQLException {
        assertThat(notNullFlags("watch_history")).containsEntry("viewer_id", true);
    }

    @Test
    void watchHistoryTable_titleIsNotNull() throws SQLException {
        assertThat(notNullFlags("watch_history")).containsEntry("title", true);
    }

    @Test
    void watchHistoryTable_watchedAtIsNotNull() throws SQLException {
        assertThat(notNullFlags("watch_history")).containsEntry("watched_at", true);
    }

    @Test
    void watchHistoryTable_ratingIsNullable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO viewers (username, email) VALUES ('bob','bob@sw.com')");
            assertThat(stmt.executeUpdate(
                "INSERT INTO watch_history (viewer_id, title, watched_at) " +
                "VALUES (1, 'Succession', '2026-08-01T20:00')"
            )).isEqualTo(1);
        }
    }

    // ── foreign key ───────────────────────────────────────────

    @Test
    void watchHistory_viewerIdForeignKey_rejectsUnknownViewer() {
        org.junit.jupiter.api.Assertions.assertThrows(SQLException.class, () -> {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON");
                stmt.execute(
                    "INSERT INTO watch_history (viewer_id, title, watched_at) " +
                    "VALUES (999, 'Severance', '2026-08-02T21:00')"
                );
            }
        });
    }

    @Test
    void watchHistory_viewerIdForeignKey_declaresReference() throws SQLException {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("PRAGMA foreign_key_list(watch_history)")) {
            assertThat(rs.next()).as("watch_history should declare a foreign key").isTrue();
            assertThat(rs.getString("table")).isEqualTo("viewers");
            assertThat(rs.getString("from")).isEqualTo("viewer_id");
            assertThat(rs.getString("to")).isEqualTo("id");
        }
    }

    // ── helpers ───────────────────────────────────────────────

    private Map<String, Boolean> primaryKeyFlags(String table) throws SQLException {
        return columnFlags(table, "pk");
    }

    private Map<String, Boolean> notNullFlags(String table) throws SQLException {
        return columnFlags(table, "notnull");
    }

    private Map<String, Boolean> columnFlags(String table, String flag) throws SQLException {
        Map<String, Boolean> result = new HashMap<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs   = stmt.executeQuery("PRAGMA table_info(" + table + ")")) {
            while (rs.next()) {
                result.put(rs.getString("name"), rs.getInt(flag) > 0);
            }
        }
        return result;
    }
}
