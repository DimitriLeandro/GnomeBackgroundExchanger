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

    public ImagensOnline(MainExchanger objMainExchanger, String tema) throws IOException {
        this.objMainExchanger = objMainExchanger;
        this.tema = tema;
        criarConexao();

        this.baixarImagens = () -> {
            BufferedReader objBR = null;
            deletarImagensAnteriores();
            try {
                System.out.println("\nIniciando a thread para baixar as imagens");
                URLConnection objURLImagem;
                objBR = new BufferedReader(new InputStreamReader(objURLSite.getInputStream(), Charset.forName("UTF-8")));
                String row;
                String caminhoImg;
                while ((row = objBR.readLine()) != null) {
                    if (row.matches(".*<img srcset=.*")) {
                        row = row.substring(row.indexOf("https://images.pexels.com/photos"), row.indexOf("?auto="));
                        try {
                            System.out.println("\nComeçando a baixar " + row);
                            objURLImagem = new URL(row).openConnection();
                            objURLImagem.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

                            caminhoImg = "/opt/Gnome_Background_Exchanger/OnlineImages/" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(Calendar.getInstance().getTime()) + ".jpeg";
                            Files.copy(objURLImagem.getInputStream(), Paths.get(caminhoImg));
                            objMainExchanger.addTermoNoArrayImagens(caminhoImg);
                            System.out.println("Imagem salva em: " + caminhoImg);
                        } catch (IOException e) {
                            System.out.println("Erro ao tentar salvar a imagem no computador: " + e);
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ImagensOnline.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                objBR = null;
            }
        };
    }

    public void criarConexao() {
        try {
            objURLSite = new URL("https://www.pexels.com/search/" + tema).openConnection();
            objURLSite.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            objURLSite.connect();
            System.out.println("\nConexão com https://www.pexels.com/search/" + tema + " criada com sucesso");
        } catch (MalformedURLException ex) {
            Logger.getLogger(ImagensOnline.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImagensOnline.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void deletarImagensAnteriores(){
        try { 
            System.out.println("\nRemovendo imagens baixadas anteriormente.");
            FileUtils.cleanDirectory(new File("/opt/Gnome_Background_Exchanger/OnlineImages/"));
        } catch (IOException ex) {
            Logger.getLogger(ImagensOnline.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final Runnable baixarImagens;
}
