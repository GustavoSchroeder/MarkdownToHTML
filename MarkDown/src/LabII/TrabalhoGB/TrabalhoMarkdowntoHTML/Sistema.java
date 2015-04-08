package LabII.TrabalhoGB.TrabalhoMarkdowntoHTML;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Sistema {

    public static void main(String[] args) {
        LeitoraHTML l = new LeitoraHTML();
        try {
            l.fazLeitura();
            l.gravaDados();
        } catch (FileDontExistException e) {
            e.showReason();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar entrar em contato com o arquivo!");
        }
    }
}
