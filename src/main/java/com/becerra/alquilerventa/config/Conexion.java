package com.becerra.alquilerventa.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream in = Conexion.class.getClassLoader()
                .getResourceAsStream("db.properties")){
            if (in == null) {
                throw new RuntimeException("No se encontró ddb.properties");
            }
            Properties props = new Properties();
            props.load(in);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar la conexión: " + e.getMessage(), e);
        }
    }

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
