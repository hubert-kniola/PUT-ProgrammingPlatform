package pl.poznan.put.cie.coffee.EntityModel;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class CoffHaousesDao {

    private final EntityManager entityManager;

    public CoffHaousesDao() {
        var factory = Persistence.createEntityManagerFactory("entity_JPA");

        entityManager = factory.createEntityManager();
    }

    public List<CoffHousesElements> getAllEnt() {
        return entityManager
                .createQuery("SELECT ch FROM COFFEE_HOUSES ch", CoffHousesElements.class)
                .getResultList();
    }

    public Optional<CoffHousesElements> getEnt(Integer id) {
        try {
            var ent = entityManager
                    .createQuery("SELECT ch FROM COFFEE_HOUSES ch WHERE ch.id = :id", CoffHousesElements.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.of(ent);

        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public void saveEnt(CoffHousesElements ent) {
        entityManager.getTransaction().begin();

        try {
            entityManager.merge(ent);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteEnt(Integer id) {
        entityManager.getTransaction().begin();

        try {
            getEnt(id).ifPresent(entityManager::remove);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
}
