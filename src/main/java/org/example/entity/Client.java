package org.example.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets;

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
