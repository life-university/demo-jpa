package com.example.jpa_basic.relationship;

import com.example.jpa_basic.jpa_pk.StudentIdentity;
import com.example.jpa_basic.jpa_pk.StudentSequence;
import com.example.jpa_basic.jpa_pk.StudentSequenceMapping;
import com.example.jpa_basic.jpa_pk.StudentTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RelationshipJpa {

    private final EntityManagerFactory emf;

    public RelationshipJpa(EntityManagerFactory emf) {
        this.emf = emf;
    }

/*
    // object reference x
    public void relationshipPersist() {
        System.out.println("RelationshipJpa.relationshipPersist");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // Department
            Department department = new Department();
            department.setName("Computer Science");
            em.persist(department);

            // Student
            Student student = new Student();
            student.setDepartmentId(department.getId());
            student.setUsername("student1");
            em.persist(student);

            // 조회
            Student findStudent = em.find(Student.class, student.getId());
            Long findDepartmentId = findStudent.getDepartmentId();
            Department findDepartment = em.find(Department.class, findDepartmentId);
            // ...

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
*/

    public void relationshipPersist() {
        System.out.println("RelationshipJpa.relationshipPersist");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // Department
            Department department = new Department();
            department.setName("Computer Science");
            em.persist(department);

            // Student
            Student student = new Student();
            student.setDepartment(department);
            student.setUsername("student1");
            em.persist(student);

            // em.flush()와 em.clear()를 사용하여 영속성 컨텍스트를 초기화
            em.flush();
            em.clear();

            // select
            Student findStudent = em.find(Student.class, student.getId());
            System.out.println("findStudent.getDepartment().getName() = " + findStudent.getDepartment().getName());

            // update
            findStudent.getDepartment().setName("Computer Science2");

            em.flush();
            em.clear();

            Student findStudent2 = em.find(Student.class, student.getId());
            System.out.println("findStudent2.getDepartment().getName() = " + findStudent2.getDepartment().getName());

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void bidirectional() {
        System.out.println("RelationshipJpa.bidirectional");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // Department
            Department department = new Department();
            department.setName("Computer Science");
            em.persist(department);

            // Student
            Student student1 = new Student();
            student1.setDepartment(department);
            student1.setUsername("student1");
            em.persist(student1);

            Student student2 = new Student();
            student2.setDepartment(department);
            student2.setUsername("student2");
            em.persist(student2);

            // em.flush()와 em.clear()를 사용하여 영속성 컨텍스트를 초기화
            em.flush();
            em.clear();

            // select
            Student findStudent = em.find(Student.class, student2.getId());
            List<Student> students = findStudent.getDepartment().getStudents();
            for (Student s : students) {
                System.out.println("student .getUsername() = " + s.getUsername());
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void mistake1() {
        System.out.println("RelationshipJpa.mistake1");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // Student
            Student student1 = new Student();
            student1.setUsername("mistake_student1");
            em.persist(student1);

            // Department
            Department department = new Department();
            department.setName("Computer Science");
            department.getStudents().add(student1);
            em.persist(department);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void caution1() {
        System.out.println("RelationshipJpa.caution1");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // Department
            Department department = new Department();
            department.setName("Computer Science");
            em.persist(department);

            // Student
            Student student1 = new Student();
            student1.setUsername("caution_student1");
            student1.changeDepartment(department);
            em.persist(student1);

            // department.getStudents().add(student1);
            // department.addStudent(student1);

            Department findDepartment = em.find(Department.class, department.getId());
            List<Student> students = findDepartment.getStudents();
            System.out.println("==================================== students.size : " + students.size());
            for (Student s : students) {
                System.out.println("student .getUsername() = " + s.getUsername());
            }
            System.out.println("====================================");

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void unlimitedLoop() {
        System.out.println("RelationshipJpa.unlimitedLoop");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // Department
            Department department = new Department();
            department.setName("Computer Science");
            em.persist(department);

            // Student
            Student student1 = new Student();
            student1.setUsername("caution_student1");
            student1.changeDepartment(department);
            em.persist(student1);

            Department findDepartment = em.find(Department.class, department.getId());
            System.out.println("findDepartment = " + findDepartment);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
