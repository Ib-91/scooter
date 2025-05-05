package models;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Magasin {
    public int id_magasin;
    public String nom;

    Vector<Marque> marques = new Vector<Marque>();
    Vector<Scooter> scooters = new Vector<Scooter>();
    Vector<Client> clients = new Vector<Client>();

    /**
     * Default constructor
     */
    public Magasin(int id, String name) {
        id_magasin = id;
        nom = name;
    }
    
    public int getId_magasin() {
        return id_magasin;
    }

    public void setId_magasin(int newid) {
        id_magasin = newid;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String newnom) {
        nom = newnom;
    }

    public Vector<Marque> getMarques() {
        return marques;
    }

    public void setMarques(Vector<Marque> newmarques) {
        marques = newmarques;
    }

    public Vector<Scooter> getScooters() {
        return scooters;
    }

    public void setScooters(Vector<Scooter> newscooters) {
        scooters = newscooters;
    }

    public Vector<Client> getClients() {
        return clients;
    }

    public void setClients(Vector<Client> newclients) {
        clients = newclients;
    }
    public void addMarque(Marque m) {
        marques.add(m);
    }
    public void addScooter(Scooter s) {
        scooters.add(s);
    }
    public void addClient(Client c) {
        clients.add(c);
    }
    public void removeMarque(Marque m) {
        marques.remove(m);
    }
    public void removeScooter(Scooter s) {
        scooters.remove(s);
    }
    public void removeClient(Client c) {
        clients.remove(c);
    }

    //Méthodes
    //Trie
    public Vector<Scooter> ScootersByMarque(Marque m) {
        Vector<Scooter> scootersByMarque = new Vector<Scooter>();
        for (Scooter s : scooters) {
            if (s.getModele().getMarque().equals(m)) {
                            scootersByMarque.add(s);
                        }
        }
        return scootersByMarque;
    }

    public Vector<Scooter> ScootersByModele(Modele m) {
        Vector<Scooter> scootersByModele = new Vector<Scooter>();
        for (Scooter s : scooters) {
            if (s.getModele().equals(m)) {
                scootersByModele.add(s);
            }
        }
        return scootersByModele;
    }


    public Vector<Scooter> Scooterbyprix(double prixMin, double prixMax) {
        Vector<Scooter> scootersByPrix = new Vector<Scooter>();
        if (prixMin > prixMax) {
            double tmp= prixMax;
            prixMax = prixMin;
            prixMin = tmp;
        }
        if (prixMax <= 0) {
            prixMax = Double.MAX_VALUE;
        }

        for (Scooter s : scooters) {
            if (s.getPrix() >= prixMin && s.getPrix() <= prixMax) {
                scootersByPrix.add(s);
            }
        }
        return scootersByPrix;
    }

    public Vector<Scooter> ScootersDispo() {
        Vector<Scooter> disponibles = new Vector<Scooter>();
        for (Scooter s : scooters) {
            if (s.estDispo()) {
                disponibles.add(s);
            }
        }
        return disponibles;
    }

    public void Client_avec_reservation() {
        System.out.println("Clients avec réservation : ");
        for (Client c : clients) {
            if (c.nb_loc() > 0) {
                System.out.println(c.getNom());
            }
        }
    }

    public void locationsEnCours() {
        System.out.println("Locations en cours : ");
        for (Client c : clients) {
            for (Location l : c.getLocations()) {
                if (l.getRetour() == null) {
                    System.out.println(c.getNom() + " loue " + l.getScooter().getModele().getNom_model());
                }
            }
        }
    }

    public void retour_effectue() {
        System.out.println("Retours effectués : ");
        for (Client c : clients) {
            for (Location l : c.getLocations()) {
                if (l.getRetour() != null) {
                    System.out.println(l.getScooter().getModele().getNom_model() + " par " + c.getNom());
                }
            }
        }
    }
    
    //recherche par id
    public Location LocationById(int id) {
        for (Scooter s : scooters) {
            for (Location l : s.getLocations()) {
                if (l.getId_location() == id) {
                    return l;
                }
            }
        }
        return null;
    }

    public Client ClientById(int id) {
        for (Client c : clients) {
            if (c.getId_client() == id) {
                return c;
            }
        }
        return null;
    }
    
    public Scooter ScooterById(int id) {
        for (Scooter s : scooters) {
            if (s.getNum_idt() == id) {
                return s;
            }
        }
        return null;
    }
    public Marque MarqueById(int id) {
        for (Marque m : marques) {
            if (m.getId_marque() == id) {
                return m;
            }
        }
        return null;
    }
    public Modele ModeleById(int id) {
        for (Scooter s : scooters) {
            if (s.getModele().getId_model() == id) {
                return s.getModele();
            }
        }
        return null;
    }
    //
    public Vector<Location> getallLocations() {
        Vector<Location> allLocations = new Vector<Location>();
        for (Scooter s : scooters) {
            for (Location l : s.getLocations()) {
                allLocations.add(l);
            }
        }
        return allLocations;
    }



        //Pour gerer direcetement les locations avec le magasin
        public Location reserve_Scooter(Client client, Scooter scooter, Date debut, Date fin) {
            Location l = new Location(debut, fin, null, client, scooter);
            boolean reserver = l.reserverScooter(debut, fin);
            if (reserver) {
                return l;
            }
            return null;
        }
    
        public void annuler_Reservation(int id_location) {
            Location loc = LocationById(id_location);
            if (loc != null) {
                loc.annulerReservation();
            } else {
                System.out.println("Location non trouvée pour l'ID : " + id_location);
            }
        }
    
        public void demarrer_Location(int id_location) {
            Location loc = LocationById(id_location);
            if (loc != null) {
                loc.loc_start();
            } else {
                System.out.println("Location non trouvée pour l'ID : " + id_location);
            }
        }
    
        public void enregistrerRetour(int id_location, Date dateRetour, int kmParcourus, boolean etatRetour) {
            Location loc = LocationById(id_location);
            if (loc != null) {
                Retour retour = new Retour(kmParcourus, dateRetour, etatRetour, loc.getScooter().getPenalite_pj(), loc);
                retour.retourScooter(loc);
                retour.details_retour();
            } else {
                System.out.println("Location non trouvée pour l'ID : " + id_location);
            }
        }



    public void magasin_details() {
        System.out.println("--------------Détails magasin :------------------ \n");
        System.out.println("Nombre de scooters : " + scooters.size());
        System.out.println("Scooters disponibles : " + ScootersDispo().size());
        System.out.println("Nombre de clients : " + clients.size());
        System.out.println("Nombre de marques : " + marques.size());
        System.out.println("Locations effectuées : " + getallLocations().size());
        locationsEnCours();
        retour_effectue();
    }

}