import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class Main {

    static Scanner entrada = new Scanner(System.in);
    static Pilhaestudos pilha = new Pilhaestudos();

    static DateTimeFormatter formato = DateTimeFormatter
            .ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\nTO DO LIST");
            System.out.println("1 - Cadastrar atividade");
            System.out.println("2 - Concluir atividade");
            System.out.println("3 - Visualizar pilha de estudos");
            System.out.println("4 - Consultar proxima atividade");
            System.out.println("5 - Ordenar por prioridade");
            System.out.println("6 - Ordenar por data de entrega");
            System.out.println("7 - Ordenar por nome");
            System.out.println("0 - Sair");
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    cadastrar();
                    break;

                case 2:
                    concluir();
                    break;

                case 3:
                    pilha.listar();
                    break;

                case 4:
                    consultarTopo();
                    break;

                case 5:
                    ordenar(1);
                    break;

                case 6:
                    ordenar(2);
                    break;

                case 7:
                    ordenar(3);
                    break;

                case 0:
                    System.out.println("SAINDO");
                    break;

                default:
                    System.out.println("Opcao invalida");
            }

        } while (opcao != 0);

        entrada.close();
    }

    public static int lerInteiro(String mensagem) {

        while (true) {
            System.out.print(mensagem);

            try {
                return Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido!");
            }
        }
    }

    public static void cadastrar() {

        if (pilha.estaCheia()) {
            System.out.println("A pilha esta cheia!");
            return;
        }

        System.out.println("\nCADASTRAR ITEM");

        int id;
        do {
            id = lerInteiro("ID: ");

            if (id <= 0) {
                System.out.println("O ID deve ser positivo!");
            } else if (pilha.existeId(id)) {
                System.out.println("Esse ID ja esta cadastrado!");
            }

        } while (id <= 0 || pilha.existeId(id));
        String nome;

        do {
            System.out.print("Nome da atividade: ");
            nome = entrada.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("O nome nao pode estar vazio!");
            }

        } while (nome.isEmpty());

        LocalDate data = null;
        while (data == null) {
            System.out.print("Data de entrega (dd/MM/aaaa): ");

            try {
                data = LocalDate.parse(
                        entrada.nextLine().trim(), formato
                );
            } catch (DateTimeParseException e) {
                System.out.println("Data invalida!");
            }
        }
        String prioridade = "";
        while (prioridade.isEmpty()) {

            System.out.println("\nPrioridade:");
            System.out.println("1 - Alta");
            System.out.println("2 - Media");
            System.out.println("3 - Baixa");

            int escolha = lerInteiro("Escolha: ");

            switch (escolha) {
                case 1:
                    prioridade = "Alta";
                    break;
                case 2:
                    prioridade = "Media";
                    break;
                case 3:
                    prioridade = "Baixa";
                    break;

                default:
                    System.out.println("Prioridade invalida!");
            }
        }

        Item novo = new Item(id, nome, data, prioridade);
        pilha.push(novo);
    }

    public static void concluir() {

        System.out.println("\nCONCLUIR ATIVIDADE");

        Item concluida = pilha.pop();

        if (concluida != null) {
            System.out.println("Atividade concluida:");
            System.out.println(concluida);
        }
    }

    public static void consultarTopo() {

        System.out.println("\nPROXIMA ATIVIDADA");

        Item atividade = pilha.top();
        if (atividade != null) {
            System.out.println(atividade);
        }
    }

    public static void ordenar(int criterio) {

        if (pilha.estaVazia()) {
            System.out.println("Nenhuma atividade cadastrada!");
            return;
        }

        pilha.ordenar(criterio);

        switch (criterio) {
            case 1:
                System.out.println("\nOrdenacao por prioridade:");
                break;

            case 2:
                System.out.println("\nOrdenacao por data de entrega:");
                break;

            case 3:
                System.out.println("\nOrdenacao por nome:");
                break;
        }

        pilha.listar();
    }
}
