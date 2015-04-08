package LabII.TrabalhoGB.TrabalhoMarkdowntoHTML;

import javax.swing.JOptionPane;

public class StyleNotFoundException extends Exception{
    
    public void geraMensagem(){
        JOptionPane.showMessageDialog(null, "O Estilo não foi encontrado!");
    }
    
}
