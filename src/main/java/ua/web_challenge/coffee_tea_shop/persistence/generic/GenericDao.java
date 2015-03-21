package ua.web_challenge.coffee_tea_shop.persistence.generic;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 18.03.2015
 *
 * @author Bohdan Vanchuhov
 */
public interface GenericDao<T, PK extends Serializable> {
    List<T> findRange(int maxResults);
    T add(T object);
    T findById(PK id);
    T findByName(String name);
    T update(T object);
    void delete(T object);
}
