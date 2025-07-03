package com.example.jpa_basic.relationship;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;
    private String username;
    // private Long departmentId;
    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void changeDepartment(Department department) {
        this.department = department;
        department.getStudents().add(this);
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", department=" + department +
            '}';
    }
}
