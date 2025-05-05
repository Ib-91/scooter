package models;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Scooter {

    Vector<Location> locations = new Vector<Location>();
    Magasin magasin;
    Modele modele;
    public int num_idt;
    public boolean dispo;
    public int prix;
    public int penalite_pj;//penalite par jour

    /**
     * Default constructor
     */
    public Scooter(int id, boolean d, int p, Magasin m, Modele mod, int pen) {
        num_idt = id;
        dispo = d;
        prix = p;
        magasin = m;
        modele = mod;
        penalite_pj = pen;
    }
    public Scooter(int id, boolean d, int p, Magasin m, Modele mod) {
        num_idt = id;
        dispo = d;
        prix = p;
        magasin = m;
        modele = mod;
        penalite_pj = 20;
    }

    public Vector<Location> getLocations() {
        return locations;
    }

    public void setLocations(Vector<Location> newloc) {
        locations = newloc;
    }


    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin newmag) {
        magasin = newmag;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele newmod) {
        modele = newmod;
    }

    public int getNum_idt() {
        return num_idt;
    }

    public void setNum_idt(int newnum_idt) {
        num_idt = newnum_idt;
    }
    public int getPenalite_pj() {
        return penalite_pj;
    }
    public void setPenalite_pj(int newpenalite) {
        penalite_pj = newpenalite;
    }

    public boolean estDispo() {
        return dispo;
    }

    public void setDispo(boolean newdispo) {
        dispo = newdispo;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int newprix) {
        prix = newprix;
    }
    public void addLocation(Location l) {
        locations.add(l);
    }
    public void removeLocation(Location l) {
        locations.remove(l);
    }

    public int nb_loc() {
        return locations.size();
    }
    public void historique_loc() {
        System.out.println("Historique des locations, du scooter " + getNum_idt() + "\n");
        for (Location l : locations) {
            System.out.println("Location : " + l.getScooter().getModele().getNom_model() + " du " + l.getDate_debut() + " au " + l.getDate_fin() + "Id :" + l.getId_location() + "\n");
        }
    }

    public Vector<Client> ancien_Clients() {
        Vector<Client> historique = new Vector<Client>();
        for (Location loc : getLocations()) { //Parcourt les locations du scooter
            historique.add(loc.getClient());
        }
        return historique;
    }

    public int getkm() {
        int km = 0;
        for (Location loc : locations) {
            Retour retour = loc.getRetour();
            if (retour != null) {
                km += retour.getKm_parcourut();
            }
        }
        return km;
    }

    public boolean dispo_entre(Date debut, Date fin) {
        for (Location loc : locations) {
            Date locDebut = loc.getDate_debut();
            Date locFin = loc.getDate_fin();

            if (fin.before(locDebut) || debut.after(locFin)){
                //on passe à la suite
            } else {
                // Chevauchement de dates
                return false; // Indique que le scooter n'est pas disponible
            }
        }
        return true;
    }

    public String dernierEtat() {
        Retour dernierRetour = null;
        for (Location loc : locations) {
            if (loc.getRetour() != null) {
                dernierRetour = loc.getRetour();
            }
        }
        if (dernierRetour == null) {
            return "Neuf";
        }
        return dernierRetour.verifierEtat() ? "Bon état" : "Endommagé";
    }
    
    
    public void details_scooter() {
        System.out.println("-----------------Détails du scooter : ------------------");
        System.out.println("ID : " + num_idt);
        System.out.println("Marque : " + modele.getMarque());
        System.out.println("Modèle : " + modele.getNom_model());
        System.out.println("Magasin : " + magasin.getNom());
        System.out.println("Kilométrage : " + getkm());
        System.out.println("Prix : " + prix);
        System.out.println("État : " + dernierEtat());
        System.out.println("Pénalité par jour : " + penalite_pj);
        System.out.println("Disponible : " + (dispo ? "Oui" : "Non"));
        System.out.println("Scooter loué"+ nb_loc() +"fois");
        System.out.println("Ancien clients : ");
        for (Client c : ancien_Clients()) {
            System.out.println("- " + c.getNom());
        }
    }

}