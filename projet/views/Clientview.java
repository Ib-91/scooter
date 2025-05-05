package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Clientview extends JFrame {

    private JButton reserverButton;
    private JButton historiqueLocButton;
    private JButton historiqueScooterButton;
    private JButton retourAccueilButton;

    public Clientview() {
        setTitle("Espace Client - Loc2Scoot");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        reserverButton = new JButton("Réserver un scooter");
        historiqueLocButton = new JButton("Historique de mes locations");
        historiqueScooterButton = new JButton("Historique des scooters loués");
        retourAccueilButton = new JButton("Retour à l'accueil");

        reserverButton.addActionListener(e -> {
            // appel à la méthode de réservation (à relier au contrôleur)
            JOptionPane.showMessageDialog(this, "Fonctionnalité de réservation à venir.");
        });

        historiqueLocButton.addActionListener(e -> {
            // appel méthode affichage historique des locations
            JOptionPane.showMessageDialog(this, "Historique de vos locations (à venir).");
        });

        historiqueScooterButton.addActionListener(e -> {
            // appel méthode affichage historique scooters
            JOptionPane.showMessageDialog(this, "Historique des scooters loués (à venir).");
        });

        retourAccueilButton.addActionListener(e -> {
            this.dispose(); // Ferme la fenêtre actuelle
            new Interface_principale().setVisible(true); // Retour accueil
        });

        setLayout(new GridLayout(5, 1, 10, 10));
        add(new JLabel("Bienvenue dans l'espace client", SwingConstants.CENTER));
        add(reserverButton);
        add(historiqueLocButton);
        add(historiqueScooterButton);
        add(retourAccueilButton);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Clientview().setVisible(true));
    }
}
