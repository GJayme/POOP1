package domain.entities.acao;

import domain.entities.notificarClientes.Publisher;
import domain.entities.notificarClientes.EventType;
import domain.utils.EntityNotFoundException;

public class RemoveAcaoUseCase {

    private AcaoDAO acaoDAO;
    private Publisher publisher;

    public RemoveAcaoUseCase(AcaoDAO acaoDAO, Publisher publisher) {
        this.acaoDAO = acaoDAO;
        this.publisher = publisher;
    }

    public boolean remove (String acaoCodigo){
        if (acaoCodigo == null || acaoCodigo.trim().equals("")) {
            throw new EntityNotFoundException("Código da ação não pode ser nulo.");
        }

        Acao acaoEncontrada = acaoDAO.findOne(acaoCodigo);

        if(acaoEncontrada == null){
            throw new EntityNotFoundException("Ação não encontrada!");
        }

        acaoDAO.remove(acaoCodigo);
        publisher.notificarClientes(acaoEncontrada, EventType.REMOVER);
        publisher.removeAcaoListner(acaoCodigo);
        return true;
    }

}
