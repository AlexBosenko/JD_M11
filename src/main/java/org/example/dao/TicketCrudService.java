package org.example.dao;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class TicketCrudService {
    private final Session session;

    public TicketCrudService(Session session) {
        this.session = session;
    }

    public Ticket createNewTicket(Client client, Planet fromPlanet, Planet toPlanet) {
        Transaction transaction = session.beginTransaction();
        try {
            Ticket newTicket = new Ticket();
            newTicket.setClient(client);
            newTicket.setCreatedAt(ZonedDateTime.now(ZoneOffset.UTC));
            newTicket.setFromPlanetId(fromPlanet);
            newTicket.setToPlanetId(toPlanet);
            session.persist(newTicket);

            transaction.commit();

            return newTicket;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }

        return null;
    }
}
