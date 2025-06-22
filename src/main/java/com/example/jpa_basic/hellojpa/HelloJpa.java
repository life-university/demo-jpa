package com.example.jpa_basic.hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class HelloJpa {

    private final EntityManagerFactory emf;

    public HelloJpa(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void helloPersist() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("Hello JPA");

            // Persisting the member entity
            em.persist(member);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void helloFind() {
        EntityManager em = emf.createEntityManager();
        try {
            // find member entity by ID
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember = " + findMember.getId() + " / " + findMember.getName());
        } catch (Exception e) {
            ///
        } finally {
            em.close();
        }
    }
}
