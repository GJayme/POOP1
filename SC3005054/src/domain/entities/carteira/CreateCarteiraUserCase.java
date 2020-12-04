package domain.entities.carteira;

import domain.entities.notificarClientes.Publisher;
import domain.entities.aplicacao.Aplicacao;
import domain.utils.EntityAlreadyExist;
import domain.utils.EntityNotFoundException;

public class CreateCarteiraUserCase {

    private CarteiraDAO carteiraDAO;
    private Publisher publisher;

    public CreateCarteiraUserCase(CarteiraDAO carteiraDAO, Publisher publisher) {
        this.carteiraDAO = carteiraDAO;
        this.publisher = publisher;
    }

    public boolean create(Carteira carteira){
        if (carteira == null) {
            throw new EntityNotFoundException("Carteirta não encontrada!");
        }
        if(carteira.getCpf() == null || carteira.getCpf().trim().equals("")){
            throw new EntityNotFoundException("CPF da carteira não informada!");
        }
        if(carteiraDAO.findOne(carteira.getCpf()) != null){
            throw new EntityAlreadyExist("Carteira já cadastrada!");
        }

        carteiraDAO.insert(carteira);
        for (Aplicacao aplicacao : carteira.listarAplicao()) {
            String codigoAcao = aplicacao.getAcao().getCodigo();

            UpdateAplicacoesCarteirasUseCase updateAplicacoesCarteirasUseCase = new UpdateAplicacoesCarteirasUseCase(carteiraDAO);

            publisher.subscribe(codigoAcao,updateAplicacoesCarteirasUseCase);
        }
        return true;
    }
}
