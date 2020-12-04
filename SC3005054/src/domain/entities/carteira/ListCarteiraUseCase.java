package domain.entities.carteira;

import java.util.List;

public class ListCarteiraUseCase {

    private CarteiraDAO carteiraDAO;

    public ListCarteiraUseCase(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public List<Carteira> listCarteira(){
        return carteiraDAO.findAll();
    }
}
