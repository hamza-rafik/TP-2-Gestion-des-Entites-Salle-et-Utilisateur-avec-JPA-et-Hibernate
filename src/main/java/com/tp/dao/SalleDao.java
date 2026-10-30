package com.tp.dao;

import com.tp.model.Salle;
import com.tp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

/**
 * Implémentation DAO pour l'entité Salle.
 */
public class SalleDao implements IDao<Salle> {

    @Override
    public boolean create(Salle o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(o);
            tx.commit();
            System.out.printf("  [SalleDao] Salle créée avec succès : ID=%d, Code='%s'%n", o.getId(), o.getCode());
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("  [SalleDao Error] Échec de création : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Salle o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(o);
            tx.commit();
            System.out.printf("  [SalleDao] Salle mise à jour avec succès : ID=%d, Code='%s'%n", o.getId(), o.getCode());
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("  [SalleDao Error] Échec de mise à jour : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Salle o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Salle managedSalle = session.get(Salle.class, o.getId());
            if (managedSalle != null) {
                session.remove(managedSalle);
                tx.commit();
                System.out.printf("  [SalleDao] Salle supprimée avec succès : ID=%d%n", o.getId());
                return true;
            } else {
                System.out.printf("  [SalleDao Warning] Salle introuvable pour suppression : ID=%d%n", o.getId());
                return false;
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("  [SalleDao Error] Échec de suppression : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Salle findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Salle.class, id);
        } catch (Exception e) {
            System.err.println("  [SalleDao Error] Erreur lors de la recherche par ID : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Recherche une salle par son code unique.
     *
     * @param code Le code unique de la salle (ex: 'A101').
     * @return L'entité Salle correspondante ou null.
     */
    public Salle findByCode(String code) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Salle> query = session.createQuery("from Salle s where s.code = :code", Salle.class);
            query.setParameter("code", code);
            return query.uniqueResult();
        } catch (Exception e) {
            System.err.println("  [SalleDao Error] Erreur lors de la recherche par code : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Salle> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Salle order by id asc", Salle.class).list();
        } catch (Exception e) {
            System.err.println("  [SalleDao Error] Erreur lors de la récupération des salles : " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
