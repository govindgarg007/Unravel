import com.zaxxer.hikari.HikariDataSource; // Import HikariDataSource for connection pooling
import org.springframework.beans.factory.annotation.Autowired; // Import for dependency injection
import org.springframework.stereotype.Component; // Import for Spring component annotation

import javax.sql.DataSource; // Import for DataSource interface
import java.sql.Connection; // Import for SQL Connection
import java.sql.SQLException; // Import for SQL exceptions
import java.util.logging.Logger; // Import for logging

/**
 * DatabaseManager is responsible for managing database connections.
 * It uses a DataSource to obtain connections and ensures proper handling of resources.
 */
@Component
public class DatabaseManager {
    // Logger instance for logging database-related messages
    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());

    // DataSource is injected by Spring to handle database connections
    @Autowired
    private DataSource dataSource;

    /**
     * Retrieves a connection from the DataSource.
     * Logs a warning if the connection is closed.
     *
     * @return The database connection.
     * @throws SQLException If there is an issue obtaining the connection.
     */
    public Connection getConnection() throws SQLException {
        // Obtain a connection from the DataSource
        Connection connection = dataSource.getConnection();

        // Check if the connection is closed and log a warning if so
        if (connection.isClosed()) {
            LOGGER.warning("Connection was closed.");
        }

        return connection;
    }

    /**
     * Closes the provided database connection.
     * Logs an error if an exception occurs during closing.
     *
     * @param connection The database connection to close.
     */
    public void closeConnection(Connection connection) {
        try {
            // Check if the connection is not null and not already closed
            if (connection != null && !connection.isClosed()) {
                // Close the connection
                connection.close();
            }
        } catch (SQLException e) {
            // Log an error if an exception occurs while closing the connection
            LOGGER.severe("Error closing connection: " + e.getMessage());
        }
    }
}
