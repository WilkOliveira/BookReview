package bookreview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Wilk Oliveira
 */
public class BookReview {

    public static void main(String[] args) {
    
        File amazonDataset = new File("C:\\Users\\Wilk\\Documents\\GitHub\\BookReview\\files\\BookReviewsFromAmazon.txt"); // Pega o arquivo .csv a ser trabalhado
        String linhasDoArquivo;
        Map<String,Integer> mapPalavras;
        int i = 1;
        /**
         * Deixa todo o código dentro de try-catch para controlar erros nativos de arquivo não encontrado
         */      
        try{
            
            linhasDoArquivo = new String(); // Armazenar parcialmente cada linha do arquivo
            Scanner leitor = new Scanner(amazonDataset); // Faz a leitura do arquivo recebido
            mapPalavras = new HashMap<String,Integer>(); // Para armazenar as palavras
                    
                    /**
                     * Enquanto o arquivo tiver uma nova linha, continua fazendo sua leitura linha por linha
                     */
                    while (leitor.hasNext()){
                        
                        linhasDoArquivo = leitor.nextLine(); // Variavel "linhasDoArquivo" recebe proxíma linha disponível no arquivo
                        String minusculo = linhasDoArquivo.toLowerCase(); // Converte para minusculo para evitar problemas futuros na leitura
                        StringTokenizer qtdPalavras=new StringTokenizer(minusculo); // Soma todas apalavras de cada linha
                        
                        /**
                         * Usa expressão regular pegar caracteres especiais
                         * Faz sentido quando pensamos em textos em português
                         */
                        Pattern p = Pattern.compile("(\\d+)|([a-záéíóúçãõôê]+)");
                        Matcher m = p.matcher(minusculo);
                        
                        /**
                         * Contador de frenquência de palavras
                         */
                        while(m.find())
                        {
                            String token = m.group(); // Pega uma palavra
                            Integer freq = mapPalavras.get(token); // Identifica se a palavra já foi mapeada
				
                                /**
                                 * Se palavra existir, atualiza a frequencia, 
                                 * Senão, cria um novo id e insere a palavra nesse id com frequência = 1
                                 * - PRECISO PEGAR SOMENTE PALAVRAS ESPECIFICAS, NO CASO, ENCONTRAR A PALAVRA "good" OU "medium" OU "bad" e incrementar cada uma delas em uma variavel especifica, por fim, comparar e ver qual qual é a maior
                                 * - Se a maior for a variavel que armazena a frequência de "good", dizer que a qualidade do livro é boa
                                 */
				if (freq != null) {
					mapPalavras.put(token, freq+1);
				}
				else {
					mapPalavras.put(token,1);
				}
                        }
                        
                        System.out.println("- Avaliação do texto " +i +" -");
                            
                        for (Map.Entry<String, Integer> entry : mapPalavras.entrySet())
                            {
                                System.out.println(entry.getKey() + "\t frequência = " + entry.getValue());
                            }
                        
                        i = i +1;

                        //System.out.println("Quantidade de palavras: " +qtdPalavras.countTokens());
                        //System.out.println(minusculo);
                        
                    }
            
        } catch (FileNotFoundException ex) {
            
        }
        
    }
}