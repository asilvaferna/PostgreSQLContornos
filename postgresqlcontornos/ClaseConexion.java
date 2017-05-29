package postgresqlcontornos;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * ClaseConexion is the class that is used to manage all the operations related
 * to the database.
 *
 * @author adri
 */
public class ClaseConexion {

    private String url;
    private String user;
    private String pwd;
    private Connection c;
    private Statement stmt;

    /**
     * Constructor of the class, it connects itself to the DB.
     */
    public ClaseConexion() {
        url = "jdbc:postgresql://localhost:5432/dbcd";
        conectar();

    }

    /**
     * Constructor of the class, it connects to the DB url.
     *
     * @param url
     */
    public ClaseConexion(String url) {
        this.url = url;
        conectar();

    }

    /**
     * Insert data into a table.
     *
     * @param id
     * @param name
     * @param edad
     * @param address
     * @param salario
     * @exception SQLException if the statement fails.
     * @return 0 if exception, 1 otherwise.
     */
    public int insertarDatos(int id, String name, int edad, String address, float salario) {
        String sqlQuery = null;
        sqlQuery = "insert into company" + " (id, name, age, address, salary)" + " values (" + id + ", '" + name + "', " + edad + ", '" + address + "', " + salario + ")";
        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sqlQuery);
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error en el statement");
            return 0;
        }
        return 1;
    }

    /**
     * Checks if the conexion to the DB is correct
     *
     * @return true if the conexion is permited
     * @return false if the conexion is not permited.
     */
    public boolean conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, "adrian", "root");
            return true;
        } catch (SQLException ex) {
            System.out.println("Conexion no válida");
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }

    /**
     * Deletes a row of the table 'prueba' that is identified by 'id'.
     *
     * @param id
     * @return 0 if exception, 1 otherwise.
     */
    public int borrarDatos(int id) {
        try {
            stmt = c.createStatement();
            String sql = "DELETE from company where id=" + id;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            return 0;
        }
        return 1;
    }

    /**
     * Closes the conection to the DB server
     */
    public void cerrarConexion() {
        try {
            c.close();
        } catch (SQLException ex) {
            System.out.println("Fallo al cerrar la conexion");
        }
    }

    /**
     * Modifies the String values of the table, one each time.
     *
     * @param tableName
     * @param campo
     * @param valor
     * @param identifier
     * @return 0 if exception, 1 otherwise.
     */
    public int modificarDatosString(String campo, String valor, int identifier) {
        try {
            stmt = c.createStatement();
            String sql = "UPDATE company set " + campo + "= '" + valor + "' where ID=" + identifier + ";";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Campo no valido");
            return 0;
        }
        return 1;
    }

    /**
     * Modifies the numeric values of the table, one each time.
     *
     * @param tableName
     * @param campo
     * @param valor
     * @param identifier
     * @return 0 if exception, 1 otherwise.
     */
    public int modificarDatosNumericos(String campo, Float valor, int identifier) {
        try {
            stmt = c.createStatement();
            String sql = "UPDATE company set " + campo + "= " + valor + " where ID=" + identifier + ";";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Campo no valido");
            return 0;
        }
        return 1;
    }

}
