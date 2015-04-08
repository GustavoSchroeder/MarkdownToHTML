/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabII.TrabalhoGB.TrabalhoMarkdowntoHTML;

/**
 *
 * @author gschroeder
 */
public class ItemN extends Sintaxe {

    private String content;
    private boolean breakLine;
    private int level;
    private boolean end;

    public ItemN(String content, boolean breakLine, int level, boolean end) {
        super(content);
        this.breakLine = breakLine;
        this.level = level;
        this.end = end;
    }

    public boolean getBreakLine() {
        return breakLine;
    }

    public void setBreakLine(boolean breakLine) {
        this.breakLine = breakLine;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Item{" + "level=" + level + ", content=" + "\"" + content + "\"" + ", end=" + end + ", breakLine=" + breakLine + '}';
    }
}
