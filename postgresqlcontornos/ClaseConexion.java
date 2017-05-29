/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author adri
 */
public class ClaseConexion {

    private String url;
    private String user;
    private String pwd;
    private Connection c;
    private Statement stmt;

    public ClaseConexion() {
        url = "jdbc:postgresql://localhost:5432/dbcd";
        conectar();
        leerCredenciales();
    }

    public ClaseConexion(String url) {
        this.url = url;
        conectar();
        leerCredenciales();
    }

    /**
     * This method reads the credentials for the DB user.
     *
     * @exception FileNotFoundException
     *
     */
    public void leerCredenciales() {
        Scanner sc = null;
        boolean b = true;
        try {
            sc = new Scanner(new File("/home/adri/NetBeansProjects/PostgreSQLContornos/user.txt"));
            while (sc.hasNext()) {
                if (b) {
                    user = sc.next();
                    b = false;
                } else {
                    pwd = sc.next();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 " + ex.getMessage());
        } finally {
            sc.close();
        }
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
        sqlQuery = "insert into prueba" + " (id, name, age, address, salario)" + " values (" + id + ", '" + name + "', " + edad + ", '" + address + "', " + salario + ")";
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
            c = DriverManager.getConnection(url, user, pwd);
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
            String sql = "DELETE from PRUEBA where id=" + id;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            return 0;
        }
        return 1;
    }

    /**
     * Closes the conection to the DB server.
     */
    
    public void cerrarConexion() {
        try {
            c.close();
        } catch (SQLException ex) {
            System.out.println("Fallo al cerrar la conexion");
        }
    }

}
