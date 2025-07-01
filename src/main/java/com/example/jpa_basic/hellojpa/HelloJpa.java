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
            List<Member> findMembers = em.createQuery("select m from StudentIdentity m", Member.class)
                .getResultList();
            System.out.println("findMembers = " + findMembers);

            List<Member> findMembers2 = em.createQuery("select m from StudentIdentity m where m.id >= 1", Member.class)
                .getResultList();
            System.out.println("findMembers2 = " + findMembers2);
        } catch (Exception e) {
            //
        } finally {
            em.close();
        }
    }

    public void firstLevelCache() {
        System.out.println("HelloJpa.firstLevelCache");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Member member = new Member();
            member.setId(1000L);
            member.setName("Hello JPA");

            // persistence context
            System.out.println("before persist");
            em.persist(member);
            System.out.println("after persist");

            Member find1 = em.find(Member.class, 1000L);
            Member find2 = em.find(Member.class, 1000L);
            Member find3 = em.find(Member.class, 1000L);
            System.out.println("find1 = " + find1.getId() + " / " + find1.getName());
            System.out.println("find2 = " + find2.getId() + " / " + find2.getName());
            System.out.println("find3 = " + find3.getId() + " / " + find3.getName());

            System.out.println("System.identityHashCode(find1) = " + System.identityHashCode(find1));
            System.out.println("System.identityHashCode(find2) = " + System.identityHashCode(find2));
            System.out.println("System.identityHashCode(find3) = " + System.identityHashCode(find3));

            System.out.println("before commit");
            em.getTransaction().commit();
            System.out.println("after commit");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void lazyLoading() {
        System.out.println("HelloJpa.lazyLoading");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Member member1 = new Member();
            member1.setId(1001L);
            member1.setName("1001 Member");

            Member member2 = new Member();
            member2.setId(1002L);
            member2.setName("1002 Member");

            System.out.println("member1 persist");
            em.persist(member1);
            System.out.println("member2 persist");
            em.persist(member2); // the insert query is not executed yet

            // When you commit, the insert query is executed.
            System.out.println("before commit");
            em.getTransaction().commit();
            System.out.println("after commit");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // Bad usage examples
    public void badUsage() {
        System.out.println("HelloJpa.badUsage");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Member findMember = em.find(Member.class, 1001L);
            // findMember = Member{id=1001, name='1001 Member'}
            System.out.println("findMember = " + findMember);

            // bad usage: JPA detects changes internally and automatically generates update queries.
            // dirty checking
            if (findMember.getName().equals("1001 Member")) {
                findMember.setName("Member Name Updated");
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
