package LabII.TrabalhoGB.TrabalhoMarkdowntoHTML;

import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo Lazarotto Schroeder
 */
public class FileDontExistException extends Exception{

    @Override
    public synchronized Throwable getCause() {
        return super.getCause(); //To change body of generated methods, choose Tools | Templates.
    }
    public void showReason(){
        JOptionPane.showMessageDialog(null, "Nâo foi possível abrir o arquivo solicitado, "
                + "desde já peço desculpas");
        System.out.println("Razão: " + getCause());
    }
    
}
