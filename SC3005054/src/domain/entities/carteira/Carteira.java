package domain.entities.carteira;

import domain.entities.aplicacao.Aplicacao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Carteira {
    private String cpf;
    private List<Aplicacao> aplicacoes;

    public Carteira(String cpf) {
        this.cpf = cpf;
        this.aplicacoes = new ArrayList<>();
    }

    public Iterator<Aplicacao> iterator(){
        return aplicacoes.iterator();
    }

    public Double valorCarteira(){
        double valorTotal = 0;
        for (Aplicacao aplicacao : aplicacoes) {
            double quantidadeAcao = aplicacao.getQuantidadeDaAcao();
            Double valorAcao = aplicacao.getAcao().getValor();
            valorTotal += quantidadeAcao * valorAcao;
        }
        return valorTotal;
    }

    public List<Aplicacao> listarAplicao(){
        return aplicacoes;
    }

    public void addAplicacao(Aplicacao aplicacao){
        if (aplicacao != null) {
            this.aplicacoes.add(aplicacao);
        }
    }

    public void removeAplicacao(List<Aplicacao> aplicacoes){
        this.aplicacoes.removeAll(aplicacoes);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Carteira: " + cpf
                + " | Aplicações: " + aplicacoes
                + " | Valor da Carteira: " + valorCarteira()
                + "\n";
    }
}
