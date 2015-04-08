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
 * @author Gustavo Lazarotto Schroeder
 */
public class Leitora {

    ArrayList<Sintaxe> markdown = new ArrayList();

    public void fazLeitura() throws FileDontExistException, FileNotFoundException, IOException, StyleNotFoundException {

        int var = -1;
        int var2 = -1;
        boolean breakline = false;
        boolean end = false;
        String montaFrase = null;

        BufferedReader in;
        FileReader fe;
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            fe = new FileReader(jfc.getSelectedFile());
            in = new BufferedReader(fe);
        } else {
            throw new FileDontExistException();
        }
        String line = null;

        while ((line = in.readLine()) != null) {

            if ("".equals(line)) {
                line = in.readLine();

            }
            if (line.indexOf(",") >= 0) {
                breakline = true;
            }
            /*montaFrase = in.readLine();
             if(montaFrase.startsWith("1")){
             end = true;
             }
             else
             end = false;
             */
            if (line.indexOf("##") == 0) {
                markdown.add(new Title(line, 2));
            } else if (line.indexOf("#") == 0) {
                markdown.add(new Title(line, 1));
            } //Verificação de PAGINA DA WEB
            else if (line.indexOf("[página web]") >= 0) {
                var = line.indexOf("(");
                var2 = line.indexOf(")");

                for (int i = var; i < var2; i++) {
                    montaFrase += line.charAt(i);
                }
                markdown.add(new Link(montaFrase));
                if (line.indexOf(".") >= 0) {
                    markdown.add(new Text(("."), breakline));
                }
            } else if (line.indexOf("**") >= 0) {
                int cont = 0;
                if (line.startsWith("1")) {
                    markdown.add(new Text("Style: [ITALICO] " + line, breakline));
                } else {
                    for (int i = 0; i < line.length(); i++) {
                        if (('*') == (line.charAt(i))) {
                            cont++;
                        }
                    }
                }
                if (cont == 4 || cont == 8) {
                    if (breakline == true) {
                        String[] variavel = line.split(",");
                        for (int i = 0; i < variavel.length; i++) {
                            markdown.add(new Text("Style: [BOLD] " + variavel[i], breakline));

                        }
                    } 
                    
                    else {
                        markdown.add(new Text("Style: [BOLD] " + line, breakline)); // Se é Itálico
                        continue;
                    }
                }
                else{
                        throw new StyleNotFoundException();
                    }
        
            } else if (line.startsWith("*")) {

                if (line.endsWith(".")) {
                    markdown.add(new Item(line, 1, breakline, end));
                } else {
                    markdown.add(new Item(line, 2, breakline, end));
                }
            } // começa a verificação de TEXT    
            else if (line.indexOf("*") >= 0) {
                int cont = 0;

                if (line.startsWith("1")) {
                    markdown.add(new Text("Style: [ITALICO] " + line, breakline));
                } else {

                    for (int i = 0; i < line.length(); i++) {
                        if (('*') == (line.charAt(i))) {
                            cont++;
                        }
                    }


                }
                if (cont == 2 && cont == 4 && cont == 6) {
                    String[] variavel = line.split(",");
                    if (breakline = true) {
                        for (int i = 0; i < variavel.length; i++) {
                            markdown.add(new Text("Style: [ITALICO] " + variavel[i], breakline));

                        }
                    } else {
                        markdown.add(new Text("Style: [ITALICO] " + line, breakline)); // Se é Itálico

                    }
                }
                else
                    throw new StyleNotFoundException();

            } else if (line.indexOf("_") >= 0) {
                int cont = 0;
                if (line.startsWith("1")) {
                    markdown.add(new Text("Style: [UNDERLINE] " + line, breakline));
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
                            markdown.add(new Text("Style: [UNDERLINE]" + variavel[i], breakline));

                        }
                    } else {
                        markdown.add(new Text("Style: [UNDERLINE] " + line, breakline)); // Se é Itálico
                        continue;
                    }
                }
                else
                throw new StyleNotFoundException();


            } else {  // Se não tem formatação
                if (line.startsWith("1")) {
                    markdown.add(new Item(line, 1, breakline, end));
                } else {
                    if (breakline == true) {
                        String[] variavel = line.split(",");

                        for (int i = 0; i < variavel.length; i++) {
                            markdown.add(new Text(variavel[i], breakline));

                        }
                    } else {
                        markdown.add(new Text(line, breakline));
                    }
                }
            }
            for (int i = 0;
                    i < markdown.size();
                    i++) {
                System.out.println(markdown.get(i).toString());
            }
        }
    }

    public void gravaDados() throws IOException {
        FileWriter fr = new FileWriter("E:\\ProjetoGrauA LAST EDITION BETA\\ProjetoGrauA\\MarkDown\\src\\LabII\\TrabalhoGA\\TrabalhoMarkdown\\ArquivoProcessado");
        PrintWriter out = new PrintWriter(fr);
        for (int i = 0; i < markdown.size(); i++) {
            out.print(markdown.get(i).toString() + "\n");
            out.flush();
        }

    }
}
