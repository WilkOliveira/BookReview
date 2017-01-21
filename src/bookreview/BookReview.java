package bookreview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Wilk Oliveira
 */
public class BookReview {

    public static void main(String[] args) {
    
        File amazonDataset = new File("C:\\Users\\Wilk\\Documents\\GitHub\\BookReview\\files\\BookReviewsFromAmazon.txt"); // Pega o arquivo .csv a ser trabalhado
        
        /**
         * Deixa todo o código dentro de try-catch para controlar erros nativos de arquivo não encontrado
         */      
        try{
            
            String linhasDoArquivo = new String(); // Cria String "linhasDoArquivo"
            
            Scanner leitor = new Scanner(amazonDataset); // Criar um leitor parar ler o arquivo recebido
                    
                    /**
                     * Enquanto o arquivo tiver uma nova linha, continua fazendo sua leitura linha por linha
                     */
                    while (leitor.hasNext()){
                        
                        linhasDoArquivo = leitor.nextLine(); // Variavel "linhasDoArquivo" recebe proxíma linha disponível no arquivo
                        StringTokenizer qtdPalavras=new StringTokenizer(linhasDoArquivo);
                        
                        System.out.println(qtdPalavras.countTokens());
                        //System.out.println(linhasDoArquivo);
                        
                    }
            
        } catch (FileNotFoundException ex) {
            
        }
        
    }
}