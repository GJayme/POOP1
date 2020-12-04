package domain.entities.carteira;

import domain.utils.EntityNotFoundException;

public class GetUmaCarteiraUseCase {

    private CarteiraDAO carteiraDAO;

    public GetUmaCarteiraUseCase(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public Carteira getCarteira(String cpf) {
        if(cpf == null || cpf.trim().equals("")){
            throw new EntityNotFoundException("CPF da carteira não informada!");
        }

        return carteiraDAO.findOne(cpf);
    }
}
