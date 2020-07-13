package modele;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.ArrayList;


public class Ellipse extends FigureColoree implements Serializable {

    protected Ellipse2D cercle;

    protected double largeur;
    private double hauteur;


    public Ellipse() {
        super();
        this.cercle = new Ellipse2D.Double();
    }

    @Override
    public int nbClics() {
        return 3;
    }

    @Override
    public void modifierPoints(ArrayList<Point> tab) {
        Point p1 = tab.get(0);
        Point p2 = tab.get(1);
        Point p3 = tab.get(2);

        if (p1.rendreX() > p2.rendreX() || p1.rendreY() > p2.rendreY()) {
            Point ptmp = p1;
            p1 = p2;
            p2 = ptmp;
        }

        this.largeur = p1.distance(p2);
        this.hauteur = p2.distance(p3);
        this.cercle = new Ellipse2D.Double(p1.rendreX(), p1.rendreY(), this.largeur, this.hauteur);
        this.tab_mem = new ArrayList<>(tab);
    }

    @Override
    public boolean contient(int x, int y) {
        return this.cercle.contains(x, y);
    }

    @Override
    public void affiche(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.couleur);
        g2.fill(this.cercle);
        if (this.selected) {
            g2.setColor(Color.RED);
            g2.draw(this.cercle);
        }
    }

}
