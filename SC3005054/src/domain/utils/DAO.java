package domain.utils;

import java.util.List;

public interface DAO <K, T>{
    boolean insert(T object);

    boolean update(T object);

    T findOne(K key);

    List<T> findAll();

    boolean remove(K key);
}
