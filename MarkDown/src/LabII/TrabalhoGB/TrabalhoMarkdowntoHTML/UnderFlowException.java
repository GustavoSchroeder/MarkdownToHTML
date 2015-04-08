/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabII.TrabalhoGB.TrabalhoMarkdowntoHTML;

import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
class UnderFlowException extends Exception {

    public UnderFlowException() {
        JOptionPane.showMessageDialog(null, "Não existem elementos para serem retirados!");
    }

}
