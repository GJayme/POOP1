package domain.entities.carteira;

import domain.entities.acao.Acao;
import domain.utils.EntityNotFoundException;

public class UpdateCarteiraUseCase {

    private CarteiraDAO carteiraDAO;

    public UpdateCarteiraUseCase(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public boolean update(Carteira carteira){
        if (carteira == null) {
            throw new EntityNotFoundException("Carteira não encontrada!");
        }
        if(carteiraDAO.findOne(carteira.getCpf()) == null){
            throw new EntityNotFoundException("Carteira não encontrada!");
        }
        if(carteira.getCpf() == null){
            throw new EntityNotFoundException("CPF da carteira não informada!");
        }

        carteiraDAO.update(carteira);
        return true;
    }
}
