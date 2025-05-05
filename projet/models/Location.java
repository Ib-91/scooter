package models;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 */
public class Location {

    public static int nextId = 1; // Variable pour que l'ID s'auto-incrémenté
    
    public int id_location;
    public Date date_debut;
    public Date date_fin;

    Retour retour;
    Client client;
    Scooter scooter;    
    /**
     * Default constructor
     */
    public Location(int id, Date d1, Date d2, Retour r, Client c, Scooter s) {
        id_location = id;
        date_debut = d1;
        date_fin = d2;
        retour= r;
        client= c;
        scooter = s;
    }

    public Location(Date d1, Date d2, Retour r, Client c, Scooter s) {
        id_location = nextId++;
        date_debut = d1;
        date_fin = d2;
        retour= r;
        client= c;
        scooter = s;
    }

    //Getters et Setters
    public int getId_location() {
        return id_location;
    }
    public void setId_location(int newid) {
        id_location = newid;
    }
    public Date getDate_debut() {
        return date_debut;
    }
    public void setDate_debut(Date newdate_debut) {
        date_debut = newdate_debut;
    }
    public Date getDate_fin() {
        return date_fin;
    }
    public void setDate_fin(Date newdate_fin) {
        date_fin = newdate_fin;
    }
    public Retour getRetour() {
        return retour;
    }
    public void setRetour(Retour newretour) {
        this.retour = newretour;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client newclient) {
        client = newclient;
    }
    public Scooter getScooter() {
        return scooter;
    }
    public void setScooter(Scooter newscooter) {
        scooter = newscooter;
    }

    //Méthodes
    public boolean reserverScooter(Date debut, Date fin) {
        if (scooter.dispo_entre(debut, fin)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date_debut = debut;
            date_fin = fin;
            scooter.addLocation(this);
            client.addLocation(this);
            System.out.println("Scooter "+ scooter.getModele().getNom_model() + " réservé pour le client " + client.getNom() + " du " + sdf.format(date_debut) + " au " + sdf.format(date_fin));
            System.out.println("Le retour du scooter est prévu pour le " + sdf.format(date_fin));
            return true;
        }else {
            System.out.println("Le scooter n'est pas disponible à cette date.");
            return false;
        }
    }
/* SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
27/04/2025 au 30/04/2025 au lieu de Sun Apr 27 00:00:00 UTC 2025.
*/
    public void annulerReservation() {
        Date now= new Date();
        if(now.before(date_debut)){//Vérifie si la date actuelle est avant la date de début
            System.out.println("Réservation annulée pour le client " + client.getNom());
            scooter.setDispo(true);
        } else {
        System.out.println("Impossible d'annuler la réservation, la location a déjà commencé.");
    }
    }

    public void loc_start() {
        Date now= new Date();
        if (now.before(date_debut)) {
            System.out.println("La location n'a pas encore commencé.");
        } else if (now.after(date_fin)){
            System.out.println("La location est déjà terminée.");
        } else {
            if (scooter.estDispo()) {
            scooter.setDispo(false);
            System.out.println("La location a commencé pour le client " + client.getNom());
            }else{
            System.out.println("Le scooter n'est pas disponible pour la location.");
            }
        }
    }

    public void details_location() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");        System.out.println("--------------Détails de la location :------------------ \n");
        System.out.println("ID Location: " + id_location);
        System.out.println("Client: " + client.getNom());
        System.out.println("Date de début : " + sdf.format(date_debut));
        System.out.println("Date de retour prévu : " + sdf.format(date_fin));
        System.out.println("Scooter: " + scooter.getModele().getNom_model());
        System.out.println("--------------------------------------------------");
    }
}