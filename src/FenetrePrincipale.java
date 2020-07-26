import controleurs.Controleur;
import modeles.ModeleImage;
import modeles.ModelePerspective;
import vues.Vignette;
import vues.Vue;

import javax.swing.*;
import java.awt.*;


public class FenetrePrincipale extends JFrame {

    private static final String TITRE_FENETRE = "Laboratoire 3";
    private static final Dimension DIMENSION = new Dimension(700, 500);


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


        //perspective1.addObserver(vue1);
        //perspective2.addObserver(vue2);

        Controleur controleur = new Controleur(vue1, vue2, image, perspective1, perspective2);

        JFrame fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fenetre.setTitle(TITRE_FENETRE);
        fenetre.setSize(DIMENSION);
        fenetre.setVisible(true);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);


        JPanel panel = new JPanel(new GridLayout(1,3,10,20));
        panel.add(vue1);
        panel.add(vue2);
        panel.add(vignette);

        fenetre.add(controleur, BorderLayout.NORTH);
        fenetre.add(panel,BorderLayout.CENTER);

        this.pack();


    }

    public static void main(String[] args) throws Exception
    {
        FenetrePrincipale f = new FenetrePrincipale();

    }
}
