package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dimi
 */
public class MainExchanger {

    private boolean rodar = true;
    private ArrayList<String> vetorImagens;
    private int contImagens;

    public void iniciarThread(ArrayList<String> imagens) {
        rodar = true;
        vetorImagens = imagens;
        contImagens = imagens.size();

        new Thread(trocarPlanoDeFundo).start();
    }

    public void pararThread() {
        rodar = false;
    }

    private Runnable trocarPlanoDeFundo = new Runnable() {
        @Override
        public void run() {
            try {
                //vou fazer uma string com o comando de mudar o plano de fundo, aí no final é só ir colocando o caminho pro arquivo
                String comando = "gsettings set org.gnome.desktop.background picture-uri ";
                String fullComando = "";
                int i = 0;

                while (rodar == true) {
                    try {
                        fullComando = comando + vetorImagens.get(i);
                        i++;
                        if (i >= contImagens) {
                            i = 0;
                        }

                        Process process = Runtime.getRuntime().exec(fullComando, null);
                        Thread.sleep(1500);
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(MainExchanger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (Exception e) {
                System.out.println("Não foi possível começar a thread: " + e);
            }
        }
    };
}
