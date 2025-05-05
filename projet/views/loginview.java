package views;

import models.Client;
import models.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class loginview extends JFrame {

    private JButton connexion;
    private JButton inscription;
    private JButton retour;
    private Vector<Client> clients; // Liste des clients

    public loginview(Vector<Client> clients) {
        this.clients = clients; 
        setTitle("Connexion - Loc2Scoot");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    public void initUI() {
        connexion = new JButton("Se connecter");
        inscription = new JButton("S'inscrire");
        retour = new JButton("Retour");
        // appel à la méthode de client_user (à relier au contrôleur)
    connexion.addActionListener(e -> {
    String input = JOptionPane.showInputDialog(this, "Entrez votre ID ou nom :");

    Client clientTrouve = null;
    for (Client c : clients) {
        if (String.valueOf(c.getId_client()).equals(input) || c.getNom().equalsIgnoreCase(input)) {
            clientTrouve = c;
            break;
        }
    }

    if (clientTrouve != null) {
        JOptionPane.showMessageDialog(this, "Connexion réussie pour : " + clientTrouve.getNom());
        this.dispose();
        // TODO : ouvrir Clientview
    } else {
        JOptionPane.showMessageDialog(this, "Client introuvable.");
    }
});

inscription.addActionListener(e -> {
    try {
        String idStr = JOptionPane.showInputDialog(this, "Entrez un ID :");
        int id = Integer.parseInt(idStr);
        String nom = JOptionPane.showInputDialog(this, "Entrez votre prénom :");

        Client nouveauClient = new Client(id, nom);
        clients.add(nouveauClient);

        JOptionPane.showMessageDialog(this, "Inscription réussie pour : " + nom);
        this.dispose();
        // TODO : ouvrir Clientview
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erreur lors de l'inscription.");
    }
});
    
        retour.addActionListener(e -> {
            this.dispose(); // Ferme la fenêtre actuelle
            new Interface_principale().setVisible(true); // Retour accueil
        });
 
        setLayout(new GridLayout(5, 1, 10, 10));
        add(new JLabel("Espace de connexion", SwingConstants.CENTER));
        add(connexion);
        add(inscription);
        add(retour);
        setDefaultCloseOperation(EXIT_ON_CLOSE);// Ferme la fenêtre

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new loginview(new Vector<>()).setVisible(true));
    }
}
