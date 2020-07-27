package controleur;

import modele.DessinModele;
import modele.Point;
import modele.Trait;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * TraceurForme auditeur permettant de dessiner un Trait
 */
public class TraceurForme implements MouseListener, MouseMotionListener {

    private Color c;
    private ArrayList<Point> trait_en_cours;
    private DessinModele dm;

    /**
     * Constructeur preparant le Trait et recuperant le DessinModele correspondant
     *
     * @param d le DessinModele
     */
    public TraceurForme(DessinModele d) {
        this.dm = d;
        this.trait_en_cours = new ArrayList<>();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * On ajoute les Points au trait en cours
     *
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        this.trait_en_cours.add(new Point(e.getX(), e.getY()));
    }

    /**
     * On ajoute a la liste de FigureColoree de DessinModele le Trait que l on vient de dessiner, puis on reinitialise
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.dm.ajoute(new Trait(new ArrayList<>(this.trait_en_cours), this.c));
        this.trait_en_cours.clear();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Permet de modifier la couleur du Trait
     *
     * @param c la nouvelle couleur
     */
    public void setC(Color c) {
        this.c = c;
    }

    /**
     * On ajoute les Points au trait en cours
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        this.trait_en_cours.add(new Point(e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
