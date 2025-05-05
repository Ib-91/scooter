package models;

import java.io.*;
import java.util.*;


public class Marque {
    public int id_marque;
    public String nom_marque;
    
    Vector<Modele> modeles = new Vector<Modele>();
    Vector<Magasin> magasins = new Vector<Magasin>();
 
    /**
     * Default constructor
     */
    // Removed duplicate constructor

    public Marque(int id, String name) {
        id_marque = id;
        nom_marque = name;
    }

    public int getId_marque() {
        return id_marque;
    }

    public void setId_marque(int newid) {
        id_marque = newid;
    }

    public String getNom_marque() {
        return nom_marque;
    }

    public void setNom_marque(String newnom) {
        nom_marque = newnom;
    }

    public Vector<Modele> getModeles() {
        return modeles;
    }

    public void setModeles(Vector<Modele> newmodeles) {
        modeles = newmodeles;
    }

    public Vector<Magasin> getMagasins() {
        return magasins;
    }

    public void setMagasins(Vector<Magasin> newmagasins) {
        magasins = newmagasins;
    }
    
    public void addModele(Modele m) {
        modeles.add(m);
    }

    public void addMagasin(Magasin m) {
        magasins.add(m);
    }

    public void removeModele(Modele m) {
        modeles.remove(m);
    }

    public void removeMagasin(Magasin m) {
        magasins.remove(m);
    }

    public int Nb_Modeles() {
        return modeles.size();
    }

    public void allmodeles() {
        System.out.println("Modeles disponibles: ");
        for (Modele m : modeles) {
            System.out.println("-"+m.getNom_model());
        }
    }

    public void allmagasins() {
        System.out.println("Disponible dans les magasins suivants: ");
        for (Magasin m : magasins) {
            System.out.println("-"+m.getNom());
        }
    }

    public Vector <Modele> modelebypermis(Permis p) {
        Vector<Modele> modelesbypermis = new Vector<Modele>();
        for (Modele m : modeles) {
            if (m.getPermis().contains(p)) {
                modelesbypermis.add(m);
            }
        }
        return modelesbypermis;
    }
    

    public void details_marque() {
        System.out.println("--------------Détails de marque :------------------ \n");
        System.out.println("Marque: " + nom_marque);
        System.out.println("ID: " + id_marque);
        System.out.println("Nombre de modeles: " + Nb_Modeles());
        allmodeles();
        allmagasins();

    }


    
}