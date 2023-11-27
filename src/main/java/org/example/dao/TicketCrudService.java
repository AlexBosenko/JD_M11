package org.example.dao;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.utils.CheckParameter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.InputStream;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketCrudService {
    private final Session session;

    public TicketCrudService(Session session) {
        this.session = session;
    }

    public Ticket createNewTicket(Client client, Planet fromPlanet, Planet toPlanet) {
        if (!CheckParameter.of(client, Client.class.getSimpleName())
                || !CheckParameter.of(fromPlanet, Planet.class.getSimpleName())
                || !CheckParameter.of(toPlanet, Planet.class.getSimpleName())) {
            return null;
        }

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

    public boolean setClientById(Client curClient, Client newClient) {
        boolean result = false;

        Transaction transaction = session.beginTransaction();
        try {
            session.createQuery("update Ticket set client = :newClient where client = :curClient")
                    .setParameter("newClient", newClient)
                    .setParameter("curClient", curClient)
                    .executeUpdate();
            transaction.commit();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }

        return result;
    }

    public List<Ticket> getClientTickets(Client client) {
        return session.createQuery("from Ticket where client = :client", Ticket.class)
                .setParameter("client", client)
                .list();
    }

    public List<Ticket> getAllTickets() {
        return session.createQuery("from Ticket", Ticket.class).list();
    }

    public void deleteTicketById(Long id) {
        Transaction transaction = session.beginTransaction();
        try {
            Ticket ticket = session.get(Ticket.class, id);
            session.remove(ticket);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
    }
}
