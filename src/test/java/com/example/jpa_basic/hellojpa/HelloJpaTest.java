package com.example.jpa_basic.hellojpa;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloJpaTest {

    @Autowired
    HelloJpa helloJpa;

    @Test
    void helloPersist() {
        helloJpa.helloPersist();
    }

    @Test
    void helloFind() {
        helloJpa.helloFind();
    }

    @Test
    void firstLevelCache() {
        helloJpa.firstLevelCache();
    }
}
