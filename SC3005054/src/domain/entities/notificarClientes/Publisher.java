package domain.entities.notificarClientes;

import domain.entities.acao.Acao;

public interface Publisher {
    void subscribe(String codigoAcao, Listner listener);

    void unsubscribe(String codigoAcao, Listner listener);

    void notificarClientes(Acao acao, EventType eventType);

    void addAcaoListner(String codigoAcao);

    void removeAcaoListner(String codigoAcao);
}
