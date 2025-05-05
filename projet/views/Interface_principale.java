package views;
import javax.swing.*;
import java.awt.*;
import controllers.InterfaceController;


public class Interface_principale extends JFrame {

    public Interface_principale() {
        setTitle("Location 2 Scooter");
        setSize(400, 200);
        setLocationRelativeTo(null);//centre la fenetre

        JLabel title = new JLabel("LOC2SCOOT", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 25));// Police et taille du texte
        title.setForeground(new Color(50, 50, 180)); // Couleur du texte
        add(title, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40)); // Centrer et espacer les boutons
        JButton gestionButton = new JButton("Gestion");
        JButton clientButton = new JButton("Client");
        
        ImageIcon gestionIcon = new ImageIcon("C:\\Users\\barak\\Documents\\POO\\Projet\\Views\\gestion.jpg");  // Chemin sans espaces
        ImageIcon clientIcon = new ImageIcon("C:\\Users\\barak\\Documents\\POO\\Projet\\Views\\cliennt.png");  // Chemin sans espaces
        
        // Redimensionner l'icône pour que l'icône soit bien ajustée
        Image gestionImg = gestionIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);  // Redimensionner
        Image clientImg = clientIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);  // Redimensionner

        gestionButton.setIcon(new ImageIcon(gestionImg));
        clientButton.setIcon(new ImageIcon(clientImg));

        // Espacer l'icône du texte sur les boutons
        gestionButton.setIconTextGap(10);
        clientButton.setIconTextGap(10);

        buttonPanel.add(gestionButton);
        buttonPanel.add(clientButton);

        add(buttonPanel, BorderLayout.CENTER);
        
        InterfaceController.initListeners(this, gestionButton, clientButton);
        setDefaultCloseOperation(EXIT_ON_CLOSE);// Ferme la fenêtre
        /* 
        ESTHETIQUE 
        FOND
        Ameliorer la resolution image des boutons
        setBackground(new Color(240, 240, 240));

        
        Bouton qui change de couleur au survol

        gestionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gestionButton.setBackground(Color.LIGHT_GRAY);  // Change la couleur de fond quand la souris entre
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                gestionButton.setBackground(UIManager.getColor("control"));  // Restaure la couleur d'origine
            }

        });
*/

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interface_principale().setVisible(true));
    }
}
