package domain.entities.aplicacao;

import domain.entities.acao.Acao;

import java.util.Objects;

public class Aplicacao {
    private Double quantidadeDaAcao;
    private Acao acao;

    public Aplicacao(Double quantidadeDaAcao, Acao acao) {
        this.quantidadeDaAcao = quantidadeDaAcao;
        this.acao = acao;
    }

    public Double getQuantidadeDaAcao() {
        return quantidadeDaAcao;
    }

    public void setQuantidadeDaAcao(Double quantidadeDaAcao) {
        this.quantidadeDaAcao = quantidadeDaAcao;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aplicacao)) return false;
        Aplicacao aplicacao = (Aplicacao) o;
        return acao.equals(aplicacao.acao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acao);
    }

    @Override
    public String toString() {
        return "Ação: "+ acao.getNome() +
                " | Quantidade: " + quantidadeDaAcao;
    }
}
