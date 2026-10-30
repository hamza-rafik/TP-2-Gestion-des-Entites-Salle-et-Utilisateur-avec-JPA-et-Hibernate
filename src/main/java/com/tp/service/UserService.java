package com.tp.service;

import com.tp.dao.IDao;
import com.tp.dao.UtilisateurDao;
import com.tp.model.Utilisateur;

import java.util.List;

/**
 * Couche Service pour la gestion des opérations métier sur les Utilisateurs.
 * Implémente l'interface générique IDao<Utilisateur>.
 */
public class UserService implements IDao<Utilisateur> {

    private final UtilisateurDao utilisateurDao;

    public UserService() {
        this.utilisateurDao = new UtilisateurDao();
    }

    public UserService(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public boolean create(Utilisateur o) {
        if (o == null || o.getEmail() == null || o.getEmail().trim().isEmpty()) {
            System.err.println("  [Service Validation] L'adresse email est obligatoire.");
            return false;
        }
        return utilisateurDao.create(o);
    }

    @Override
    public boolean update(Utilisateur o) {
        if (o == null || o.getId() == null) {
            System.err.println("  [Service Validation] L'identifiant est requis pour la mise à jour.");
            return false;
        }
        return utilisateurDao.update(o);
    }

    @Override
    public boolean delete(Utilisateur o) {
        if (o == null || o.getId() == null) {
            System.err.println("  [Service Validation] L'identifiant est requis pour la suppression.");
            return false;
        }
        return utilisateurDao.delete(o);
    }

    @Override
    public Utilisateur findById(Long id) {
        if (id == null) return null;
        return utilisateurDao.findById(id);
    }

    public Utilisateur findByEmail(String email) {
        if (email == null) return null;
        return utilisateurDao.findByEmail(email);
    }

    @Override
    public List<Utilisateur> findAll() {
        return utilisateurDao.findAll();
    }
}
