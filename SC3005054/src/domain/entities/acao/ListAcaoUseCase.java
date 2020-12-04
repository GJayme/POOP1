package domain.entities.acao;

import java.util.List;

public class ListAcaoUseCase {

    private AcaoDAO acaoDAO;

    public ListAcaoUseCase(AcaoDAO acaoDAO) {
        this.acaoDAO = acaoDAO;
    }

    public List<Acao> listAcoes(){
        return acaoDAO.findAll();
    }
}
