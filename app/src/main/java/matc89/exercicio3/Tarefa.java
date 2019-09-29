package matc89.exercicio3;

public class Tarefa implements Comparable<Tarefa> {
    private String descricao;
    private int prioridade;

    public Tarefa(String descricao, int prioridade) {
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    @Override
    public int compareTo(Tarefa other) {
        return this.getPrioridade() - other.getPrioridade();
    }
}
