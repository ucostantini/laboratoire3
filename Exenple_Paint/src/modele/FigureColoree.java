package modele;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * FigureColoree, superclasse de toutes les figures pouvant etre tracees a l ecran
 */
public abstract class FigureColoree implements Serializable {

    protected Color couleur;
    protected ArrayList<Point> tab_mem;
    protected boolean selected;

    /**
     * Constructeur d une figure coloree
     */
    public FigureColoree() {
        this.selected = false;
        this.couleur = Color.BLACK;
        this.tab_mem = new ArrayList<>();
    }

    /**
     * Le nombre de points de la figure
     *
     * @return int la taille de la liste
     */
    public int nbPoints() {
        return this.tab_mem.size();
    }

    /**
     * Methode retournant  le nombre de clics necessaires pour realiser la figure
     *
     * @return Nombre de clics
     */
    public abstract int nbClics();

    /**
     * Permet de modifier les points de la figure
     *
     * @param tab la nouvelle liste de Point
     */
    public abstract void modifierPoints(ArrayList<Point> tab);

    /**
     * Permet de determiner si le clic est dans la FigureColoree ou non
     *
     * @param x la coordonnee sur x
     * @param y la coordonnee sur y
     * @return le boolean
     */
    public abstract boolean contient(int x, int y);

    /**
     * Permet d afficher la figure a l ecran
     *
     * @param g Graphics
     */
    public abstract void affiche(Graphics g);

    /**
     * Methode qui change l attribut pour prevenir que la figure est selectionnee
     */
    public void selectionne() {
        this.selected = true;
    }

    /**
     * Permet de translater la FigureColoree
     *
     * @param x la coordonnee sur x
     * @param y la coordonnee sur y
     */
    public void translation(int x, int y) {
        for (Point p : this.tab_mem)
            p.translation(x, y);
    }

    /**
     * Methode qui change l attribut pour prevenir que la figure est deselectionnee
     */
    public void deSelectionne() {
        this.selected = false;
    }

    /**
     * Change la couleur de la figure
     *
     * @param c la nouvelle couleur
     */
    public void changeCouleur(Color c) {
        this.couleur = c;
    }

    /**
     * Retourne la couleur de la figure
     *
     * @return la couleur
     */
    public Color getCouleur() {
        return this.couleur;
    }

    /**
     * Permet de modifier la couleur de la figure
     *
     * @param couleur la couleur
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    /**
     * Retourne la liste de Point de la figure
     *
     * @return la liste
     */
    public ArrayList<Point> getTab_mem() {
        return this.tab_mem;
    }

    /**
     * Permet de modifier la liste de Point de la figure
     *
     * @param tab_mem la nouvelle liste
     */
    public void setTab_mem(ArrayList<Point> tab_mem) {
        this.tab_mem = tab_mem;
    }

}
