package org.example.dao;

import org.example.entity.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudService {
    private final Session session;

    public PlanetCrudService(Session session) {
        this.session = session;
    }

    public Planet createNewPlanet(String id, String name) {
        Transaction transaction = session.beginTransaction();
        try {
            Planet newPlanet = new Planet();
            newPlanet.setId(id);
            newPlanet.setName(name);
            session.persist(newPlanet);
            transaction.commit();

            return newPlanet;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
        return null;
    }

    public Planet getPlanetById(String id) {
        return session.get(Planet.class, id);
    }

    public List<Planet> getAllPlanets() {
        return session.createQuery("from Planet", Planet.class).list();
    }

    public Planet setPlanetNameById(String id, String newName) {
        Transaction transaction = session.beginTransaction();
        try {
            Planet planet = session.get(Planet.class, id);
            planet.setName(newName);
            session.persist(planet);
            transaction.commit();
            return planet;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }

        return null;
    }

    public void deletePlanetById(String id) {
        Transaction transaction = session.beginTransaction();
        try {
            Planet planet = session.get(Planet.class, id);
            session.remove(planet);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
    }
}
