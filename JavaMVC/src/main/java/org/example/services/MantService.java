package org.example.services;

import org.example.models.Car;
import org.example.models.Mantenimiento;

import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Date;
public class MantService {

    private final SessionFactory sessionFactory;

    public MantService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Mantenimiento createMantenimiento(Long id, Date fecha,String descripcion, String tipo, Car car) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Mantenimiento mantenimiento = new Mantenimiento();
            mantenimiento.setId(id);
            mantenimiento.setFecha(fecha);
            mantenimiento.setDescripcion(descripcion);
            mantenimiento.setTipo(tipo);
            mantenimiento.setCar(car);

            session.persist(mantenimiento);
            tx.commit();
            return mantenimiento;
        }
    }

    // -------------------------
    // READ
    // -------------------------
    public Mantenimiento getMantenimientoById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Mantenimiento.class, id);
        }
    }

    public List<Mantenimiento> getAllMantenimientos() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM mantenimiento", Mantenimiento.class).list();
        }
    }

    public List<Mantenimiento> getMantenimientosByCar(Car car) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM mantenimiento WHERE car = :car", Mantenimiento.class)
                    .setParameter("car", car)
                    .list();
        }
    }

    // -------------------------
    // UPDATE
    // -------------------------
    public Mantenimiento updateMantenimiento(Long id, Date fecha,String descripcion, String tipo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Mantenimiento mant = session.find(Mantenimiento.class, id);
            if (mant != null) {
                mant.setFecha(fecha);
                mant.setDescripcion(descripcion);
                mant.setTipo(tipo);
                session.merge(mant);
            }

            tx.commit();
            return mant;
        }
    }

    // -------------------------
    // DELETE
    // -------------------------
    public boolean deleteCar(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Mantenimiento mant = session.find(Mantenimiento.class, id);
            if (mant != null) {
                session.remove(mant);
                tx.commit();
                return true;
            }

            tx.rollback();
            return false;
        }
    }
}
