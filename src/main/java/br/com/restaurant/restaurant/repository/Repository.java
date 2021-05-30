package br.com.restaurant.restaurant.repository;

import java.util.List;

public interface Repository<T> {
    T find(Integer id);
    List<T> findAll();
    T save(T entity);
    T update(T Entity);
    void delete(Integer id);

}
