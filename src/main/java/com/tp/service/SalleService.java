package com.tp.service;

import com.tp.dao.IDao;
import com.tp.dao.SalleDao;
import com.tp.model.Salle;

import java.util.List;

/**
 * Couche Service pour la gestion des opérations métier sur les Salles.
 * Implémente l'interface générique IDao<Salle>.
 */
public class SalleService implements IDao<Salle> {

    private final SalleDao salleDao;

    public SalleService() {
        this.salleDao = new SalleDao();
    }

    public SalleService(SalleDao salleDao) {
        this.salleDao = salleDao;
    }

    @Override
    public boolean create(Salle o) {
        if (o == null || o.getCode() == null || o.getCode().trim().isEmpty()) {
            System.err.println("  [Service Validation] Le code de la salle est obligatoire.");
            return false;
        }
        return salleDao.create(o);
    }

    @Override
    public boolean update(Salle o) {
        if (o == null || o.getId() == null) {
            System.err.println("  [Service Validation] L'identifiant est requis pour la mise à jour.");
            return false;
        }
        return salleDao.update(o);
    }

    @Override
    public boolean delete(Salle o) {
        if (o == null || o.getId() == null) {
            System.err.println("  [Service Validation] L'identifiant est requis pour la suppression.");
            return false;
        }
        return salleDao.delete(o);
    }

    @Override
    public Salle findById(Long id) {
        if (id == null) return null;
        return salleDao.findById(id);
    }

    public Salle findByCode(String code) {
        if (code == null) return null;
        return salleDao.findByCode(code);
    }

    @Override
    public List<Salle> findAll() {
        return salleDao.findAll();
    }
}
