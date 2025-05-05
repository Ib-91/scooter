package models;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 */
public class Retour {

    public int id_retour;
    public int km_parcourut;

    public Date date_retour;
    public boolean Etat_retour;//endommage ou pas
    public int penalite;//penalite par jour de retards
    Location location;
    /**
     * Default constructor
     */
    public Retour(int km, Date date, boolean etat, int pen, Location loc) {
        id_retour = loc.getId_location();
        km_parcourut = km;
        date_retour = date;
        Etat_retour = etat;
        penalite = loc.getScooter().getPenalite_pj();  
        location= loc;
    }
    
    //getter et setter
    public int getId_retour() {
        return id_retour;
    }
    public void setId_retour(int newid) {
        id_retour = newid;
    }
    public int getKm_parcourut() {
        return km_parcourut;
    }
    public void setKm_parcourut(int newkm) {
        km_parcourut = newkm;
    }
    public Date getDate_retour() {
        return date_retour;
    }
    public void setDate_retour(Date newdate_retour) {
        date_retour = newdate_retour;
    }
    public boolean isEtat_retour() {
        return Etat_retour;
    }
    public void setEtat_retour(boolean newetat) {
        Etat_retour = newetat;
    }
    public int getpenalite() {
        return penalite;
    }
    public void setpenalite(int newpenalite) {
        penalite = newpenalite;
    }
    
    public Location getLocation() {
    return location;
    }
    public void setLocation(Location newlocation) {
        location = newlocation;
    }

    //Méthodes
    public boolean estEnRetard() {
        Date dateLimite = location.getDate_fin();
        Date dateRetour = date_retour;
        if (dateRetour.after(dateLimite)) {
            return true;
        }
        return false;
    }

    public int calculerPenalite() {
        int pt = 0;
        if (estEnRetard()) {
            long diff = date_retour.getTime() - location.getDate_fin().getTime();//gestion de date à revoir
            long diffDays = diff / (24 * 60 * 60 * 1000);
            pt = (int) (diffDays * penalite);
        }
        if (Etat_retour) {
            pt += 200;
        }
            return pt;
        }   

        //verifier que le scooter est bien reserve avant de le retourner

        public boolean verifierEtat() {
            if (Etat_retour) {
                return true;//le scooter est endommage
            } else {
                return false;
            }
        }

        public boolean verifierReservation() {
            if (!location.getScooter().estDispo()) {
                return true;//le scooter est reservé
            } else {
                return false;
            }
        }

        public void retourScooter(Location loc) {
            if (!verifierReservation()) {
                System.out.println("Le scooter n'est pas réservé.");
                return;
            }
            loc.setRetour(this);
            loc.getScooter().setDispo(true);
            System.out.println("Le scooter a été retourné avec succès.");
        }


        public void details_retour() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            System.out.println("--------------Détails du retour :------------------ \n");
            System.out.println("Retour du scooter :"+ location.getScooter().getModele().getNom_model());
            System.out.println("ID Location: " + location.getId_location());
            System.out.println("ID Retour: " + id_retour);
            System.out.println("Client: " + location.getClient().getNom());
            System.out.println("Date de location " + sdf.format(location.getDate_debut()));
            System.out.println("Date de retour prévue: " + sdf.format(location.getDate_fin()));
            System.out.println("Pénalité retards/j: " + penalite +"€" );
            System.out.println("Kilomètres parcourus: " + km_parcourut);
            System.out.println("Date de retour: " + sdf.format(date_retour));
            System.out.println("Scooter endommagé ? " + verifierEtat());
            System.out.println("Penalité totale à payer: " + calculerPenalite() + "€");
        }

    }