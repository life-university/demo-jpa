package com.example.jpa_basic.jpa_pk;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(
    name = "student_seq_generator",
    sequenceName = "student_seq", // This is the name of the sequence in the database
    initialValue = 1,
    allocationSize = 50
)
public class StudentSequenceMapping {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_seq_generator" // This should match the name of the SequenceGenerator defined above
    )
    private Long id;
    private String name;

    public StudentSequenceMapping() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
