package aplication.repository;

import domain.entities.carteira.Carteira;
import domain.entities.carteira.CarteiraDAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCarteiraDAO implements CarteiraDAO {

    private static Map<String, Carteira> db = new LinkedHashMap<>();

    @Override
    public boolean insert(Carteira object) {
        if(db.containsKey(object.getCpf())){
            return false;
        }

        db.put(object.getCpf(), object);
        return true;
    }

    @Override
    public boolean update(Carteira object) {
        String cpf = object.getCpf();
        if(!db.containsKey(cpf)){
            return false;
        }

        db.replace(object.getCpf(), object);
        return true;
    }

    @Override
    public Carteira findOne(String key) {
        if(!db.containsKey(key)){
            return null;
        }
        return db.get(key);
    }

    @Override
    public List<Carteira> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean remove(String key) {
        if(!db.containsKey(key)){
            return false;
        }
        db.remove(key);
        return false;
    }
}
