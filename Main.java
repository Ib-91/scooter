package models;

import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
        //Méthode

        //Retour du scooter par le client
        public static Retour retour_user(Location loc) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Retour du scooter : " + loc.getScooter().getModele().getNom_model());
        System.out.println("Entrez les kilomètres parcourus : ");
        int km = scanner.nextInt();
        
        System.out.println("Le scooter est-il endommagé ? (true/false) : ");
        boolean etat = scanner.nextBoolean();
        
        int penaliteParJour = loc.getScooter().getPenalite_pj();

        Retour retour = new Retour(km, new Date(), etat, penaliteParJour, loc);
        retour.details_retour();
        retour.retourScooter(loc);
        System.out.println("--------------------------------------------------");
        return retour;
}

    public static Location location_user(Scooter s, Client c) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Réservation du scooter : " + s.getModele().getNom_model());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebut = null;
        Date dateFin = null;
        Date now = new Date();
        boolean dateValide = true;
        
        do{
        
            try {
        System.out.println("Entrez la date de début de location (jj/mm/aaaa) : ");
        String dateDebutStr = scanner.nextLine();
        
        dateDebut = sdf.parse(dateDebutStr);

        System.out.println("Entrez la date de fin de location (jj/mm/aaaa) : ");
        String dateFinStr = scanner.nextLine();
        dateFin = sdf.parse(dateFinStr);
        
        if (dateFin.before(now)) {
            System.out.println("La date de fin doit être dans le futur.");
            dateValide = false;
        } else if (dateDebut.after(dateFin)) {
            System.out.println("La date de début doit être avant la date de fin.");
            dateValide = false;
        } else if(dateDebut.before(now)) {
            dateValide = false;
            System.out.println("La date de début se fait à partir de maintenant.");
        } else if(!dateValide) {
            System.out.println("Entrée invalide. Veuillez entrer une date valide.");}
        else{
            dateValide = true;
        }
        } catch (Exception e) {
        System.out.println("Erreur de format. Assurez-vous d'utiliser le format jj/mm/aaaa.");
        dateValide = false;
        }

        }while(!dateValide);


        if (s.dispo_entre(dateDebut, dateFin)) {
            Location loc = new Location(dateDebut, dateFin, null, c, s);
            loc.reserverScooter(dateDebut, dateFin);
            System.out.println("--------------------------------------------------");
            loc.details_location();
            return loc;
        } else {
            System.out.println("Le scooter n'est pas disponible à cette date.");
            System.out.println("--------------------------------------------------");
            return null;
        }
}

    public static Client nv_client() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Création d'un nouveau client");
        System.out.print("Entrez le nom du client : ");
        String nom = scanner.nextLine();

        int id = (int)(Math.random() * 1000); //id au hasard sur 100
        Client nouveauClient = new Client(id, nom);
        System.out.println("Client créé avec succès ! ID: " + nouveauClient.getId_client() + ", Nom: " + nouveauClient.getNom());
        
        return nouveauClient;
}





public static void main(String[] args) {
        

        Marque marque1= new Marque(1, "Kawasaki");
        Marque marque2= new Marque(2, "Yamaha");
        
        Modele modele1 = new Modele(1, "ZII", marque1);
        Modele modele2 = new Modele(2, "MT-O7", marque2);
        
        Permis permisA= new Permis(1, "A");
        Permis permisB= new Permis(2, "B");

        Magasin m1 = new Magasin(1, "LOCATION2SCOOT");
        Magasin m2= new Magasin(2, "LOC2FOU");

        Scooter s1 = new Scooter(1, true, 100, m1, modele1);
        Scooter s2 = new Scooter(2, false, 200, m2, modele2, 30);

        Client c1 = new Client(1, "TESTEUR2LOC");
        Client c2= new Client(2, "CASSEUR2LOC");
        //Client c1= nv_client();
        //Client c2= nv_client();

        s1.setDispo(false);

        //Ajout d'un client dans un magasin
        m1.addClient(c1);
        m2.addClient(c2);

        //Ajout d'un permis à un client
        c1.addPermis(permisA);
        c2.addPermis(permisB);

        permisA.addModele(modele1);
        permisB.addModele(modele2);

        m1.addScooter(s2);
        m2.addScooter(s1);

        Location l2=location_user(s2, c2);
        Location l1=location_user(s1, c1);
        Vector <Client> allClients = new Vector<Client>();
        allClients.add(c1);
        allClients.add(c2);
        c1.addLocation(l1);
        c2.addLocation(l2);

        //System.out.println("TEST D'UNE LOCATION");
        //retour
        //retour_user(l1);
        retour_user(l2);
        
}
}