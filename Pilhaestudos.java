public class Pilhaestudos {

    private Item[] itens;
    private int topo;

    public Pilhaestudos() {
        itens = new Item[100];
        topo = -1;
    }

    public boolean estaVazia() {
        return topo == -1;
    }

    public boolean estaCheia() {
        return topo == itens.length - 1;
    }

    public void push(Item item) {

        if (estaCheia()) {
            System.out.println("A pilha esta cheia!");
            return;
        }

        topo++;
        itens[topo] = item;

        System.out.println("Atividade cadastrada com sucesso!");
    }

    public Item pop() {

        if (estaVazia()) {
            System.out.println("A pilha esta vazia!");
            return null;
        }

        Item removido = itens[topo];

        itens[topo] = null;
        topo--;

        return removido;
    }

    public Item top() {

        if (estaVazia()) {
            System.out.println("A pilha esta vazia!");
            return null;
        }

        return itens[topo];
    }

    public void listar() {

        if (estaVazia()) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }

        System.out.println("\nPILHA DE TAREFAS");

        for (int i = topo; i >= 0; i--) {
            System.out.println(itens[i]);
        }
    }

    public boolean existeId(int id) {

        for (int i = 0; i <= topo; i++) {

            if (itens[i].getId() == id) {
                return true;
            }
        }

        return false;
    }

    public void ordenar(int criterio) {

        if (estaVazia()) {
            System.out.println("Nenhuma atividade cadastrada!");
            return;
        }

        QuickSort.ordenar(itens, 0, topo, criterio);

        System.out.println("Pilha organizada com sucesso!");
    }
}
