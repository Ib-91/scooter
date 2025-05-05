package controllers;
import views.Gestionview;
import views.loginview;
import views.Clientview;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import models.Client;

public class InterfaceController {

    public static void initListeners(JFrame mainFrame, JButton gestionButton, JButton clientButton) {
        gestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Gestionview().setVisible(true);  // Fenêtre de gestion
            }
        });

        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new loginview(new Vector<>()).setVisible(true); 
            }
        });
    }
}
