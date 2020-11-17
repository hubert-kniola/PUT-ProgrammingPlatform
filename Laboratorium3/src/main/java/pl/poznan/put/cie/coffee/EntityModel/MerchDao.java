package pl.poznan.put.cie.coffee.EntityModel;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class MerchDao {

    private final EntityManager entityManager;

    public MerchDao() {
        var factory = Persistence.createEntityManagerFactory("entity_JPA");

        entityManager = factory.createEntityManager();
    }

    public List<MerchElements> getAllEnt() {
        return entityManager
                .createQuery("SELECT m FROM MERCH_INVENTORY m", MerchElements.class)
                .getResultList();
    }

    public Optional<MerchElements> getEnt(Integer id) {
        try {
            var ent = entityManager
                    .createQuery("SELECT m FROM MERCH_INVENTORY m WHERE m.id = :id", MerchElements.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.of(ent);

        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public void saveEnt(MerchElements ent) {
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
