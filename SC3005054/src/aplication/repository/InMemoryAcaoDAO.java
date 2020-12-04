package aplication.repository;

import domain.entities.acao.Acao;
import domain.entities.acao.AcaoDAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InMemoryAcaoDAO implements AcaoDAO {

    private static Map<String, Acao> db = new LinkedHashMap<>();

    @Override
    public boolean insert(Acao object) {
        if(db.containsKey(object.getCodigo())){
            return false;
        }

        db.put(object.getCodigo(), object);
        return true;
    }

    @Override
    public boolean update(Acao object) {
        String acaoCodigo = object.getCodigo();
        if(!db.containsKey(acaoCodigo)){
            return false;
        }

        db.replace(object.getCodigo(), object);
        return true;
    }

    @Override
    public Acao findOne(String key) {
        if(!db.containsKey(key)){
            return null;
        }
        return db.get(key);
    }

    @Override
    public List<Acao> findAll() {
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
