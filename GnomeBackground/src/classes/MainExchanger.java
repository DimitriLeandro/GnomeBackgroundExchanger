package classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dimitri leandro
 */
public class MainExchanger {

    //DECLARANDO
    private boolean rodar; //depois de acionada, a thread de trocar o plano de fundo roda só se isso aqui for true, aí da parar a thread fazendo isso ser false
    private int delay;
    private String comando;
    private ArrayList<String> arrayImagens;

    public MainExchanger() {
        arrayImagens = new ArrayList();

        //THREAD DE TROCAR O PLANO DE FUNDO
        this.trocarPlanoDeFundo = () -> {
            try {
                System.out.println("Iniciando thread para trocar o plano de fundo: rodar = " + rodar + " - delay: " + delay + " - arrayImagens.size() = " + arrayImagens.size());
                int i = 0;
                String fullCommand;
                File objFile; //esse objeto vai ser pra verificar se a imagem existe no pc

                while (rodar == true) {
                    if (arrayImagens.size() > 0) {
                        //A PRIMEIRA COISA É VERIFICAR SE A IMAGEM EXISTE MESMO
                        objFile = new File(arrayImagens.get(i));

                        if (objFile.exists() && !objFile.isDirectory()) {
                            //AI SE EXISTIR, EXIBE
                            try {
                                fullCommand = comando + arrayImagens.get(i);
                                System.out.println("Exibindo " + arrayImagens.get(i));
                                Runtime.getRuntime().exec(fullCommand, null);
                                Thread.sleep(delay);
                            } catch (IOException | InterruptedException ex) {
                                Logger.getLogger(MainExchanger.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            System.out.println("O arquivo " + arrayImagens.get(i) + " não está sendo exibido pois não existe");
                        }
                        
                        //PASSANDO PRA PRÓXIMA IMAGEM
                        i++;
                        if (i >= arrayImagens.size()) {
                            i = 0;
                        }
                    } else {
                        Thread.sleep(1000);
                    }
                }

                //DESTRUINDO OBJETOS
                objFile = null;

            } catch (Exception e) {
                System.out.println("\nNão foi possível começar a thread para trocar o plano de fundo: " + e);
            }
        };
    }

    public void iniciarThread(String tempo, String systemInterface) {
        rodar = true;
        delay = tempoToInt(tempo);
        comando = interfaceToCommand(systemInterface);

        new Thread(trocarPlanoDeFundo).start();
    }

    public void pararThread() {
        rodar = false;
        System.out.println("Thread para trocar o plano de fundo parada");
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

    private String interfaceToCommand(String systemInterface) {
        switch (systemInterface) {
            case "Gnome":
                return "gsettings set org.gnome.desktop.background picture-uri file://";
            case "Mate":
                return "gsettings set org.mate.background picture-filename ";
            case "Cinnamon":
                return "gsettings set org.cinnamon.desktop.background picture-uri file://";
        }

        return "";
    }

    public void setArrayImagens(ArrayList<String> arrayImagens) {
        this.arrayImagens = arrayImagens;
    }

    public void addTermoNoArrayImagens(String caminhoImg) {
        arrayImagens.add(caminhoImg);
        System.out.println("\"" + caminhoImg + "\" adicionado ao arrayImagens na classe MainExchanger");
    }

    public void limparArrayImagens() {
        this.arrayImagens.clear();
        System.out.println("Limpando o arrayImagens");
    }

    private final Runnable trocarPlanoDeFundo;
}
