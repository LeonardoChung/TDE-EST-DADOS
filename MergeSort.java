import java.util.Random;

public class MergeSort {
    static class Contadores {
        long iteracoes;
        long trocas;

        public Contadores() {
            this.iteracoes = 0;
            this.trocas = 0;
        }
    }

    public static void mergeSort(int[] array) {
        Contadores contadores = new Contadores();
        long tempoInicio = System.currentTimeMillis();
        int tamanho = obterTamanhoArray(array);

        // inicia o processo de ordenação
        ordenar(array, tamanho, contadores);

        long tempoFim = System.currentTimeMillis();
        long tempoExecucao = tempoFim - tempoInicio;

        System.out.println("tempo de execução: " + tempoExecucao);
        System.out.println("trocas: " + contadores.trocas);
        System.out.println("iterações: " + contadores.iteracoes);
    }

    // ordenar e dividir array
    public static void ordenar(int[] array, int tamanho, Contadores contadores) {
        if (tamanho > 1) {
            int meio = tamanho / 2; // divide array na metade
            int[] esquerda = new int[meio]; // parte da esquerda do array
            int[] direita = new int[tamanho - meio]; // parte da direita do array

            // copia os elementos para os arrays auxiliares
            copiarArray(array, esquerda, 0, meio, contadores);
            copiarArray(array, direita, meio, tamanho - meio, contadores);

            // ordena as duas partes
            ordenar(esquerda, meio, contadores);
            ordenar(direita, tamanho - meio, contadores);

            // mescla as duas partes já ordenadas
            mesclar(array, esquerda, direita, meio, tamanho - meio, contadores);
        }
    }

    // copiar partes do array
    public static void copiarArray(int[] origem, int[] destino, int inicio, int tamanho, Contadores contadores) {
        // copia os elementos do array origem para o destino
        for (int i = 0; i < tamanho; i++) {
            destino[i] = origem[inicio + i];
            contadores.iteracoes++;
        }
    }

    // mesclar dois arrays ordenados em um único array
    public static void mesclar(int[] array, int[] esquerda, int[] direita, int tamanhoEsquerda, int tamanhoDireita, Contadores contadores) {
        int i = 0, j = 0, k = 0;

        // compara elementos de ambos os arrays e insere o menor no array principal
        while (i < tamanhoEsquerda && j < tamanhoDireita) {
            contadores.iteracoes++;
            if (esquerda[i] <= direita[j]) {
                array[k++] = esquerda[i++];
            } else {
                array[k++] = direita[j++];
                contadores.trocas++;
            }
        }

        // se ainda tiver elementos da esquerda, copia
        while (i < tamanhoEsquerda) {
            contadores.iteracoes++;
            array[k++] = esquerda[i++];
        }

        // Copia os elementos restantes da subparte direita, se houver
        while (j < tamanhoDireita) {
            contadores.iteracoes++;
            array[k++] = direita[j++];
        }
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

        // gerar valor aleatório para o vetor
        for (int i = 0; i < tamanho; i++) {
            array[i] = rand.nextInt(tamanho);
        }

        return array;
    }

    public static void main(String[] args) {
        int[] tamanhos = {1000, 10000, 100000, 500000, 1000000}; // tamanhos dos vetores
        long seed = 12345;  // garantir replicabilidade

        for (int tamanho : tamanhos) {
            int[] array = gerarVetorAleatorio(tamanho, seed);  // itera sobre cada tamanho e cria um vetor aleatório com o método
            mergeSort(array); // passa o vetor para o método
            System.out.println();
        }
    }
}
