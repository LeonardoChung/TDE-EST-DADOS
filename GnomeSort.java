import java.util.Random;

public class GnomeSort {
    public static void gnomeSort(int[] array) {
        int i = 0;
        long iteracoes = 0;
        long trocas = 0;
        long tempoInicio = System.currentTimeMillis();
        int tamanho = obterTamanhoArray(array);

        while (i < tamanho) {
            iteracoes++;
            if (i == 0 || array[i] >= array[i - 1]) { //se for maior que a posicao anterior, vai pra proxima posicao
                i++;
            } else {
                int temp = array[i];
                array[i] = array[i - 1]; // atual vira anterior
                array[i - 1] = temp; // e o anterior vira o proximo
                trocas++;
                i--; // voltando para conferir se esta de acordo
            }
        }

        long tempoFim = System.currentTimeMillis();
        long tempoExecucao = tempoFim - tempoInicio;
;
        System.out.println("tempo de execução: " + tempoExecucao);
        System.out.println("yrocas: " + trocas);
        System.out.println("iterações: " + iteracoes);
    }

    public static int obterTamanhoArray(int[] array) {
        int tamanho = 0;
        for (int elemento : array) {
            tamanho++;
        }
        return tamanho;
    }

    public static int[] gerarVetorAleatorio(int tamanho, long seed) {
        Random rand = new Random(seed);
        int[] array = new int[tamanho];

        // gerar valor aleatorio para o vetor
        for (int i = 0; i < tamanho; i++) {
            array[i] = rand.nextInt(tamanho);
        }

        return array;
    }

    public static void main(String[] args) {
        int[] tamanhos = {1000, 10000, 100000, 500000, 1000000}; // tamanho dos vertores
        long seed = 12345;  // garantir replicabilidade

        for (int tamanho : tamanhos) {
            int[] array = gerarVetorAleatorio(tamanho, seed); //itera sobre cada tamanho e cria um vetor aleatório com o método
            gnomeSort(array); // passa o vetor para o método
            System.out.println();
        }
    }
}
