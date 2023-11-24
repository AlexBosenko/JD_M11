package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    @JoinColumn(name="from_planet_id", nullable = false)
    private Planet fromPlanetId;

    @ManyToOne
    @JoinColumn(name="to_planet_id", nullable = false)
    private Planet toPlanetId;
}
