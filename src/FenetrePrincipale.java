import controleurs.Controleur;
import modeles.ModeleImage;
import modeles.ModelePerspective;
import vues.Vignette;
import vues.Vue;

import javax.swing.*;
import java.awt.*;


public class FenetrePrincipale extends JFrame {

    private static final String TITRE_FENETRE = "Laboratoire 3";
    private static final Dimension DIMENSION = new Dimension(1200, 800);


    private FenetrePrincipale() throws Exception
    {

        ModeleImage image = new ModeleImage();
        ModelePerspective perspective1 = new ModelePerspective();
        ModelePerspective perspective2 = new ModelePerspective();

        Vue vue1 = new Vue(image, perspective1);
        Vue vue2 = new Vue(image, perspective2);
        Vignette vignette = new Vignette(image);

        image.addObserver(vue1);
        image.addObserver(vue2);
        image.addObserver(vignette);

        perspective1.addObserver(vue1);
        perspective2.addObserver(vue2);

        Controleur controleur = new Controleur(vue1, vue2, image, perspective1, perspective2);

        JFrame fenetre = new JFrame();

        fenetre.getContentPane().add(controleur, BorderLayout.NORTH);
        fenetre.getContentPane().add(vue1, BorderLayout.WEST);
        fenetre.getContentPane().add(vue2, BorderLayout.EAST);
        fenetre.getContentPane().add(vignette, BorderLayout.CENTER);

        this.pack();

        fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fenetre.setTitle(TITRE_FENETRE);
        fenetre.setSize(DIMENSION);
        fenetre.setVisible(true);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
    }

    public static void main(String[] args) throws Exception
    {
        new FenetrePrincipale();
    }
}