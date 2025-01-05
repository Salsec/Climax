package com.gs.climaxrepoport.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@XmlRootElement
public class Client {

    private String nom;
    private String prenom;
    private int age;
    private String profession;
    private double salaire;

    @JsonCreator
    public Client(@JsonProperty("name") String nom, @JsonProperty("prenom") String prenom, @JsonProperty("age") int age, @JsonProperty("profession") String profession, @JsonProperty("salaire") double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.profession = profession;
        this.salaire = salaire;
    }

    @XmlElement
    public String getNom() {
        return nom;
    }

    @XmlElement
    public String getPrenom() {
        return prenom;
    }

    @XmlElement
    public int getAge() {
        return age;
    }

    @XmlElement
    public String getProfession() {
        return profession;
    }

    @XmlElement
    public double getSalaire() {
        return salaire;
    }

    @Override
    public String toString() {
        return nom + " " + prenom + ", " + age + " ans, " + profession + ", " + salaire + "K€";
    }

}