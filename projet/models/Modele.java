package models;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Modele {

    public int id_model;
    public String nom_model;
    
    Vector<Scooter> scooters = new Vector<Scooter>();
    Marque marque;
    Vector<Permis> permis = new Vector<Permis>();
    
    /**
     * Default constructor
     */
    public Modele(int id, String name, Marque m) {
        id_model = id;
        nom_model = name;
        marque=m;
    }

    public int getId_model() {
        return id_model;
    }

    public void setId_model(int newid) {
        id_model = newid;
    }

    public String getNom_model() {
        return nom_model;
    }

    public void setNom_model(String newnom) {
        nom_model = newnom;
    }

    public Vector<Scooter> getScooters() {
        return scooters;
    }

    public void setScooters(Vector<Scooter> newscooters) {
        scooters = newscooters;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque newmarque) {
        marque = newmarque;
    }

    public Vector<Permis> getPermis() {
        return permis;
    }

    public void setPermis(Vector<Permis> newpermis) {
        permis = newpermis;
    }

    public void addScooter(Scooter s) {
        scooters.add(s);
    }

    public void addPermis(Permis p) {
        permis.add(p);
    }
    
    public void removeScooter(Scooter s) {
        scooters.remove(s);
        s.setModele(this);//Enlever au scooter le modèle
    }
    public void removePermis(Permis p) {
        permis.remove(p);
        p.removeModele(this);//Enlever le permis du modèle
    }

    public void permis_requis() {
        System.out.println("Permis requis pour conduire ce modèle :");
        for (Permis p : permis) {
            System.out.println("- " + p.getNom_permis());
        }
    }

    public int modele_dispo() {
        int dispoCount = 0;
        for (Scooter s : scooters) {
            if (s.estDispo()) {
                dispoCount++;
            }
        }
        return dispoCount;
    }

    public void details_modele() {
        System.out.println("--------------Détails Modele :------------------ \n");
        System.out.println("ID : " + id_model);
        System.out.println("Nom : " + nom_model);
        System.out.println("Marque : " + marque.getNom_marque());
        permis_requis();
        System.out.println("Nombre de scooters disponibles : " + modele_dispo());
        }
    
}