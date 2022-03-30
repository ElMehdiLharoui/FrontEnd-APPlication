package com.example.etudiant;

public class Etudiant {
    private static int id;
    private int compt;
    private int etu_id;
    private String nom;
    private String prenom;
    private String age;



    public Etudiant(String nom, String prenom, String age) {
        this.etu_id = compt++;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;


    }



    public int getEtu_id() {
        return etu_id;
    }

    public void setEtu_id(int etu_id) {
        this.etu_id = etu_id;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

