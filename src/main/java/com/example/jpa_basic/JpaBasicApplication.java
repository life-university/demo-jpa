package com.example.jpa_basic;

import com.example.jpa_basic.hellojpa.HelloJpa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JpaBasicApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JpaBasicApplication.class, args);

        HelloJpa helloJpa = context.getBean("helloJpa", HelloJpa.class);
        helloJpa.helloPersist();
        helloJpa.helloFind();
        helloJpa.helloRemove();
        helloJpa.helloUpdate();
        helloJpa.helloFindJPQL();

    }

}
