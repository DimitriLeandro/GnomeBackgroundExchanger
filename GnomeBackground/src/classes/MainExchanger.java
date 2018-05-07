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
    private int delay;

    public void iniciarThread(ArrayList<String> imagens, String tempo) {
        rodar = true;
        vetorImagens = imagens;
        contImagens = imagens.size();
        delay = tempoToInt(tempo);

        new Thread(trocarPlanoDeFundo).start();
    }

    public void pararThread() {
        rodar = false;
    }
    
    private int tempoToInt(String tempo){
        switch(tempo){
            case "Trocar a cada 1 minuto": return (1000 * 60);
            case "Trocar a cada 5 minutos": return (1000 * 60 * 5);
            case "Trocar a cada 10 minutos": return (1000 * 60 * 10);
            case "Trocar a cada 1 hora": return (1000 * 60 * 60);
            case "Trocar a cada 5 horas": return (1000 * 60 * 60 * 5);
            case "Trocar a cada 10 horas": return (1000 * 60 * 60 * 10);
        }
        
        return 0;
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
                        Thread.sleep(delay);
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
