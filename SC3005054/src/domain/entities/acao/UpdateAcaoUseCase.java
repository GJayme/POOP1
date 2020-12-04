package domain.entities.acao;

import domain.entities.notificarClientes.Publisher;
import domain.entities.notificarClientes.EventType;
import domain.utils.EntityNotFoundException;

public class UpdateAcaoUseCase {
    private AcaoDAO acaoDAO;
    private Publisher publisher;

    public UpdateAcaoUseCase(AcaoDAO acaoDAO, Publisher publisher) {
        this.acaoDAO = acaoDAO;
        this.publisher = publisher;
    }

    public boolean update(Acao acao){
        if (acao == null) {
            throw new EntityNotFoundException("Ação não encontrada!");
        }
        if(acaoDAO.findOne(acao.getCodigo()) == null){
            throw new EntityNotFoundException("Ação não encontrada!");
        }
        if(acao.getCodigo() == null){
            throw new EntityNotFoundException("Código da ação não informada!");
        }

        acaoDAO.update(acao);
        publisher.notificarClientes(acao, EventType.ATUALIZAR);
        return true;
    }
}
