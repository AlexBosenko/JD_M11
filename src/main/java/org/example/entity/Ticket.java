package org.example.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="created_at")
    private ZonedDateTime createdAt;

    @ManyToOne
    private Client client;

    @ManyToOne
    @JoinColumn(name="from_planet_id", nullable = false)
    private Planet fromPlanetId;

    @ManyToOne
    @JoinColumn(name="to_planet_id", nullable = false)
    private Planet toPlanetId;

    public Long getId() {
        return id;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setFromPlanetId(Planet fromPlanetId) {
        this.fromPlanetId = fromPlanetId;
    }

    public void setToPlanetId(Planet toPlanetId) {
        this.toPlanetId = toPlanetId;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss z");
        return "Ticket{" +
                "id=" + id +
                ", createdAt=" + createdAt.format(formatter) +
                ", client=" + client +
                ", fromPlanetId=" + fromPlanetId +
                ", toPlanetId=" + toPlanetId +
                '}';
    }
}
