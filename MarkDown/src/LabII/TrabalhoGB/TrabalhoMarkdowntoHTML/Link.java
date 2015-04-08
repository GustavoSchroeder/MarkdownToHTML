package LabII.TrabalhoGB.TrabalhoMarkdowntoHTML;

/**
 *
 * @author Gustavo Lazarotto Schroeder
 */
public class Link extends Sintaxe {

    private boolean breakline;

    public Link(String referencia) {
        super(referencia);
        this.breakline = true;
    }

    public boolean isBreakline() {
        return breakline;
    }

    @Override
    public String toString() {
        return "<a href=\"" + super.getContent() + "\"target=\"_blank\"";
    }
    /*
     public void exibeDados(){
     System.out.print("Content: " + "Link: " + content);
     }
     */
}
