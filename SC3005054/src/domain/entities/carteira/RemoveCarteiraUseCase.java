package domain.entities.carteira;

import domain.utils.EntityNotFoundException;

public class RemoveCarteiraUseCase {

    private CarteiraDAO carteiraDAO;

    public RemoveCarteiraUseCase(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public boolean remove (String cpf){
        if (cpf == null || cpf.trim().equals("")) {
            throw new EntityNotFoundException("CPF da carteira não pode ser nulo.");
        }
        if(carteiraDAO.findOne(cpf.trim()) == null){
            throw new EntityNotFoundException("carteira não encontrada!");
        }

        carteiraDAO.remove(cpf.trim());
        return true;
    }
}
