package com.example.jpa_basic.jpa_pk;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StudentJpa {

    private final EntityManagerFactory emf;

    public StudentJpa(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void studentIdentityPersist() {
        System.out.println("StudentJpa.studentPersist");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            StudentIdentity student = new StudentIdentity();
            student.setName("Student Identity Name");

            System.out.println("================");
            em.persist(student);
            System.out.println("================ " + student.getId());

            System.out.println("================ : Commiting transaction");
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void studentSequencePersist() {
        System.out.println("StudentJpa.studentSequencePersist");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            StudentSequence student = new StudentSequence();
            student.setName("Student Sequence Name");

            em.persist(student);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void studentSequenceMappingPersist() {
        System.out.println("StudentJpa.studentSequenceMappingPersist");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            StudentSequenceMapping student = new StudentSequenceMapping();
            student.setName("Student Sequence Mapping Name");

            System.out.println("================");
            em.persist(student);
            System.out.println("================");

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void studentTablePersist() {
        System.out.println("StudentJpa.studentTablePersist");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            StudentTable student = new StudentTable();
            student.setName("Student Table Name");

            em.persist(student);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
