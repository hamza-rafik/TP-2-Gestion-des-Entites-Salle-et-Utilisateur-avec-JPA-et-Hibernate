package com.tp.model;

import jakarta.persistence.*;

/**
 * Entité JPA représentant une Salle.
 * Mappée sur la table 'salles' en base de données relationnelle.
 */
@Entity
@Table(name = "salles")
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true, length = 30)
    private String code;

    @Column(name = "libelle", nullable = false, length = 100)
    private String libelle;

    @Column(name = "capacite", nullable = false)
    private int capacite;

    @Column(name = "etage", length = 30)
    private String etage;

    // Constructeur sans argument (obligatoire selon la spécification JPA/Hibernate)
    public Salle() {
    }

    // Constructeur avec paramètres sans identifiant
    public Salle(String code, String libelle, int capacite, String etage) {
        this.code = code;
        this.libelle = libelle;
        this.capacite = capacite;
        this.etage = etage;
    }

    // Constructeur complet avec identifiant
    public Salle(Long id, String code, String libelle, int capacite, String etage) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.capacite = capacite;
        this.etage = etage;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    @Override
    public String toString() {
        return String.format("Salle[ID=%d, Code='%s', Libelle='%s', Capacité=%d places, Étage='%s']",
                id, code, libelle, capacite, etage);
    }
}
