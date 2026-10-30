package com.tp;

import com.tp.model.Salle;
import com.tp.model.Utilisateur;
import com.tp.service.SalleService;
import com.tp.service.UserService;
import com.tp.util.HibernateUtil;

import java.util.List;

/**
 * Application principale démontrant l'exécution complète du :
 * TP 2 : Gestion des Entités Salle et Utilisateur avec JPA et Hibernate.
 */
public class Main {

    public static void main(String[] args) {
        afficherBanniere();

        SalleService salleService = new SalleService();
        UserService userService = new UserService();

        // ====================================================================
        // ÉTAPE 1 : CRÉATION ET PERSISTANCE DES SALLES (CREATE)
        // ====================================================================
        System.out.println("\n===============================================================================");
        System.out.println(">>> ÉTAPE 1 : Insertion des entités Salle en base de données H2 (CREATE)");
        System.out.println("===============================================================================");

        Salle s1 = new Salle("S-101", "Amphithéâtre Ibn Battuta", 150, "Rez-de-chaussée");
        Salle s2 = new Salle("LAB-201", "Laboratoire Intelligence Artificielle", 35, "1er Étage");
        Salle s3 = new Salle("CONF-301", "Salle de Conférence Atlas", 60, "2ème Étage");
        Salle s4 = new Salle("SEM-401", "Salle de Séminaire Toubkal", 25, "3ème Étage");

        salleService.create(s1);
        salleService.create(s2);
        salleService.create(s3);
        salleService.create(s4);

        // ====================================================================
        // ÉTAPE 2 : CRÉATION ET PERSISTANCE DES UTILISATEURS (CREATE)
        // ====================================================================
        System.out.println("\n===============================================================================");
        System.out.println(">>> ÉTAPE 2 : Insertion des entités Utilisateur en base de données H2 (CREATE)");
        System.out.println("===============================================================================");

        Utilisateur u1 = new Utilisateur("Rafik", "Hamza", "hamza.rafik@example.com", "+212 600-112233", "Étudiant Ingénieur");
        Utilisateur u2 = new Utilisateur("Ait Said", "Oumaima", "oumaima.aitsaid@example.com", "+212 611-445566", "Ingénieur Manager & Instructrice");
        Utilisateur u3 = new Utilisateur("Bennani", "Karim", "karim.bennani@example.com", "+212 622-778899", "Administrateur Système");
        Utilisateur u4 = new Utilisateur("Alami", "Sofia", "sofia.alami@example.com", "+212 633-990011", "Chercheuse Data Science");

        userService.create(u1);
        userService.create(u2);
        userService.create(u3);
        userService.create(u4);

        // ====================================================================
        // ÉTAPE 3 : RÉCUPÉRATION ET AFFICHAGE DES SALLES (READ ALL)
        // ====================================================================
        System.out.println("\n===============================================================================");
        System.out.println(">>> ÉTAPE 3 : Liste de toutes les Salles (Requête HQL : from Salle)");
        System.out.println("===============================================================================");

        List<Salle> salles = salleService.findAll();
        afficherTableauSalles(salles);

        // ====================================================================
        // ÉTAPE 4 : RÉCUPÉRATION ET AFFICHAGE DES UTILISATEURS (READ ALL)
        // ====================================================================
        System.out.println("\n===============================================================================");
        System.out.println(">>> ÉTAPE 4 : Liste de tous les Utilisateurs (Requête HQL : from Utilisateur)");
        System.out.println("===============================================================================");

        List<Utilisateur> utilisateurs = userService.findAll();
        afficherTableauUtilisateurs(utilisateurs);

        // ====================================================================
        // ÉTAPE 5 : RECHERCHE PAR IDENTIFIANT ET CRITÈRE (READ by ID / Unique)
        // ====================================================================
        System.out.println("\n===============================================================================");
        System.out.println(">>> ÉTAPE 5 : Recherches spécifiques par ID et attributs uniques");
        System.out.println("===============================================================================");

        Salle salleTrouvee = salleService.findById(2L);
        System.out.println("  [Recherche Salle ID=2] -> " + salleTrouvee);

        Salle salleParCode = salleService.findByCode("CONF-301");
        System.out.println("  [Recherche Salle Code='CONF-301'] -> " + salleParCode);

        Utilisateur userTrouve = userService.findById(1L);
        System.out.println("  [Recherche Utilisateur ID=1] -> " + userTrouve);

        Utilisateur userParEmail = userService.findByEmail("oumaima.aitsaid@example.com");
        System.out.println("  [Recherche Utilisateur Email='oumaima.aitsaid@example.com'] -> " + userParEmail);

        // ====================================================================
        // ÉTAPE 6 : MISE À JOUR D'ENTITÉS (UPDATE)
        // ====================================================================
        System.out.println("\n===============================================================================");
        System.out.println(">>> ÉTAPE 6 : Modification et mise à jour d'entités (UPDATE)");
        System.out.println("===============================================================================");

        if (salleTrouvee != null) {
            System.out.println("  Avant modification : " + salleTrouvee);
            salleTrouvee.setLibelle("Laboratoire IA & Deep Learning Avancé");
            salleTrouvee.setCapacite(45);
            salleService.update(salleTrouvee);
            System.out.println("  Après modification  : " + salleService.findById(2L));
        }

        if (userTrouve != null) {
            System.out.println("\n  Avant modification : " + userTrouve);
            userTrouve.setTelephone("+212 600-999888");
            userTrouve.setRole("Lead Software Engineer & Cloud Architect");
            userService.update(userTrouve);
            System.out.println("  Après modification  : " + userService.findById(1L));
        }

        // ====================================================================
        // ÉTAPE 7 : SUPPRESSION D'ENTITÉS (DELETE)
        // ====================================================================
        System.out.println("\n===============================================================================");
        System.out.println(">>> ÉTAPE 7 : Suppression d'une salle et d'un utilisateur (DELETE)");
        System.out.println("===============================================================================");

        Salle salleASupprimer = salleService.findById(4L);
        if (salleASupprimer != null) {
            System.out.println("  Suppression de la salle : " + salleASupprimer.getLibelle());
            salleService.delete(salleASupprimer);
        }

        Utilisateur userASupprimer = userService.findById(3L);
        if (userASupprimer != null) {
            System.out.println("  Suppression de l'utilisateur : " + userASupprimer.getPrenom() + " " + userASupprimer.getNom());
            userService.delete(userASupprimer);
        }

        // ====================================================================
        // ÉTAPE 8 : ÉTAT FINAL DE LA BASE DE DONNÉES
        // ====================================================================
        System.out.println("\n===============================================================================");
        System.out.println(">>> ÉTAPE 8 : État final de la base de données H2 après opérations CRUD");
        System.out.println("===============================================================================");

        System.out.println("\n-- Salles restantes en base de données --");
        afficherTableauSalles(salleService.findAll());

        System.out.println("\n-- Utilisateurs restants en base de données --");
        afficherTableauUtilisateurs(userService.findAll());

        System.out.println("\n===============================================================================");
        System.out.println(" [SUCCÈS] Exécution du TP 2 terminée avec succès sans aucune erreur !");
        System.out.println("===============================================================================");

        // Clôture propre des ressources Hibernate
        HibernateUtil.shutdown();
    }

    private static void afficherBanniere() {
        System.out.println("*******************************************************************************");
        System.out.println("*                                                                             *");
        System.out.println("*   TP 2 : Gestion des Entités Salle et Utilisateur avec JPA et Hibernate     *");
        System.out.println("*   Technologies : Hibernate 6.4 ORM | Jakarta Persistence | H2 Database      *");
        System.out.println("*   Architecture : Modèle - DAO Générique IDao<T> - Services Métier           *");
        System.out.println("*                                                                             *");
        System.out.println("*******************************************************************************");
    }

    private static void afficherTableauSalles(List<Salle> liste) {
        String format = "  | %-4s | %-10s | %-42s | %-10s | %-16s |%n";
        String separateur = "  +------+------------+--------------------------------------------+------------+------------------+";

        System.out.println(separateur);
        System.out.printf(format, "ID", "Code", "Libellé de la Salle", "Capacité", "Étage");
        System.out.println(separateur);

        for (Salle s : liste) {
            System.out.printf(format, s.getId(), s.getCode(), s.getLibelle(), s.getCapacite() + " p.", s.getEtage());
        }

        System.out.println(separateur);
        System.out.printf("  Total : %d salle(s) répertoriée(s).%n", liste.size());
    }

    private static void afficherTableauUtilisateurs(List<Utilisateur> liste) {
        String format = "  | %-4s | %-20s | %-32s | %-16s | %-38s |%n";
        String separateur = "  +------+----------------------+----------------------------------+------------------+----------------------------------------+";

        System.out.println(separateur);
        System.out.printf(format, "ID", "Nom & Prénom", "Email", "Téléphone", "Rôle / Fonction");
        System.out.println(separateur);

        for (Utilisateur u : liste) {
            System.out.printf(format, u.getId(), u.getPrenom() + " " + u.getNom(), u.getEmail(), u.getTelephone(), u.getRole());
        }

        System.out.println(separateur);
        System.out.printf("  Total : %d utilisateur(s) répertorié(s).%n", liste.size());
    }
}
