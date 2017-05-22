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
        leerCredenciales();
         
    }
    
    /**
     * This method reads the credentials for the DB user.
     * @exception FileNotFoundException
     * 
     */
    public void leerCredenciales(){
        Scanner sc = null;
        boolean b = true;
        try {
            sc = new Scanner(new File("/home/adri/NetBeansProjects/PostgreSQLContornos/user.txt"));
            while (sc.hasNext()) {
                if(b){
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
     * @param tableName 
     * @exception SQLException if the statement fails.
     */
    public void insertarDatos(String tableName){
        String sqlQuery = null;
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
        String name = JOptionPane.showInputDialog("Nombre: ");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad: "));
        String address = JOptionPane.showInputDialog("Pais: ");
        float salario = Float.parseFloat(JOptionPane.showInputDialog("Salario: "));
        sqlQuery = "insert into " + tableName + " (id, name, age, address, salario)" + " values (" + id + ", " + name + ", " + edad + ", " + address + ", " + salario + ")"; 
        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sqlQuery);
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error en el statement");
        }
    }
    
    /**
     * Checks if the conexion to the DB is correct
     * @return true if the conexion is permited
     * @return false if the conexion is not permited.
     */
    public boolean conectar(){
        try {
            c = DriverManager.getConnection(url, user, pwd);
            return true;
        } catch (SQLException ex) {
            System.out.println("Conexion no válida");
            return false;
        }
    }
    
    
    
}
