package models;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Permis {

    public int Type_permis;
    public String nom_permis;
    Vector<Client> clients = new Vector<Client>();
    Vector<Modele> modeles = new Vector<Modele>();

    /**
     * Default constructor
     */
    public Permis(int id, String name) {
        Type_permis = id;
        nom_permis = name;
    }

    public int getType_permis() {
        return Type_permis;
    }

    public void setType_permis(int newtype_permis) {
        Type_permis = newtype_permis;
    }

    public String getNom_permis() {
        return nom_permis;
    }

    public void setNom_permis(String newnom_permis) {
        nom_permis = newnom_permis;
    }

    public Vector<Client> getClients() {
        return clients;
    }

    public void setClients(Vector<Client> newclients) {
        clients = newclients;
    }

    public Vector<Modele> getModeles() {
        return modeles;
    }

    public void setModeles(Vector<Modele> newmodeles) {
        modeles = newmodeles;
    }

    public void addClient(Client c) {
        clients.add(c);
    }
    public void addModele(Modele m) {
        modeles.add(m);
        m.addPermis(this);//Ajouter au modele le permis
    }
    public void removeClient(Client c) {
        clients.remove(c);
    }
    public void removeModele(Modele m) {
        modeles.remove(m);
        m.removePermis(this);//Enlever au modele le permis
    }

    public boolean permis_modele(Modele m){
        for (Modele mod : modeles) {//parcourir la liste des modeles
            if (mod.getId_model() == m.getId_model()) {
                return true;
            }
        }
        return false;
    }

    public void permis_details() {
        System.out.println("--------------Détails du permis :------------------ \n");
        System.out.println("Type de permis: " + Type_permis);
        System.out.println("Nom du permis: " + nom_permis);
        System.out.println("Modeles de scooter necessitant ce permis: ");
        for (Modele m : modeles) {
            System.out.println(m.getNom_model());
        }
    }

}