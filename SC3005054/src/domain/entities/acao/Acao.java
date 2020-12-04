package domain.entities.acao;

import java.util.Objects;

public class Acao {
    private String nome;
    private String codigo;
    private Double valor;

    public Acao(String nome, String codigo, Double valor) {
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Acao)) return false;
        Acao acao = (Acao) o;
        return codigo.equals(acao.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Acao: " + nome  +
                " | Código: " + codigo +
                " | Valor: " + valor +
                "\n";
    }
}
