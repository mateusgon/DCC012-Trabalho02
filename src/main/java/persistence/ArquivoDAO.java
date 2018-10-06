package persistence;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import model.Resultado;

/**
 * Classe responsável por realzar a leitura do Entrada.txt e a escrita das métricas coletadas de execução
 */

public class ArquivoDAO { 

    private static Integer[] leituras;

    public static Integer[] getInstance() throws FileNotFoundException { // Realiza a leitura do Entrada.txt
        Scanner input = new Scanner(new FileReader("C:\\Users\\Mateu\\Documents\\NetBeansProjects\\DCC012---Trabalho01\\entrada.txt"));
        try {
            Integer valor;
            int contador = 0;
            while (input.hasNext()) {
                if (contador == 0) {
                    valor = input.nextInt();
                    leituras = new Integer[valor + 1];
                    leituras[contador] = valor;
                    contador++;
                } else {
                    leituras[contador] = input.nextInt();
                    contador++;
                }
            }
        } catch (Exception ex) {
            
        }
        input.close();
        return leituras;
    }

    public static Integer[] getInstance(Integer[] valores) throws FileNotFoundException { // Responsável por realizar a leitura da entrada que o usuário informou e substitui o Entrada.txt
        leituras = valores;
        return leituras;
    }

    public static void escrever(Integer codigo, String nome, Resultado[] resultado) { // Função responsável por escrever a saída das execuções dos cenário do item 1
        try {
            if (codigo == 0) {
                FileWriter fw = new FileWriter("C:\\Users\\Mateu\\Desktop\\" + nome + ".txt", false);
                BufferedWriter conexao = new BufferedWriter(fw);
                conexao.write("Resultado,Tempo gasto,Número de Trocas,Número de comparações");
                conexao.newLine();
                for (int i = 0; i < resultado.length; i++) {
                    conexao.write("Resultado " + i + "," + resultado[i].getTempoGasto() + "," + resultado[i].getNumTrocas() + "," + resultado[i].getNumComparacoes());
                    conexao.newLine();
                }
                conexao.close();
                fw = new FileWriter("C:\\Users\\Mateu\\Desktop\\Media" + nome + ".txt", false);
                conexao = new BufferedWriter(fw);
                conexao.write("Media,Tempo gasto,Número de Trocas,Número de comparações");
                conexao.newLine();
                Media m = new Media();
                Integer contador = 0;
                List<Resultado> resultados = m.media(resultado);
                for (Resultado resultado1 : resultados) {
                    conexao.write("Media tamanho " + contador + "," + resultado1.getTempoGasto() + "," + resultado1.getNumTrocas() + "," + resultado1.getNumComparacoes());
                    conexao.newLine();
                    contador++;
                }
                conexao.close();
            } else {
                FileWriter fw = new FileWriter("C:\\Users\\Mateu\\Desktop\\" + nome + ".txt", false);
                BufferedWriter conexao = new BufferedWriter(fw);
                conexao.write("Resultado,Tempo,Memória,Número de comparações");
                conexao.newLine();
                for (int i = 0; i < resultado.length; i++) {
                    conexao.write("Resultado " + i + "," + resultado[i].getTempoGasto() + "," + resultado[i].getMemoriaGasto() + "," + resultado[i].getNumComparacoes());
                    conexao.newLine();
                }
                conexao.close();
                fw = new FileWriter("C:\\Users\\Mateu\\Desktop\\Media" + nome + ".txt", false);
                conexao = new BufferedWriter(fw);
                conexao.write("Media,Tempo gasto,Gasto de memória,Número de comparações");
                conexao.newLine();
                Media m = new Media();
                Integer contador = 0;
                List<Resultado> resultados = m.media2(resultado);
                for (Resultado resultado1 : resultados) {
                    conexao.write("Media tamanho " + contador + "," + resultado1.getTempoGasto() + "," + resultado1.getMemoriaGasto() + "," + resultado1.getNumComparacoes());
                    conexao.newLine();
                    contador++;
                }
                conexao.close();
            }
        } catch (Exception e) {
            
        }
    }
}
