package com.example.jpa_basic.jpa_pk;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity
@TableGenerator(
    name = "table_generator",
    table = "table_gen", // This is the name of the table that will hold the generated values
    pkColumnName = "gen_name", // The column that holds the name of the generator
    valueColumnName = "gen_value", // The column that holds the current value of the generator
    initialValue = 0,
    allocationSize = 1 // This is the increment size for each allocation
)
public class StudentTable {

    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator = "table_generator" // This should match the name of the TableGenerator defined above
    )
    private Long id;
    private String name;

    public StudentTable() {
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
