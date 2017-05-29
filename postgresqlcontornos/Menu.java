/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postgresqlcontornos;

import javax.swing.JOptionPane;

/**
 *
 * @author adri
 */
public class Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // ClaseConexion object, it connects itself to the DB
        ClaseConexion cC = new ClaseConexion();
        
        // Attributes for the DB table
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
        String name = JOptionPane.showInputDialog("Nombre: ");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad: "));
        String address = JOptionPane.showInputDialog("Pais: ");
        float salario = Float.parseFloat(JOptionPane.showInputDialog("Salario: "));
        
        cC.insertarDatos(id, name, edad, address, salario);
        cC.modificarDatosNumericos("salary", 3000f, 1);
        cC.modificarDatosString("name", "Arturo", 1);
        cC.cerrarConexion();
        
            
        
        
    }

}
