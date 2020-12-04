package domain.entities.notificarClientes;

import domain.entities.acao.Acao;

public interface Listner {

    void atualizarValorDasAcoesDasCarteiras(Acao acao);

    void removerAcoesDasCarteiras(Acao acao);
}
