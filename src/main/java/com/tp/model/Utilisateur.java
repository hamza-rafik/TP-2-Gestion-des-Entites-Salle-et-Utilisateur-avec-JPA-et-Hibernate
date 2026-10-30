package com.tp.model;

import jakarta.persistence.*;

/**
 * Entité JPA représentant un Utilisateur.
 * Mappée sur la table 'utilisateurs' en base de données relationnelle.
 */
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "telephone", length = 30)
    private String telephone;

    @Column(name = "role", length = 50)
    private String role;

    // Constructeur sans argument (requis par la spécification JPA/Hibernate)
    public Utilisateur() {
    }

    // Constructeur avec paramètres sans identifiant
    public Utilisateur(String nom, String prenom, String email, String telephone, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
    }

    // Constructeur complet avec identifiant
    public Utilisateur(Long id, String nom, String prenom, String email, String telephone, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("Utilisateur[ID=%d, Nom='%s %s', Email='%s', Téléphone='%s', Rôle='%s']",
                id, prenom, nom, email, telephone, role);
    }
}
