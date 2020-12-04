package domain.entities.acao;

import domain.utils.EntityNotFoundException;

public class FindOneAcaoUseCase {

    private AcaoDAO acaoDAO;

    public FindOneAcaoUseCase(AcaoDAO acaoDAO) {
        this.acaoDAO = acaoDAO;
    }

    public Acao findOne(String codigoAcao){
        if (codigoAcao == null || codigoAcao.trim().equals("")) {
            throw new EntityNotFoundException("Codigo da ação nao existe!");
        }
        Acao acao = acaoDAO.findOne(codigoAcao);

        if (acao == null) {
            throw new EntityNotFoundException("Ação nao existe!");
        }

        return acao;
    }
}
