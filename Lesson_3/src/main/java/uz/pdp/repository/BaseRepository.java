package uz.pdp.repository;

import java.util.List;

public interface BaseRepository<T, ID> {
    List<T> findAll();
    T findById(ID id);
    void save(T item);
    void update(T item);
}