package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "planet")
public class Planet {
    @Id
    private String id;
    @Column
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
