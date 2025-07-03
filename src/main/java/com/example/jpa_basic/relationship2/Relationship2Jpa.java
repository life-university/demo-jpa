package com.example.jpa_basic.relationship2;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class Relationship2Jpa {

    private final EntityManagerFactory emf;

    public Relationship2Jpa(EntityManagerFactory emf) {
        this.emf = emf;
    }


}
