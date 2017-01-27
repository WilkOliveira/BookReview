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
        Integer freqGood;
        Integer freqMedium;
        Integer freqBad;

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

                //Para cada linha do arquivo, inicia as categorias com 0
                mapPalavras.put("good", 0);
                mapPalavras.put("medium", 0);
                mapPalavras.put("bad", 0);

                /**
                 * Contador de frenquência de palavras
                 */
                while (m.find()) {
                    String token = m.group(); // Pega uma palavra

                    String cat = null; //categoria ainda não definida

                    if (token.equals("good") || token.equals("excellent") || token.equals("great")|| token.equals("acceptable") || token.equals("positive") || token.equals("satisfactory")) {
                        //Define a categoria como boa
                        cat = "good";
                    } else if (token.equals("medium") || token.equals("regular") || token.equals("ordinary") || token.equals("normal") || token.equals("intermediate") || token.equals("common") || token.equals("neutral")) {
                        //Define a categoria como média
                        cat = "medium";
                    } else if (token.equals("bad") || token.equals("terrible") || token.equals("awful") || token.equals("disagreeable") || token.equals("inferior") || token.equals("poor") || token.equals("unsatisfactory")) {
                        //Define a categoria como ruim
                        cat = "bad";
                    }
                    /**
                     * Armazena as palavras relacionadas a avaliação da
                     * categoria
                     */
                    if (cat != null) {
                        if (mapPalavras.containsKey(cat)) {
                            Integer value = mapPalavras.get(cat);
                            mapPalavras.put(cat, value + 1);
                        } else {
                            mapPalavras.put(cat, 1);
                        }
                    }
                }
                
                // Recupera o valor de cada categoria
                freqGood = mapPalavras.get("good");
                freqMedium = mapPalavras.get("medium");
                freqBad = mapPalavras.get("bad");

                System.out.println("- Avaliação do texto " + i + " -");
                
                /**
                 * Analisa a frequencia e imprimi a classificação
                 */
                if (freqGood > freqBad && freqGood > freqMedium) {
                    System.out.println("Este livro é classificado como bom");
                } else if (freqMedium > freqGood && freqMedium > freqBad) {
                    System.out.println("Este livro é classificado como médio");
                } else if (freqBad > freqGood && freqBad > freqMedium) {
                    System.out.println("Este livro é classificado como ruim");
                } else {
                    System.out.println("Não foi possível classificar");
                }

                for (Map.Entry<String, Integer> entry : mapPalavras.entrySet()) {
                    System.out.println(entry.getKey() + "\t frequência = " + entry.getValue());
                }
                System.out.println("");
                i = i + 1;

                //System.out.println("Quantidade de palavras: " +qtdPalavras.countTokens());
                //System.out.println(minusculo);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }
}