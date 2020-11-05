package pl.poznan.put.cie.coffee.EntityModel;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SupplierDao {

    private final EntityManager entityManager;

    public SupplierDao() {
        var factory = Persistence.createEntityManagerFactory("entity_JPA");

        entityManager = factory.createEntityManager();
    }

    public List<SupplierElements> getAllEnt() {
        return entityManager
                .createQuery("SELECT s FROM SUPPLIERS s", SupplierElements.class)
                .getResultList();
    }


    public Optional<SupplierElements> getEnt(String name) {
        try {
            var ent = entityManager
                    .createQuery("SELECT ent FROM SUPPLIERS ent WHERE ent.name = :name", SupplierElements.class)
                    .setParameter("name", name)
                    .getSingleResult();

            return Optional.of(ent);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void getIdList()
    {
        var list = getAllEnt();
        for(var e : list)
            System.out.println("supplierName: " + e.getName() + ", supplierId: " + e.getId());
    }

    public void saveEnt(SupplierElements ent) {
        entityManager.getTransaction().begin();

        try {
            entityManager.merge(ent);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteEnt(String name) {
        entityManager.getTransaction().begin();

        try {
            getEnt(name).ifPresent(entityManager::remove);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
}