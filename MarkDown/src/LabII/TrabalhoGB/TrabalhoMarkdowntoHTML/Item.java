package LabII.TrabalhoGB.TrabalhoMarkdowntoHTML;

/**
 *
 * @author Gustavo Lazarotto Schroeder
 */
public class Item extends Sintaxe {

    private int level;
    private boolean breakline;
    private boolean end;

    public Item(String content, int level, boolean breakline, boolean end) {
        super(content);
        this.level = level;
        this.breakline = breakline;
        this.end = end;
    }
     
    
    public int getLevel() {
        return level;
    }

    public boolean isBreakline() {
        return breakline;
    }

    public boolean isEnd() {
        return end;
    }
    public void setEnd(boolean end){
        this.end = end;
    }
    

    @Override
    public String toString() {
        return "<li>" + level +  " " + super.getContent() + "</li>";
    }

}
