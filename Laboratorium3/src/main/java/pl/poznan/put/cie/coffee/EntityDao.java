package pl.poznan.put.cie.coffee;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EntityDao {

    private final EntityManager entityManager;

    public EntityDao() {
        var factory = Persistence.createEntityManagerFactory("entity_JPA");

        entityManager = factory.createEntityManager();
    }

    public List<EntityElements> getAllEnt() {
        return entityManager
                .createQuery("SELECT s FROM SUPPLIERS s", EntityElements.class)
                .getResultList();
    }


    public Optional<EntityElements> getEnt(Integer id) {
        try {
            var ent = entityManager
                    .createQuery("SELECT ent FROM SUPPLIERS ent WHERE ent.id = :id", EntityElements.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.of(ent);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void saveEnt(EntityElements ent) {
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