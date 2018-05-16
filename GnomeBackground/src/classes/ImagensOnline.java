package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author dimi
 */
public final class ImagensOnline {

    private MainExchanger objMainExchanger;
    private String tema;
    private URLConnection objURLSite;
    private boolean rodarThread;

    //NO CONSTRUTOR JÁ RECEBE O TEMA E O MAINEXCHANGER
    //vou precisar do MESMO OBJETO MainExchanger da classe MainFrame pq a thread de trocar o plano de fundo e o ArrayImagens vão estar rodando nesse objeto, por isso precisa ser o mesmo, não adianta criar outro objeto dessa classe.
    public ImagensOnline(MainExchanger objMainExchanger, String tema) throws IOException {
        //INICIANDO OS OBJETOS E VARIÁVEIS E APROVEITANDO PRA JÁ INICIAR A CONEXÃO COM O SITE DE PLANOS DE FUNDO
        this.objMainExchanger = objMainExchanger;
        this.tema = tema;
        criarConexao();
        criarPastaOnlineImages();

        //THREAD DE BAIXAR IMAGENS
        this.baixarImagens = () -> {
            BufferedReader objBR;
            //ANTES DE COMEÇAR A BAIXAR AS IMAGENS NOVAS, É BOM DAR UMA LIMPADA NO QUE TINHA ANTES
            deletarImagensAnteriores();
            this.objMainExchanger.limparArrayImagens();
            try {
                //CRIANDO UM OBJ BUFFEREDREADER QUE VAI CONSEGUIR LER TODO O HTML DA PÁGINA E OUTRO OBJETO URLCONNECTION SÓ QUE DESSA VEZ PRA URL DAS IMAGENS
                System.out.println("Iniciando a thread para baixar as imagens");
                URLConnection objURLImagem;
                objBR = new BufferedReader(new InputStreamReader(objURLSite.getInputStream(), Charset.forName("UTF-8")));
                String row;
                String caminhoImg;
                int contador = 0;

                while ((row = objBR.readLine()) != null && rodarThread == true) {
                    //SEPARANDO APENAS AS LINHAS QUE TEM <img srcset= ESSE .* É TIPO O LIKE DO SQL
                    if (row.matches(".*<img srcset=.*")) {
                        //PEGANDO A SUBSTRING PRA FICAR SÓ A URL DA IMAGEM
                        row = row.substring(row.indexOf("https://images.pexels.com/photos"), row.indexOf("?auto="));

                        try {
                            //CRIANDO CONEXÃO COM A URL DA IMAGEM
                            System.out.println("Começando a baixar " + row);
                            objURLImagem = new URL(row).openConnection();
                            objURLImagem.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

                            //SALVANDO A IMAGEM COM O NOME NO FORMATO yyyy-MM-dd'T'HH:mm:ss.SSSXXX QUE AI NÃO TEM ERRO
                            if (rodarThread == true) {
                                caminhoImg = "/opt/Gnome_Background_Exchanger/OnlineImages/" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(Calendar.getInstance().getTime()) + ".jpeg";
                                Files.copy(objURLImagem.getInputStream(), Paths.get(caminhoImg));
                                objMainExchanger.addTermoNoArrayImagens(caminhoImg); //agora preciso colocar essa imagem no arrayImagens pro objMainExchanger ter o que exibir
                                System.out.println("Imagem salva em: " + caminhoImg);
                                contador++;
                            }
                        } catch (IOException e) {
                            System.out.println("Erro ao tentar salvar a imagem no computador: " + e);
                        }
                    }
                }

                System.out.println("A thread baixarImagens parou. Foram baixadas " + contador + " imagens");

                //AGORA QUE TERMINOU DE BAIXAR, VOU DESTRUIR OS OBJETOS
                objURLSite = null;
                objURLImagem = null;
                objBR = null;
            } catch (IOException ex) {
                Logger.getLogger(ImagensOnline.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                objBR = null;
            }
        };
    }

    public void iniciarThreadBaixarImagens() {
        this.rodarThread = true;
        new Thread(baixarImagens).start();
    }

    public void pararThreadBaixarImagens() {
        System.out.println("Parando a thread de baixar imagens");
        this.rodarThread = false;
    }

    public void criarConexao() {
        try {
            objURLSite = new URL("https://www.pexels.com/search/" + tema).openConnection();
            objURLSite.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            objURLSite.connect();
            System.out.println("Conexão com https://www.pexels.com/search/" + tema + " criada com sucesso");
        } catch (MalformedURLException ex) {
            Logger.getLogger(ImagensOnline.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImagensOnline.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deletarImagensAnteriores() {
        try {
            System.out.println("Removendo imagens baixadas anteriormente.");
            FileUtils.cleanDirectory(new File("/opt/Gnome_Background_Exchanger/OnlineImages/"));
        } catch (IOException ex) {
            Logger.getLogger(ImagensOnline.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void criarPastaOnlineImages() {
        try {
            System.out.println("Verificando se a pasta OnlineImages existe");
            new File("/opt/Gnome_Background_Exchanger/OnlineImages").mkdirs(); //criando essa pasta caso não exista
        } catch (Exception e) {
            System.out.println("Erro ao tentar criar pasta OnlineImages: " + e);
        }
    }

    public final Runnable baixarImagens;
}
