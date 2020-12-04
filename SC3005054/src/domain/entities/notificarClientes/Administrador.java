package domain.entities.notificarClientes;

import domain.entities.acao.Acao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Administrador implements Publisher {

    private static Map<String, List<Listner>> listaAcoesDosClientes = new LinkedHashMap<>();

    @Override
    public void subscribe(String codigoAcao, Listner listner) {
        List<Listner> listners = Administrador.listaAcoesDosClientes.get(codigoAcao);
        listners.add(listner);
    }

    @Override
    public void unsubscribe(String codigoAcao, Listner listner) {
        List<Listner> listners = Administrador.listaAcoesDosClientes.get(codigoAcao);
        listners.remove(listner);
    }

    @Override
    public void notificarClientes(Acao acao, EventType eventType) {
        List<Listner> listners = Administrador.listaAcoesDosClientes.get(acao.getCodigo());
        for (Listner listner : listners) {
            if(eventType.equals(EventType.ATUALIZAR)) {
                listner.atualizarValorDasAcoesDasCarteiras(acao);
            } else {
                listner.removerAcoesDasCarteiras(acao);
            }
        }
    }

    @Override
    public void addAcaoListner(String codigoAcao) {
        listaAcoesDosClientes.put(codigoAcao,new ArrayList<>());
    }

    @Override
    public void removeAcaoListner(String codigoAcao) {
        listaAcoesDosClientes.remove(codigoAcao);
    }
}
