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
        ArrayList<String> arrayImagens;
        File objFile;
        
        try {
            arrayImagens = new ArrayList();
            objFile = new File("/opt/Gnome_Background_Exchanger/db.txt");
            Scanner objSc = new Scanner(objFile);

            arrayImagens.clear();

            while (objSc.hasNextLine()) {
                arrayImagens.add(objSc.nextLine());
            }

            objSc = null;
            objFile = null;
            
            return arrayImagens;
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        return null;
    }
}
