package DataAccessLayer.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


    public class ConnectionFactory {

        private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
        private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private static final String DBURL = "jdbc:sqlserver://10.211.55.5\\ANDREIBRANG72A0:1433;database=StudentsDB";
        private static final String USER = "abranga";
        private static final String PASS = "Axcbts123";

        private static ConnectionFactory singleInstance = new ConnectionFactory();

        private ConnectionFactory() {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private Connection createConnection() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(DBURL, USER, PASS);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
                e.printStackTrace();
            }
            return connection;
        }

        public static Connection getConnection() {
            return singleInstance.createConnection();
        }

        public static void close(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
                }
            }
        }

        public static void close(Statement statement) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
                }
            }
        }

        public static void close(ResultSet resultSet) {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
                }
            }
        }
    }


