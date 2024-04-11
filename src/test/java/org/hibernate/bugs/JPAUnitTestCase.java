package org.hibernate.bugs;

import java.util.List;

import org.hibernate.entity.TestDetailEntity;
import org.hibernate.entity.TestSpecializedEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    // Entities are auto-discovered, so just add them anywhere on class-path
    // Add your tests, using standard JUnit.
    @Test
    public void testQueryDetailsFromParentJoined() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        final TestSpecializedEntity entity = new TestSpecializedEntity();
        entity.baseAttribute = "A";
        entity.anotherAttribute = "B";
        new TestDetailEntity(entity, "C");
        new TestDetailEntity(entity, "D");
        entityManager.persist(entity);

        final List<TestSpecializedEntity> results = entityManager.createQuery(
                "select d from TestSpecializedEntity e join e.details d where e.id = :id", TestSpecializedEntity.class
            )
            .setParameter("id", entity.id)
            .getResultList();

        Assertions.assertEquals(2, results.size());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
