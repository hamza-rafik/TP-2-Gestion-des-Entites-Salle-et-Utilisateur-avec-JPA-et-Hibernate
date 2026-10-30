package com.tp.service;

import com.tp.dao.UtilisateurDao;

/**
 * Alias de UserService en français (UtilisateurService).
 */
public class UtilisateurService extends UserService {

    public UtilisateurService() {
        super();
    }

    public UtilisateurService(UtilisateurDao dao) {
        super(dao);
    }
}
