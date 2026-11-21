public class QuickSort {

    public static void ordenar(Item[] itens, int inicio, int fim, int criterio) {
        if (itens == null || inicio >= fim) {
            return;
        }
        quickSort(itens, inicio, fim, criterio);
    }

    private static void quickSort(Item[] vetor, int inicio, int fim, int criterio) {

        if (inicio < fim) {

            int posicaoPivo = particionar(vetor, inicio, fim, criterio);

            quickSort(vetor, inicio, posicaoPivo - 1, criterio);
            quickSort(vetor, posicaoPivo + 1, fim, criterio);
        }
    }

    private static int particionar(Item[] vetor, int inicio, int fim, int criterio) {

        Item pivo = vetor[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {

            if (comparar(vetor[j], pivo, criterio) >= 0) {
                i++;
                trocar(vetor, i, j);
            }
        }

        trocar(vetor, i + 1, fim);

        return i + 1;
    }

    private static void trocar(Item[] vetor, int i, int j) {

        Item auxiliar = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = auxiliar;
    }

    private static int comparar(Item a, Item b, int criterio) {

        int resultado;

        switch (criterio) {

            case 1:
                resultado = Integer.compare(
                        a.getValorprioridade(),
                        b.getValorprioridade()
                );
                break;

            case 2:
                resultado = a.getDataentrega().compareTo(b.getDataentrega());
                break;

            case 3:
                resultado = a.getNome().compareToIgnoreCase(b.getNome());
                break;

            default:
                throw new IllegalArgumentException("Criterio invalido!");
        }

        if (resultado == 0) {
            return Integer.compare(a.getId(), b.getId());
        }

        return resultado;
    }
}
