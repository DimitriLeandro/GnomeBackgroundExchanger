package classes;

import frames.MainFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dimi
 */
public class ManipuladorTxt {

    public void abrirTxt() {
        try {
            Runtime.getRuntime().exec("gedit /opt/Gnome_Background_Exchanger/db.txt", null);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> lerTxt() {
        //INICIANDO OS OBJETOS
        ArrayList<String> arrayImagens; //esse cara aqui vai ser o retorno
        File objFile;
        Scanner objSc;

        try {
            arrayImagens = new ArrayList();
            objFile = new File("/opt/Gnome_Background_Exchanger/db.txt");
            objSc = new Scanner(objFile);

            String linha;
            while (objSc.hasNextLine()) {
                linha = objSc.nextLine();
                if (linha != null && !"".equals(linha)) {
                    arrayImagens.add(linha);
                }
            }

            objSc = null;
            objFile = null;

            return arrayImagens;

        } catch (FileNotFoundException e) {
            System.out.println("Problema ao ler o txt: " + e);
        }

        return null;
    }
}
