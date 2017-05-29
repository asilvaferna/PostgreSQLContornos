package postgresqlcontornos;

import javax.swing.JOptionPane;

/**
 * Menu class that uses the ClaseConexion methods.
 * @author adri
 */
public class Menu {

    /**
     * Main method, we call all the ClaseConexion methods.
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
