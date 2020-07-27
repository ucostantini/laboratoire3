package modele;

import java.io.Serializable;

/**
 * Point d une FigureColoree
 */
public class Point implements Serializable {
    private int x;
    private int y;

    /**
     * Constructeur d un point
     *
     * @param x coordonnees sur x du point
     * @param y coordonnees sur y du point
     */
    public Point(int x, int y) {


        this.x = x;
        this.y = y;

    }

    /**
     * Methode retournant la distance entre deux points
     *
     * @param p point avec lequel calculer la distance
     * @return double la distance
     */
    public double distance(Point p) {
        double res;

        int px = p.rendreX();
        int py = p.rendreY();
        int carreX = (px - this.x) * (px - this.x);
        int carreY = (py - this.y) * (py - this.y);

        int additionCarre = carreX + carreY;

        res = Math.sqrt(additionCarre);

        return res;
    }

    /**
     * Rendre x
     *
     * @return the int
     */
    public int rendreX() {
        return x;
    }

    /**
     * Rendre y
     *
     * @return the int
     */
    public int rendreY() {
        return y;
    }

    /**
     * Incrementer x
     *
     * @param dx la valeur a ajouter
     */
    public void incrementerX(int dx) {
        if ((this.x + dx) < 0) {
            this.x = 0;
        } else {
            this.x += dx;
        }

    }

    /**
     * Incrementer y
     *
     * @param dy la valeur a ajouter
     */
    public void incrementerY(int dy) {
        if ((this.y + dy) < 0) {
            this.y = 0;
        } else {
            this.y += dy;
        }
    }

    /**
     * Modifier x
     *
     * @param x nouveau x
     */
    public void modifierX(int x) {
        if (x > 0) {
            this.x = x;
        }

    }

    /**
     * Modifier y
     *
     * @param y nouveau y
     */
    public void modifierY(int y) {

        if (y > 0) {
            this.y = y;
        }

    }

    /**
     * Methode permettant de translater un point
     *
     * @param dx la valeur a ajouter sur x
     * @param dy la valeur a ajouter sur y
     */
    public void translation(int dx, int dy) {
        this.x += dx;

        this.y += dy;

    }

}
