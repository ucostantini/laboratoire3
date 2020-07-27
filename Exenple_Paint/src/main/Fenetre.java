package main;

import controleur.PanneauChoix;
import modele.DessinModele;
import vue.VueDessin;

import javax.swing.*;
import java.awt.*;

/**
 * Fenetre qui contient le JFrame
 */
public class Fenetre {

    /**
     * Creation du "container" qui a la Jframe, ou on realise la creation des JPanels
     *
     * @param s le nom
     * @param x la largeur
     * @param y la hauteur
     */
    public Fenetre(String s, int x, int y) {

        //construction de la fenetre
        JFrame j = new JFrame(s);
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        j.getContentPane().setLayout(new BorderLayout());

        //construction du modele et de la vue
        DessinModele dm = new DessinModele();
        VueDessin vDessin = new VueDessin(dm);
        dm.addObserver(vDessin);

        //construction et ajout des panneaux
        JPanel jp = new JPanel();
        PanneauChoix p = new PanneauChoix(vDessin, jp);
        vDessin.setPreferredSize(new Dimension(x, y));
        j.getContentPane().add(jp, BorderLayout.PAGE_START);
        j.getContentPane().add(vDessin, BorderLayout.CENTER);
        j.setJMenuBar(p.getJm());

        j.pack();
        j.setVisible(true);

    }

    /**
     * Le main de l application
     *
     * @param args arguments d entree
     */
    public static void main(String[] args) {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Linux"))
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            else if (os.contains("Windows"))
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.Windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new Fenetre("Figures Géométriques", 500, 500);
    }

}
