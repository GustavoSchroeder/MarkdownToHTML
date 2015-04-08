package LabII.TrabalhoGB.TrabalhoMarkdowntoHTML;

/**
 *
 * @author Gustavo Lazarotto Schroeder
 */
public class Text extends Sintaxe {
    /*representa um elemento de texto que pode conter ou não formatação 
     (atributo style) e pode terminar ou não com quebra de linha (breakLine). 
     Os estilos suportados no trabalho são: *(I)tálic*, **(B)old** ou _(U)nderline_ */

    private String content;
    private char style;
    private boolean breakLine;

    public Text(String content, char style, boolean breakLine) {
        super(content);
        this.style = style;
        this.breakLine = breakLine;
    }
     public Text(String content, boolean breakLine) {
        super(content);
        this.style = 'z';
        this.breakLine = breakLine;
    }

    public boolean isBreakLine() {
        return breakLine;
    }

    public char getStyle() {
        return style;
    }

    @Override
    public String toString() {
        if (breakLine == true) {
            return super.getContent() + "<br/>";
        }
        return super.getContent();
    }

}
