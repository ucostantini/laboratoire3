package controleur;

import modele.DessinModele;
import modele.FigureColoree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * ManipulateurForme auditeur permettant de manipuler une FigureColoree
 */
public class ManipulateurForme extends MouseAdapter {

    private DessinModele dm;
    private FigureColoree fc;
    private int lastX;
    private int lastY;
    private boolean dedans;

    /**
     * Constructeur de l auditeur en attribuant le dessin modele
     *
     * @param dm le modele observe qui permet de gerer l evolution
     */
    public ManipulateurForme(DessinModele dm) {
        this.dm = dm;
        this.dedans = false;
    }

    /**
     * Permet de changer la position de la figure
     *
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {

        ArrayList<FigureColoree> lfc = this.dm.getLfc();
        for (FigureColoree aLfc : lfc) {
            this.lastX = e.getX();
            this.lastY = e.getY();
            if (aLfc.contient(this.lastX, this.lastY)) {
                this.fc = aLfc;
                this.fc.selectionne();
                this.dedans = true;
            }
        }
    }

    /**
     * Alerte la figure apres avoir relache qu elle n est plus selectionnee
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.dedans) {
            this.fc.deSelectionne();
            this.dedans = false;
        }
    }

    /**
     * Change la position de la figure la ou va la souris
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        if (this.dedans) {
            int xcourant = e.getX();
            int ycourant = e.getY();

            this.fc.translation(xcourant - this.lastX, ycourant - this.lastY);
            this.fc.modifierPoints(fc.getTab_mem());

            this.dm.manipulation();
            this.lastX = xcourant;
            this.lastY = ycourant;
        }

    }

}
