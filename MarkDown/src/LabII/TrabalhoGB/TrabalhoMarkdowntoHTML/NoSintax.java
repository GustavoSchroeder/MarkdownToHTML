package LabII.TrabalhoGB.TrabalhoMarkdowntoHTML;

/**
 *
 * @author gschroeder
 */
public class NoSintax extends Sintaxe{

    public NoSintax(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return super.getContent();
    }
}
