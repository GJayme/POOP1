package domain.entities.acao;

import domain.entities.notificarClientes.Publisher;
import domain.utils.EntityAlreadyExist;
import domain.utils.EntityNotFoundException;

public class CreateAcaoUseCase {

    private AcaoDAO acaoDAO;
    private Publisher publisher;

    public CreateAcaoUseCase(AcaoDAO acaoDAO, Publisher publisher) {
        this.acaoDAO = acaoDAO;
        this.publisher = publisher;
    }

    public boolean insert (Acao acao){
        if (acao == null) {
            throw new EntityNotFoundException("Ação não encontrada!");
        }
        if(acao.getCodigo() == null || acao.getCodigo().trim().equals("")){
            throw new EntityNotFoundException("Código da ação não informada!");
        }
        if(acaoDAO.findOne(acao.getCodigo()) != null){
            throw new EntityAlreadyExist("Ação já cadastrada!");
        }

        acaoDAO.insert(acao);
        publisher.addAcaoListner(acao.getCodigo());
        return true;
    }
}
