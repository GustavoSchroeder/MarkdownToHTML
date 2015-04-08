package LabII.TrabalhoGB.TrabalhoMarkdowntoHTML;

/**
 *
 * @author Gustavo Lazarotto Schroeder
 */
public class Title extends Sintaxe {

    private final int NIVEL;

    public Title(String título, int nível) {
        super(título);
        this.NIVEL = nível;
    }

    public int getNivel() {
        return NIVEL;
    }

    @Override
    public String toString(){
        if (NIVEL == 1) {
            return "<title><h1>" + super.getContent() + "</h1></title>";
        }
        else {
            return "<h2>"+super.getContent()+"</h2>";
        }
       
    }
    /* public void exibeDados(){
     System.out.println("Level: " + nível + "Título: " + content);
     }
     */

}
