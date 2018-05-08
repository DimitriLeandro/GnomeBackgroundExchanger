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
 * @author dimitri leandro
 */
public class MainExchanger {

    private boolean rodar = true;
    private int delay;
    private ArrayList<String> arrayImagens;
    private final File objFile;
    private Scanner objSc;

    public MainExchanger() {
        this.trocarPlanoDeFundo = () -> {
            try {
                int i = 0;
                String comando;
                
                while (rodar == true) {
                    try {
                        comando = "gsettings set org.gnome.desktop.background picture-uri " + arrayImagens.get(i);
                        System.out.println("Exibindo " + arrayImagens.get(i));
                        i++;
                        if (i >= arrayImagens.size()) {
                            i = 0;
                        }
                        
                        Process process = Runtime.getRuntime().exec(comando, null);
                        Thread.sleep(delay);
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(MainExchanger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (Exception e) {
                System.out.println("Não foi possível começar a thread: " + e);
            }
        };
        arrayImagens = new ArrayList();
        objFile = new File("/opt/Gnome_Background_Exchanger/db.txt");
    }

    public void iniciarThread(String tempo) {
        lerTxt();
        rodar = true;
        delay = tempoToInt(tempo);

        System.out.println("Iniciando thread: \nrodar = " + rodar + "\ndelay: " + delay + "\n");

        new Thread(trocarPlanoDeFundo).start();
    }

    public void pararThread() {
        rodar = false;
        System.out.println("Thread parada: rodar = " + rodar + "\n");
    }

    public void abrirTxt() {
        try {
            Runtime.getRuntime().exec("gedit /opt/Gnome_Background_Exchanger/db.txt", null);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lerTxt() {
        try {
            System.out.println("Iniciando a leitura do db.txt" + "\n");

            objSc = new Scanner(objFile);

            arrayImagens.clear();

            while (objSc.hasNextLine()) {
                arrayImagens.add(objSc.nextLine());
            }

            objSc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private int tempoToInt(String tempo) {
        switch (tempo) {
            case "Trocar a cada 1 segundo":
                return (1000);
            case "Trocar a cada 5 segundos":
                return (1000 * 5);
            case "Trocar a cada 10 segundos":
                return (1000 * 10);
            case "Trocar a cada 30 segundos":
                return (1000 * 30);
            case "Trocar a cada 1 minuto":
                return (1000 * 60);
            case "Trocar a cada 5 minutos":
                return (1000 * 60 * 5);
            case "Trocar a cada 10 minutos":
                return (1000 * 60 * 10);
            case "Trocar a cada 1 hora":
                return (1000 * 60 * 60);
            case "Trocar a cada 5 horas":
                return (1000 * 60 * 60 * 5);
            case "Trocar a cada 10 horas":
                return (1000 * 60 * 60 * 10);
        }

        return 0;
    }

    private final Runnable trocarPlanoDeFundo;
}
