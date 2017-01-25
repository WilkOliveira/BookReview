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

        File amazonDataset = new File("C:\\Users\\Wilk Oliveira\\Documents\\GitHub\\BookReview\\files\\BookReviewsFromAmazon.txt"); // Pega o arquivo .csv a ser trabalhado
        String linhasDoArquivo;
        Map<String, Integer> mapPalavras;
        int i = 1;
        Integer freqGood = 0;
        Integer freqMedium = 0;
        Integer freqBad = 0;

        /**
         * Deixa todo o código dentro de try-catch para controlar erros nativos
         * de arquivo não encontrado
         */
        try {

            linhasDoArquivo = new String(); // Armazenar parcialmente cada linha do arquivo
            Scanner leitor = new Scanner(amazonDataset); // Faz a leitura do arquivo recebido
            mapPalavras = new HashMap<String, Integer>(); // Para armazenar as palavras

            /**
             * Enquanto o arquivo tiver uma nova linha, continua fazendo sua
             * leitura linha por linha
             */
            while (leitor.hasNext()) {

                linhasDoArquivo = leitor.nextLine(); // Variavel "linhasDoArquivo" recebe proxíma linha disponível no arquivo
                String minusculo = linhasDoArquivo.toLowerCase(); // Converte para minusculo para evitar problemas futuros na leitura
                StringTokenizer qtdPalavras = new StringTokenizer(minusculo); // Soma todas apalavras de cada linha

                /**
                 * Usa expressão regular pegar caracteres especiais Faz sentido
                 * quando pensamos em textos em português
                 */
                Pattern p = Pattern.compile("(\\d+)|([a-záéíóúçãõôê]+)");
                Matcher m = p.matcher(minusculo);

                /**
                 * Contador de frenquência de palavras
                 */
                while (m.find()) {
                    String token = m.group(); // Pega uma palavra
                    freqGood = mapPalavras.get(token); // Identifica se a palavra já foi mapeada
                    freqMedium = mapPalavras.get(token); // Identifica se a palavra já foi mapeada
                    freqBad = mapPalavras.get(token); // Identifica se a palavra já foi mapeada

                    /**
                     * Armazena as palavras relacionadas a avaliação boa
                     */
                    if (freqGood != null) {
                        if (token.equals("good") || token.equals("excellent") || token.equals("great")) {
                            mapPalavras.put("good", freqGood + 1);
                        }
                    } else {
                        if (token.equals("good") || token.equals("excellent") || token.equals("great")) {
                            mapPalavras.put("good", 1);
                        }
                    }
                    /**
                     * Armazena as palavras relacionadas a avaliação média
                     */
                    if (freqMedium != null) {
                        if (token.equals("medium") || token.equals("regular") || token.equals("ordinary")) {
                            mapPalavras.put("medium", freqMedium + 1);
                        }
                    } else {
                        if (token.equals("medium") || token.equals("regular") || token.equals("ordinary")) {
                            mapPalavras.put("medium", 1);
                        }
                    }
                    /**
                     * Armazena as palavras relacionadas a avaliação ruim
                     */
                    if (freqBad != null) {
                        if (token.equals("bad") || token.equals("terrible") || token.equals("awful")) {
                            mapPalavras.put("bad", freqBad + 1);
                        }
                    } else {
                        if (token.equals("bad") || token.equals("terrible") || token.equals("awful")) {
                            mapPalavras.put("bad", 1);
                        }
                    }

                    /**
                     * Analisa a frequencia e imprimi a classificação
                     */
                    if (freqBad != null || freqGood != null || freqMedium != null) {
                        
                        if (freqGood > freqBad && freqGood > freqMedium) {
                            System.out.println("Este livro é classificado como bom");
                        } else if (freqMedium > freqGood && freqMedium > freqBad) {
                            System.out.println("Este livro é classificado como médio");
                        } else if (freqBad > freqGood && freqBad > freqMedium) {
                            System.out.println("Este livro é classificado como ruim");
                        } else {
                            System.out.println("Não foi possível classificar");
                        }

                    }

                }
                
                System.out.println("- Avaliação do texto " + i + " -");

                for (Map.Entry<String, Integer> entry : mapPalavras.entrySet()) {
                    System.out.println(entry.getKey() + "\t frequência = " + entry.getValue());
                }

                i = i + 1;

                //System.out.println("Quantidade de palavras: " +qtdPalavras.countTokens());
                //System.out.println(minusculo);
            }

            
        } catch (FileNotFoundException ex) {

        }

    }
}
