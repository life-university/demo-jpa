package com.example.jpa_basic.jpa_pk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentJpaTest {

    @Autowired
    StudentJpa studentJpa;

    @Test
    void studentIdentity() {
        studentJpa.studentIdentityPersist();
        studentJpa.studentIdentityPersist();
        studentJpa.studentIdentityPersist();
        studentJpa.studentIdentityPersist();
    }

    @Test
    void studentSequence() {
        studentJpa.studentSequencePersist();
        studentJpa.studentSequencePersist();
        studentJpa.studentSequencePersist();
        studentJpa.studentSequencePersist();
    }

    @Test
    void studentSequenceMapping() {
        studentJpa.studentSequenceMappingPersist();
        studentJpa.studentSequenceMappingPersist();
        studentJpa.studentSequenceMappingPersist();
        studentJpa.studentSequenceMappingPersist();
        studentJpa.studentSequenceMappingPersist();
        studentJpa.studentSequenceMappingPersist();
        studentJpa.studentSequenceMappingPersist();
        studentJpa.studentSequenceMappingPersist();
    }

    @Test
    void studentTable() {
        studentJpa.studentTablePersist();
        studentJpa.studentTablePersist();
        studentJpa.studentTablePersist();
        studentJpa.studentTablePersist();
    }

}
