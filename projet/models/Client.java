package models;

import java.io.*;
import java.util.*;


/**
 * 
 */
public class Client {

    public int id_client;
    public String nom;

    Vector<Magasin> magasins = new Vector<Magasin>();
    Vector<Location> locations = new Vector<Location>();
    Vector<Permis> permis = new Vector<Permis>();
    
    /**
     * Default constructor
     */
    public Client(int id, String name) {
        id_client = id;
        nom = name;
    }

    
    public int getId_client() {
        return id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setId_client(int newid) {
        id_client = newid;
    }

    public void setNom(String newnom) {
        nom = newnom;
    }

    public Vector<Magasin> getMagasins() {
        return magasins;
    }

    public void setMagasins(Vector<Magasin> newmag) {
        magasins = newmag;
    }

    public Vector<Location> getLocations() {
        return locations;
    }

    public void setLocations(Vector<Location> newloc) {
        locations = newloc;
    }

    public Vector<Permis> getPermis() {
        return permis;
    }

    public void setPermis(Vector<Permis> newpermis) {
        permis = newpermis;
    }
    
    public void addMagasin(Magasin mag) {
        magasins.add(mag);
    }
    public void addLocation(Location loc) {
        locations.add(loc);
    }
    public void addPermis(Permis per) {
        permis.add(per);
    }
    public void removeMagasin(Magasin mag) {
        magasins.remove(mag);
    }
    public void removeLocation(Location loc) {
        locations.remove(loc);
    }
    public void removePermis(Permis per) {
        permis.remove(per);
    }

    public void affichemagasin() {
        System.out.println("Magasins : ");
        for (int i = 0; i < magasins.size(); i++) {
            System.out.println(magasins.get(i).getNom());
        }
    }

    public void permis_client() {
        System.out.println("Permis détunus : ");
        for (Permis p: permis) { //Parcourt les permis du client
            System.out.println("-"+p.getNom_permis());
        }
    }

    public int nb_loc(){
        return locations.size();
    }

    public void historique_loc() {
        System.out.println("Historique des locations, vous en avez fait"+ nb_loc() + "\n");
        for (Location l : locations) { //Parcourt les locations
            System.out.println("Location : " + l.getScooter().getModele().getNom_model() + " du " + l.getDate_debut() + " au " + l.getDate_fin() + "Id :" + l.getId_location() + "\n");
            }
    }

    public boolean peutConduire(Modele modele) {
        for (Permis p : permis) {
            for (Modele m : p.getModeles()) {
                if (m.equals(modele)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int km_client() {
        int km = 0;
        for (Location loc : locations) { //Parcourt les locations du client
            km += loc.getRetour().getKm_parcourut(); //Récupère le kilométrage total du scooter
        }
        return km;

    }
    
    public void details_client() {
        System.out.println("--------------Détails du client :------------------ \n");
        System.out.println("ID Client: " + id_client);
        System.out.println("Nom: " + nom);
        System.out.println("Magasins : ");
        affichemagasin();
        permis_client();
        System.out.println("Nombre de locations : " + nb_loc());
        historique_loc();        
        System.out.println("Kilomètres parcourus: " + km_client());
    }
    
}