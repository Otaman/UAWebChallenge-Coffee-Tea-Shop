package ua.web_challenge.coffee_tea_shop.persistence.generic;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * Created on 18.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Transactional(propagation = SUPPORTS, readOnly = true)
public class JpaGenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public JpaGenericDaoImpl() {
        ParameterizedType genericSuperclass =
                (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public List<T> findRange(int maxResults) {
        String entityName = entityClass.getSimpleName();
        String queryString = JpaQueries.findAllQuery(entityName);
        TypedQuery<T> namedQuery = entityManager.createQuery(queryString, entityClass);

        if (maxResults > 0) {
            namedQuery.setMaxResults(maxResults);
        }

        return namedQuery.getResultList();
    }

    @Override
    public T findByName(String name) {
        String entityName = entityClass.getSimpleName();
        String queryString = JpaQueries.findByNameQuery(entityName);
        TypedQuery<T> namedQuery = entityManager.createQuery(queryString, entityClass);

        namedQuery.setParameter("name", name);

        return namedQuery.getSingleResult();
    }

    @Override
    @Transactional(propagation = REQUIRED, readOnly = false)
    public T add(T object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public T findById(PK id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    @Transactional(propagation = REQUIRED, readOnly = false)
    public T update(T object) {
        return entityManager.merge(object);
    }

    @Override
    @Transactional(propagation = REQUIRED, readOnly = false)
    public void delete(T object) {
        object = update(object);
        entityManager.remove(object);
    }
}
