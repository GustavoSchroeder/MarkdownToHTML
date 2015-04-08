package LabII.TrabalhoGB.TrabalhoMarkdowntoHTML;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author gschroeder
 */
public class LeitoraHTML {

    ArrayList<Object> toHTML = new ArrayList<Object>();

    public void fazLeitura() throws FileNotFoundException, FileDontExistException, IOException {
        BufferedReader in;
        FileReader fe;
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { //selecao da arquivo
            fe = new FileReader(jfc.getSelectedFile());
            in = new BufferedReader(fe);
        } else {
            throw new FileDontExistException();
        }
        String line = null;
        char charinicial;
        while ((line = in.readLine()) != null) { //le a linha enquanto nao for null
            if ("".equals(line)) {
                line = in.readLine(); //se a linha for vazia, pula para o proxima linha
            }

            charinicial = line.charAt(0); // salva o primeiro character de cada linha
            //verifica se é titulo
            if (charinicial == '#') {  //verifica se o char inicial e igual a tittle
                if (charinicial == line.charAt(1)) { //se for igual ao segundo tambem
                    String text = line.substring(2);
                    toHTML.add(new Title(text, 2));
                } else {
                    String text = line.substring(1);
                    toHTML.add(new Title(text, 1));
                }
            } //faz verificacao para ter certeza que nao e um titulo
            else if (!line.startsWith("##") && !line.startsWith("#")) {

                //verifica se é item nao numerado
                if (charinicial == '*' && line.charAt(1) == ' ' && charinicial != '#' && charinicial != '1') {
                    //guarda as posicoes o style se houver se nao z!  alem do end e do breakline
                    int pos = 0; 
                    int pos2 = 0;
                    int lastIndex = 0;
                    int level = 1;
                    char style = ' ';
                    boolean end = false;
                    boolean breakline = true;

                    for (int i = 1; i < line.length(); i++) {
                        char current = line.charAt(i); //guarda p cjar corrent
                        if (current == '*') {  //se corrent for igual a * alem do primeiro
                            pos = i;
                            if (line.charAt(i + 1) == '*') { //se houver 2 negrito
                                style = 'n';
                            } else {
                                style = 'i'; //se houver apenas 1 italico
                            }

                            for (int j = pos + 1; j < line.length(); j++) {
                                char current2 = line.charAt(j); //para achar o final do style
                                if (current2 == '*') {
                                    pos2 = j;
                                }
                            }
                            break;
                        }
                        if (current == '_') { //para achar sublinhado
                            pos = i;
                            style = 'u';
                            for (int j = pos + 1; j < line.length(); j++) {
                                char current2 = line.charAt(j); //acha onde termina
                                if (current2 == '_') {
                                    pos2 = j;
                                }
                            }
                            break;
                        }
                        if (current == '[') {  //para links da web
                            pos = i;
                            style = 'l';
                            for (int j = pos + 1; j < line.length(); j++) {
                                char current2 = line.charAt(j);
                                if (current2 == ')') {
                                    pos2 = j;
                                }
                            }
                            break;
                        }
                    }

                    if (pos2 != 0) {  //faz a gravacao do obj na lista (toHTML)

                        if (line.charAt(0) == '*') {
                            toHTML.add(new Item(line.substring(2, pos), level, breakline, end));
                            //pega do substring 2 que e depois dos dois * e do espaco e coloca na lista
                        }
                        for (int x = 0; x < line.length(); x++) {
                            lastIndex = x;
                        }
                        if (pos2 != lastIndex) {
                            breakline = false;  // ve se a posicao2 for diferente do last index o breakline e falso
                        }
                        if (style == 'n') {
                            toHTML.add(new Text(line.substring(pos + 2, pos2 - 1), style, breakline));
                            //se o estilo dado foi n pega da posicao de comeco ate a de final
                        }
                        if (style == 'i') {
                            toHTML.add(new Text(line.substring(pos + 1, pos2), style, breakline));
                            // mesmo caso para i
                        }
                        if (style == 'u') {
                            toHTML.add(new Text(line.substring(pos + 1, pos2), style, breakline));
                            //mesmo casa para u
                        }
                        if (style == 'l') {
                            toHTML.add(new Link(line.substring(pos + 1, pos2)));
                            //mesmo caso para link
                        }
                        if (pos2 != lastIndex) {
                            toHTML.add(new Text(line.substring(pos2 + 1), breakline));
                            //se a posicao 2 for diferente do last index devemos coloca na lista como text normal
                        }
                    } else {
                        toHTML.add(new Item(line.substring(2), level, breakline, end));
                        //e o mesmo para quando nao tem formatacao no comeco
                    }
                }

                //verifica se item de numero
                if (charinicial == '1' && line.charAt(1) == '.' && charinicial != '#') {

                    int pos = 0;
                    int pos2 = 0;
                    int pos3 = 0;
                    int pos4 = 0;
                    int lastIndex = 0;
                    int level = 1;
                    char style = ' ';
                    boolean end = false;
                    boolean breakline = true;

                    for (int i = 1; i < line.length(); i++) {
                        char current = line.charAt(i);
                        if (current == '*') {
                            pos = i;
                            if (line.charAt(i + 1) == '*') {
                                style = 'n';
                            } else {
                                style = 'i';
                            }

                            for (int j = pos + 1; j < line.length(); j++) {
                                char current2 = line.charAt(j);
                                if (current2 == '*') {
                                    pos2 = j;
                                }
                            }
                            break;
                        }
                        if (current == '_') {
                            pos = i;
                            style = 'u';
                            for (int j = pos + 1; j < line.length(); j++) {
                                char current2 = line.charAt(j);
                                if (current2 == '_') {
                                    pos2 = j;
                                }
                            }
                            break;
                        }
                        if (current == '[') {
                            pos = i;
                            style = 'l';
                            for (int j = pos + 1; j < line.length(); j++) {
                                char current2 = line.charAt(j);
                                if (current2 == ']') {
                                    pos2 = j;
                                }
                            }

                        }

                        if (current == '(') {
                            pos3 = pos2;
                            style = 'l';
                            for (int j = pos3 + 1; j < line.length(); j++) {
                                char current2 = line.charAt(j);
                                if (current2 == ')') {
                                    pos4 = j;
                                }
                            }
                            break;
                        }
                    }

                    if (pos2 != 0) {

                        if (line.charAt(0) == '1') {
                            toHTML.add(new ItemN(line.substring(2, pos), breakline, level, end));
                        }
                        for (int a = 0; a < line.length(); a++) {
                            lastIndex = a;
                        }
                        if (pos2 != lastIndex) {
                            breakline = false;
                        }
                        if (style == 'n') {
                            toHTML.add(new Text(line.substring(pos + 2, pos2 - 1), style, breakline));
                        }
                        if (style == 'i') {
                            toHTML.add(new Text(line.substring(pos + 1, pos2), breakline));
                        }
                        if (style == 'u') {
                            toHTML.add(new Text(line.substring(pos + 1, pos2), breakline));
                        }
                        if (style == 'l') {
                            toHTML.add(new Text(line.substring(pos + 1, pos2), breakline));
                            toHTML.add(new Link(line.substring(pos2 + 1, pos4)));
                        }
                        if (pos2 != lastIndex) {
                            toHTML.add(new Text(line.substring(pos2 + 1), true));
                        }
                    } else {
                        toHTML.add(new ItemN(line.substring(2), breakline, level, end));
                    }
                }

                //verifica se é texto normal
                if (charinicial != '1' && charinicial != '*' && charinicial != '*' && charinicial != '#') {
                    int pos = 0;
                    int pos2 = 0;
                    int pos3 = 0;
                    int pos4 = 0;
                    int lastIndex = 0;
                    char style = ' ';
                    boolean breakline = true;

                    for (int i = 0; i < line.length(); i++) {
                        char current = line.charAt(i);
                        if (current == '*') {
                            pos = i;
                            if (line.charAt(i + 1) == '*') {
                                style = 'n';
                            } else {
                                style = 'i';
                            }

                            for (int j = pos + 1; j < line.length(); j++) {
                                char current2 = line.charAt(j);
                                if (current2 == '*') {
                                    pos2 = j;
                                }
                            }
                            break;
                        }
                        if (current == '_') {
                            pos = i;
                            style = 'u';
                            for (int j = pos + 1; j < line.length(); j++) {
                                char current2 = line.charAt(j);
                                if (current2 == '_') {
                                    pos2 = j;
                                }
                            }
                            break;
                        }
                        if (current == '[') {
                            pos = i;
                            style = 'l';
                            for (int j = pos + 1; j < line.length(); j++) {
                                char current2 = line.charAt(j);
                                if (current2 == ']') {
                                    pos2 = j;
                                }
                            }

                        }

                        if (current == '(') {
                            pos3 = pos2;
                            style = 'l';
                            for (int j = pos3 + 1; j < line.length(); j++) {
                                char current2 = line.charAt(j);
                                if (current2 == ')') {
                                    pos4 = j;
                                }
                            }
                            break;
                        }
                    }
                    if (pos2 != 0) {
                        if (line.charAt(0) != '*') {
                            toHTML.add(new Text(line.substring(0, pos), breakline));
                        }
                        for (int x = 0; x < line.length(); x++) {
                            lastIndex = x;
                        }
                        if (pos2 != lastIndex) {
                            breakline = false;
                        }
                        if (style == 'n') {
                            toHTML.add(new Text(line.substring(pos + 2, pos2 - 1), style, breakline));
                        }
                        if (style == 'i' || style == 'u') {
                            toHTML.add(new Text(line.substring(pos + 1, pos2), style, breakline));
                        }
                        if (style == 'l') {
                            toHTML.add(new Text(line.substring(pos + 1, pos2), breakline));
                            toHTML.add(new Link(line.substring(pos3 + 2, pos4)));
                            toHTML.add(new Text(line.substring(pos4 + 1), true));
                        } else if (pos2 != lastIndex) {
                            toHTML.add(new Text(line.substring(pos2 + 1), true));
                        }
                    } else {
                        toHTML.add(new Text(line.substring(0), breakline));
                    }
                }
            }
        }
        for (int i = 0; i < toHTML.size(); i++) {
            System.out.println(toHTML.get(i));
        }
    }

    public void gravaDados() throws IOException {
        FileWriter fr = new FileWriter("/home/gschroeder/Área de Trabalho/ NetBeansProjects/Project GB/ProjetoGrauA/MarkDown/src/LabII/TrabalhoGB/TrabalhoMarkdowntoHTML/ArquivoProcessadoHTML.html");
        PrintWriter out = new PrintWriter(fr);
        int numeracao = 0;

        FileWriter fw = new FileWriter("/home/gschroeder/Área de Trabalho/ NetBeansProjects/Project GB/ProjetoGrauA/MarkDown/src/LabII/TrabalhoGB/TrabalhoMarkdowntoHTML/ArquivoProcessadoHTML.html");
        out = new PrintWriter(fw);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Texto HTML</title>");
        out.println("<meta http-equiv=\"Content-Type" + "content=\"text/html; charset=UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        for (int i = 0; i < toHTML.size(); i++) {

            if (toHTML.get(i) instanceof Title) {
                String content = ((Title) toHTML.get(i)).getContent();
                int level = ((Title) toHTML.get(i)).getNivel();
                if (level == 1) {
                    out.println("<h1>" + content + "</h1>");
                } else {
                    out.println("<h2>" + content + "</h2>");
                }
            }

            if (toHTML.get(i) instanceof Text) {
                String content = ((Text) toHTML.get(i)).getContent();
                boolean breakLine = ((Text) toHTML.get(i)).isBreakLine();
                char style = ((Text) toHTML.get(i)).getStyle();
                if (breakLine == true) {
                    if (style == 'i') {
                        out.println("<i>" + content + "</i>" + "<br/>");
                    }

                    if (style == 'n') {
                        out.println("<b>" + content + "</b>" + "<br/>");
                    }

                    if (style == 'u') {
                        out.println("<u>" + content + "</u>" + "<br/>");
                    }

                    if (style == 'z') {
                        out.println(content + "<br/>");
                    }
                }

                if (breakLine == false) {
                    if (style == 'i') {
                        out.println("<i>" + content + "</i>");
                    }

                    if (style == 'n') {
                        out.println("<b>" + content + "</b>");
                    }

                    if (style == 'u') {
                        out.println("<u>" + content + "</u>");
                    }

                    if (style == 'z') {
                        out.println(content);
                    }
                }
            }
            if (toHTML.get(i) instanceof Item) {
                String content = ((Item) toHTML.get(i)).getContent();
                boolean breakLine = ((Item) toHTML.get(i)).isBreakline();
                if (toHTML.get(i - 1) instanceof Item) {
                    //nao faz nada, e apenas para colocar a tag
                } else {
                    out.println("<ul>");
                }

                if (breakLine == true && toHTML.get(i + 1) instanceof Item == true) {
                    out.println("<li>" + content + "</li>");
                } else if (toHTML.get(i + 1) instanceof Item == false) {
                    out.println("<li>" + content + "</li>" + "</ul>");
                }
            }
            if (toHTML.get(i) instanceof ItemN) {
                String content = ((ItemN) toHTML.get(i)).getContent();
                boolean breakLine = ((ItemN) toHTML.get(i)).getBreakLine();
                if (numeracao == 0) {
                    out.println("<ol>");
                    numeracao++;
                }

                if (breakLine == true) {
                    out.println("<li>" + content + "</li>");
                } else if (breakLine == false) {
                    out.print("<li>" + content);
                }

                if (toHTML.get(i + 1) instanceof ItemN == false) {
                    out.println("<li>" + content + "</li>" + "</ol>");
                }
            }
            if (toHTML.get(i) instanceof Link) {

                String cont = ((Text) toHTML.get(i - 1)).getContent();
                String cont1 = ((Link) toHTML.get(i)).getContent();
                out.println("<a href=\"" + cont1 + "" + "\"target=\"_blank\">" + cont + "</a>");
            }
        }
        out.println("</body>");
        out.println("</html>");
        fr.flush();
        out.flush();
        fr.close();
        out.close();
    }
}
