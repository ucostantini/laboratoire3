package vue;

import controleur.FabricantFigures;
import controleur.ManipulateurForme;
import controleur.TraceurForme;
import modele.*;
import modele.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * VueDessin la vue
 */
public class VueDessin extends JPanel implements Observer {

    public static int nbFigures;
    private DessinModele dessin;

    private TraceurForme tf;
    private ManipulateurForme mf;
    private FabricantFigures ff;

    /**
     * Constructeur de la vue
     *
     * @param d le DessinModele
     */
    public VueDessin(DessinModele d) {
        this.dessin = d;
        this.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (VueDessin.nbFigures != 0) {
            for (FigureColoree f : this.dessin.getLfc()) {
                f.affiche(g);

                if (f instanceof Trait) {
                    g.setColor(f.getCouleur());
                    for (int i = 1; i < f.getTab_mem().size(); i++) {
                        g.drawLine(f.getTab_mem().get(i - 1).rendreX(), f.getTab_mem().get(i - 1).rendreY(),
                                f.getTab_mem().get(i).rendreX(), f.getTab_mem().get(i).rendreY());
                    }
                }
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
        if (!this.dessin.isManip())
            this.nouvelleFigure();
    }

    /**
     * Permet de creer une nouvelle figure juste apres la precedente, et donc construire plusieurs
     * FigureColoree les unes a la suite des autres
     */
    private void nouvelleFigure() {
        ArrayList<FigureColoree> lfc = new ArrayList<>(this.dessin.getLfc());
        FigureColoree fc = lfc.get(lfc.size() - 1);
        Color c = fc.getCouleur();

        if (fc instanceof Quadrilatere) {
            if (fc instanceof Rectangle) {
                if (fc instanceof Carre)
                    fc = new Carre();
                else
                    fc = new Rectangle();
            } else
                fc = new Quadrilatere();
        }

        if (fc instanceof Triangle) {
            if (fc instanceof Losange)
                fc = new Losange();
            else
                fc = new Triangle();
        }

        if (fc instanceof Ellipse) {
            if (fc instanceof Cercle)
                fc = new Cercle();
            else
                fc = new Ellipse();
        }

        fc.setCouleur(c);
        this.construit(fc);

    }

    /**
     * Construit une nouvelle FigureColoree
     *
     * @param fc la FigureColoree choisie dans PanneauChoix
     */
    public void construit(FigureColoree fc) {
        this.ff = new FabricantFigures(fc, this.dessin);
        this.addMouseListener(this.ff);
    }

    /**
     * Trace un nouveau Trait
     *
     * @param c la couleur du nouveau Trait
     */
    public void trace(Color c) {
        this.tf = new TraceurForme(this.dessin);
        this.tf.setC(c);
        this.addMouseListener(this.tf);
        this.addMouseMotionListener(this.tf);
    }

    /**
     * Active la manipulation de la souris
     */
    public void activeManipulationSouris() {
        this.mf = new ManipulateurForme(this.dessin);
        this.addMouseListener(this.mf);
        this.addMouseMotionListener(this.mf);
    }


    /**
     * Recupere le DessinModele associe
     *
     * @return le dessin
     */
    public DessinModele getDessin() {
        return this.dessin;
    }

}
