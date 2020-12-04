package domain.entities.carteira;

import domain.entities.notificarClientes.Listner;
import domain.entities.acao.Acao;
import domain.entities.aplicacao.Aplicacao;

import java.util.ArrayList;
import java.util.List;

public class UpdateAplicacoesCarteirasUseCase implements Listner {

    private CarteiraDAO carteiraDAO;

    public UpdateAplicacoesCarteirasUseCase(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    @Override
    public void atualizarValorDasAcoesDasCarteiras(Acao acao) {
        List<Carteira> carteiras = carteiraDAO.findAll();
        for (Carteira carteira : carteiras) {
            for (Aplicacao aplicacao : carteira.listarAplicao()) {
                Acao cadaAcao = aplicacao.getAcao();
                if (cadaAcao.equals(acao)){
                    aplicacao.setAcao(acao);
                }
            }
            carteiraDAO.update(carteira);
        }
    }

    @Override
    public void removerAcoesDasCarteiras(Acao acao) {
        List<Carteira> carteiras = carteiraDAO.findAll();
        for (Carteira carteira : carteiras) {
            List<Aplicacao> tempAplicacao = new ArrayList<>();
            for (Aplicacao aplicacao : carteira.listarAplicao()) {
                Acao cadaAcao = aplicacao.getAcao();
                if (cadaAcao.equals(acao)){
                    tempAplicacao.add(aplicacao);
                }
            }
            carteira.removeAplicacao(tempAplicacao);
            carteiraDAO.update(carteira);
        }
    }
}
