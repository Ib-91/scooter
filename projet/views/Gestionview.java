package views;
import javax.swing.*;
import java.awt.*;


public class Gestionview extends JFrame {

    public JButton addClientButton;
    public JButton removeClientButton;
    public JButton addScooterButton;
    public JButton removeScooterButton;
    public JButton historiqueLocationsButton;
    public JButton historiqueScootersButton;
    public JButton retourButton;

    public Gestionview() {
        setTitle("Espace Gestion - Location 2 Scoot");
        setSize(450, 400);
        setLocationRelativeTo(null);

        initUI();
    }

    public void initUI() {
        addClientButton = new JButton("Ajouter un client");
        removeClientButton = new JButton("Supprimer un client");
        addScooterButton = new JButton("Ajouter un scooter");
        removeScooterButton = new JButton("Supprimer un scooter");
        historiqueLocationsButton = new JButton("Historique des locations");
        historiqueScootersButton = new JButton("Historique par scooter");
        retourButton = new JButton("Retour à l'accueil");

        // Ajouter ici les appels aux méthodes (via contrôleur)
        addClientButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Ajout de client (à implémenter)");
        });

        removeClientButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Suppression de client (à implémenter)");
        });

        addScooterButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Ajout de scooter (à implémenter)");
        });

        removeScooterButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Suppression de scooter (à implémenter)");
        });

        historiqueLocationsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Historique des locations (à implémenter)");
        });

        historiqueScootersButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Historique des scooters (à implémenter)");
        });

        retourButton.addActionListener(e -> {
            this.dispose();
            new Interface_principale().setVisible(true);
        });
        

        setLayout(new GridLayout(8, 1, 10, 10));
        add(new JLabel("Espace Gestionnaire", SwingConstants.CENTER));
        add(addClientButton);
        add(removeClientButton);
        add(addScooterButton);
        add(removeScooterButton);
        add(historiqueLocationsButton);
        add(historiqueScootersButton);
        add(retourButton);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Gestionview().setVisible(true));
    }
}
