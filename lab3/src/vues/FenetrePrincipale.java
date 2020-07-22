package vues;

import modele.ImageModel;

import java.awt.*;

import javax.swing.*;

public class FenetrePrincipale extends JFrame  {

    private static final String TITRE_FENETRE = "Laboratoire 3";
    private static final Dimension DIMENSION = new Dimension(1200, 800);
    PanneauPrincipal panneauPrincipal;

    public FenetrePrincipale(ImageModel modelImage) {

        panneauPrincipal = new PanneauPrincipal(modelImage);
        MenuFenetre menuFenetre = new MenuFenetre(modelImage);

        add(panneauPrincipal);
        add(menuFenetre, BorderLayout.NORTH);
        // Faire en sorte que le X de la fenêtre ferme la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITRE_FENETRE);
        setSize(DIMENSION);
        // Rendre la fenêtre visible
        setVisible(true);
        // Mettre la fenêtre au centre de l'écran
        setLocationRelativeTo(null);
        // Empêcher la redimension de la fenêtre
        setResizable(false);
    }




}
