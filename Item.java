import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item {
    private int id;
    private String nome;
    private LocalDate dataentrega;
    private String prioridade;

    public Item(int id, String nome, LocalDate dataentrega, String prioridade) {
        this.id = id;
        this.nome = nome;
        this.dataentrega = dataentrega;
        this.prioridade = prioridade;
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public LocalDate getDataentrega() {
        return dataentrega;
    }
    public String getPrioridade() {
        return prioridade;
    }
    public int getValorprioridade() {
        switch (prioridade) {
            case "alta":
                return 1;
            case "média":
                return 2;

            default:
                return 3;
        }
    }
    @Override
    public String toString() {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "ID: " + id +
                "\nAtividade: " + nome +

                "\nEntrega: " + dataentrega.format(formato) +

                "\nPrioridade: " + prioridade;
    }
}
