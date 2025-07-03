package com.example.jpa_basic.relationship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RelationshipJpaTest {

    @Autowired
    RelationshipJpa relationshipJpa;

    @Test
    void relationshipPersist() {
        relationshipJpa.relationshipPersist();
    }

    @Test
    void bidirectional() {
        relationshipJpa.bidirectional();
    }

    @Test
    void mistake1() {
        relationshipJpa.mistake1();
    }

    @Test
    void caution1() {
        relationshipJpa.caution1();
    }

    @Test
    void unlimitedLoop() {
        relationshipJpa.unlimitedLoop();
    }

}
