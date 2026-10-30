package com.tp.dao;

import com.tp.model.Utilisateur;
import com.tp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

/**
 * Implémentation DAO pour l'entité Utilisateur.
 */
public class UtilisateurDao implements IDao<Utilisateur> {

    @Override
    public boolean create(Utilisateur o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(o);
            tx.commit();
            System.out.printf("  [UtilisateurDao] Utilisateur créé avec succès : ID=%d, Nom='%s %s'%n",
                    o.getId(), o.getPrenom(), o.getNom());
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("  [UtilisateurDao Error] Échec de création : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Utilisateur o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(o);
            tx.commit();
            System.out.printf("  [UtilisateurDao] Utilisateur mis à jour avec succès : ID=%d, Nom='%s %s'%n",
                    o.getId(), o.getPrenom(), o.getNom());
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("  [UtilisateurDao Error] Échec de mise à jour : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Utilisateur o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Utilisateur managedUser = session.get(Utilisateur.class, o.getId());
            if (managedUser != null) {
                session.remove(managedUser);
                tx.commit();
                System.out.printf("  [UtilisateurDao] Utilisateur supprimé avec succès : ID=%d%n", o.getId());
                return true;
            } else {
                System.out.printf("  [UtilisateurDao Warning] Utilisateur introuvable pour suppression : ID=%d%n", o.getId());
                return false;
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("  [UtilisateurDao Error] Échec de suppression : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Utilisateur findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Utilisateur.class, id);
        } catch (Exception e) {
            System.err.println("  [UtilisateurDao Error] Erreur lors de la recherche par ID : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Recherche un utilisateur par son adresse email.
     *
     * @param email L'adresse email unique.
     * @return L'entité Utilisateur correspondante ou null.
     */
    public Utilisateur findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Utilisateur> query = session.createQuery("from Utilisateur u where u.email = :email", Utilisateur.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            System.err.println("  [UtilisateurDao Error] Erreur lors de la recherche par email : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Utilisateur> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Utilisateur order by id asc", Utilisateur.class).list();
        } catch (Exception e) {
            System.err.println("  [UtilisateurDao Error] Erreur lors de la récupération des utilisateurs : " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
