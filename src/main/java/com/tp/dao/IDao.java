package com.tp.dao;

import java.util.List;

/**
 * Interface générique DAO définissant les opérations CRUD standards.
 * Respecte le patron de conception Data Access Object (DAO).
 *
 * @param <T> Le type de l'entité JPA gérée.
 */
public interface IDao<T> {

    /**
     * Insérer une nouvelle entité en base de données (CREATE).
     *
     * @param o L'objet à persister.
     * @return true si l'opération a réussi, false sinon.
     */
    boolean create(T o);

    /**
     * Mettre à jour une entité existante (UPDATE).
     *
     * @param o L'objet à modifier.
     * @return true si l'opération a réussi, false sinon.
     */
    boolean update(T o);

    /**
     * Supprimer une entité de la base de données (DELETE).
     *
     * @param o L'objet à supprimer.
     * @return true si l'opération a réussi, false sinon.
     */
    boolean delete(T o);

    /**
     * Rechercher une entité par son identifiant unique de type Long (READ by ID).
     *
     * @param id L'identifiant de l'entité.
     * @return L'entité trouvée ou null si inexistante.
     */
    T findById(Long id);

    /**
     * Méthode de commodité pour rechercher par identifiant entier (READ by ID).
     *
     * @param id L'identifiant au format int.
     * @return L'entité trouvée ou null si inexistante.
     */
    default T findById(int id) {
        return findById(Long.valueOf(id));
    }

    /**
     * Récupérer toutes les entités de la table correspondante (READ ALL).
     *
     * @return Liste de toutes les entités.
     */
    List<T> findAll();
}
