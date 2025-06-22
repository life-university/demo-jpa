package com.example.jpa_basic.hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HelloJpa {

    private final EntityManagerFactory emf;

    public HelloJpa(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void helloPersist() {
        System.out.println("HelloJpa.helloPersist");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("Hello JPA");

            // Persisting the member entity
            em.persist(member);

            Member member2 = new Member();
            member2.setId(2L);
            member2.setName("Hello Second");

            // Persisting the member entity
            em.persist(member2);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void helloFind() {
        System.out.println("HelloJpa.helloFind");
        EntityManager em = emf.createEntityManager();
        try {
            // find member entity by ID
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember = " + findMember.getId() + " / " + findMember.getName());
        } catch (Exception e) {
            //
        } finally {
            em.close();
        }
    }

    public void helloRemove() {
        System.out.println("HelloJpa.helloRemove");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // find member entity by ID
            Member findMember = em.find(Member.class, 1L);
            // Remove the member entity
            em.remove(findMember);
            System.out.println("findMember = " + findMember.getId() + " / " + findMember.getName());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void helloUpdate() {
        System.out.println("HelloJpa.helloUpdate");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // find member entity by ID
            Member findMember = em.find(Member.class, 2L);
            findMember.setName("Hello IntelliJ IDEA");
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void helloFindJPQL() {
        System.out.println("HelloJpa.helloFindJPQL");
        EntityManager em = emf.createEntityManager();
        try {
            List<Member> findMembers = em.createQuery("select m from Member m", Member.class)
                .getResultList();
            System.out.println("findMembers = " + findMembers);

            List<Member> findMembers2 = em.createQuery("select m from Member m where m.id >= 1", Member.class)
                .getResultList();
            System.out.println("findMembers2 = " + findMembers2);
        } catch (Exception e) {
            //
        } finally {
            em.close();
        }
    }

}
