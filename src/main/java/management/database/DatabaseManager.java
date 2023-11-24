package management.database;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;
import lombok.Getter;

@Getter
public class DatabaseManager {
    private final EntityManager entityManager;

    public DatabaseManager() {
        entityManager = Persistence.createEntityManagerFactory("ScubaClubPU").createEntityManager();
    }

    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return entityManager.find(entityClass, primaryKey);
    }

    public void save(Object entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    public <T> T update(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T mergedEntity = entityManager.merge(entity);
            transaction.commit();
            return mergedEntity;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    public void delete(Object entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
