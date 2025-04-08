package es.adrianroguez.database;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static Connection connection = null;

    /**
     * constructor privado para evitar instancias
     */
    private ConnectionDB() {
    }

    /**
     * obtiene la conexión a la base de datos
     * 
     * @return Connection
     * @throws SQLException si ocurre un error en la conexión
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String dbPath = getDatabasePath();
            validarRutaDB(dbPath);
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        }
        return connection;
    }

    /**
     * cierra la conexión con la base de datos
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.err.println("Error cerrando la conexión: " + e.getMessage());
        }
    }

    /**
     * obtiene la ruta absoluta de la base de datos desde resources
     * 
     * @return ruta de la base de datos como String
     * @throws SQLException si el archivo no existe
     */
    private static String getDatabasePath() throws SQLException {
        URL resource = ConnectionDB.class.getClassLoader().getResource("usuarios.db");
        if (resource == null) {
            throw new SQLException("No se encontró la base de datos en resources.");
        }
        return new File(resource.getFile()).getAbsolutePath();
    }

    /**
     * valida que el archivo de la base de datos exista
     * 
     * @param dbPath ruta de la base de datos
     * @throws SQLException si la base de datos no existe
     */
    private static void validarRutaDB(String dbPath) throws SQLException {
        File file = new File(dbPath);
        if (!file.exists()) {
            throw new SQLException("No existe la base de datos en la ruta: " + dbPath);
        }
    }
}
