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
public class LeitoraReformulated {

    ArrayList toHTML = new ArrayList();

    public void fazLeitura() throws FileDontExistException, FileNotFoundException, IOException, StyleNotFoundException {

        int var = -1; //uma variavel para ser usada nas comparacoes
        int var2 = -1; // outra variavel para ser usada nas comparacoes
        int contador = 0;
        boolean breakline = false; //breakline se e e false nao tem se e true contem
        boolean end = false; //end 'e o fim?
        String montaFrase = null; // uma variavel para montar as frases para serem transformadas para html

        BufferedReader in;
        FileReader fe;
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { //selecao da arquivo
            fe = new FileReader(jfc.getSelectedFile());
            in = new BufferedReader(fe);
        } else {
            throw new FileDontExistException();
        }
        toHTML.add(new NoSintax("<html>\n"));
        toHTML.add(new NoSintax("<head>\n"));
        String line = null;

        while ((line = in.readLine()) != null) {
            if ("".equals(line)) {
                line = in.readLine();
            }

            if (line.indexOf(".") >= 0) {
                breakline = true;
            }

            if (line.indexOf("##") == 0) {
                toHTML.add(new Title(line.replace('#', ' '), 2));
            } else if (line.indexOf("#") == 0) {
                toHTML.add(new Title(line.replace('#', ' '), 1));
                if (contador == 0) {
                    toHTML.add(new NoSintax("</head>"));
                    contador++;
                }
            } //Verificação de PAGINA DA WEB
            else if (line.indexOf("[página web]") >= 0) {
                var = line.indexOf("(");
                var2 = line.indexOf(")");
                montaFrase = null;
                for (int i = var + 1; i < var2; i++) {
                    montaFrase += line.charAt(i);
                }
                toHTML.add(new Link(montaFrase.replaceFirst("null", " ")));
            } else if (line.startsWith("*")) {
                montaFrase = null;
                montaFrase = "<ul>" + (line.replace('*', ' ')) + "</ul>";
                if (line.endsWith(".")) {
                    toHTML.add(new Item(montaFrase, 1, breakline, end));
                } else {
                    toHTML.add(new Item(montaFrase, 2, breakline, end));
                }
            } //verificacao de text
            else if (line.indexOf("**") >= 0) {
                int cont = 0;
                if (line.startsWith("1")) {
                    toHTML.add(new Item("<i>" + line + "</i>", 1, breakline, end));
                } else {
                    for (int i = 0; i < line.length(); i++) {
                        if (('*') == (line.charAt(i))) {
                            cont++;
                        }
                    }
                }
                if (cont == 4 || cont == 8) { // ou igual a 8
                    if (breakline == true) {
                        String[] variavel = line.split(".");
                        for (int i = 0; i < variavel.length; i++) {
                            toHTML.add(new Text("<b>" + variavel[i] + "</b>", breakline));

                        }
                    } else {
                        toHTML.add(new Text("<b>" + line + "</b>", breakline)); // Se é negrito
                    }
                }

            } else if (line.indexOf("_") >= 0) {
                int cont = 0;
                if (line.startsWith("1")) {
                    toHTML.add(new Text("<i>" + line + "</i>", breakline));
                } else {
                    for (int i = 0; i < line.length(); i++) {
                        if (('_') == (line.charAt(i))) {
                            cont++;
                        }
                    }
                }
                if (cont == 2) {
                    if (breakline == true) {
                        String[] variavel = line.split(",");

                        for (int i = 0; i < variavel.length; i++) {
                            toHTML.add(new Text("<u>" + variavel[i] + "</u>", breakline));

                        }
                    } else {
                        toHTML.add(new Text("<u>" + line + "</u>", breakline)); // Se é italico
                    }
                }
            } else {  // Se não tem formatação
                if (line.startsWith("1")) {
                    montaFrase = null;
                    montaFrase = "<ol> /n" + line + "/n </ol>";
                    toHTML.add(new Item(montaFrase, 1, breakline, end));
                } else {
                    if (breakline == true) {
                        String[] variavel = line.split(",");

                        for (int i = 0; i < variavel.length; i++) {
                            toHTML.add(new Text(variavel[i], breakline));

                        }
                    } else {
                        toHTML.add(new Text(line, breakline));
                    }
                }
            }
        }
        toHTML.add(new NoSintax("</body>"));
        toHTML.add(new NoSintax("</html>"));
    }

    public void gravaDados() throws IOException {
        FileWriter fr = new FileWriter("/home/gschroeder/Área de Trabalho/ NetBeansProjects/Project GB/ProjetoGrauA/MarkDown/src/LabII/TrabalhoGB/TrabalhoMarkdowntoHTML/ArquivoProcessadoHTML.html");
        PrintWriter out = new PrintWriter(fr);
        for (int i = 0; i < toHTML.size(); i++) {
            out.print(toHTML.get(i).toString() + "\n");
            out.flush();
        }
    }
}
