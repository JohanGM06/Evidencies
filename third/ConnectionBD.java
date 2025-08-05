package third;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {

    private static final String URL = "jdbc:mysql://localhost:3307/biblioteca";
    private static final String USUARIO = "root";
    private static final String CLAVE = "Aqui va una contraseña";

    public static Connection conectar() throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        try {
            Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Successful connection to the database.");
            return con;
        } catch (SQLException e) {
            System.out.println("Error to connection: " + e.getMessage());
            return null;
        }
    }

}
