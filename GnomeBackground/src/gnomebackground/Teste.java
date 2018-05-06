package gnomebackground;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dimi
 */
public class Teste {

    public static void main(String[] args) {
        //HELLO FEDORA!
        System.out.println("Hello Fedora!");

        //vou fazer uma string com o comando de mudar o plano de fundo, aí no final é só ir colocando o caminho pro arquivo
        String comando = "gsettings set org.gnome.desktop.background picture-uri ";
        String fullComando = "";

        for (int i = 0; i < 100; i++) {
            try {
                if (i % 2 == 0) {
                    fullComando = comando + "file:///home/dimi/Pictures/a.jpg";
                } else {
                    fullComando = comando + "file:///home/dimi/Pictures/b.jpg";
                }
                Process process = Runtime.getRuntime().exec(fullComando, null);
                Thread.sleep(3000);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
