package controleur;

import modele.DessinModele;
import modele.FigureColoree;
import modele.Point;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * FabricantFigures auditeur fabriquant une FigureColoree
 */
public class FabricantFigures implements MouseListener {
    private ArrayList<Point> points_cliques;
    private int nb_point_cliques;
    private FigureColoree figure_en_cours_de_fabrication;
    private DessinModele dm;

    /**
     * Constructeur permettant d initialiser les outils necessaire pour la creation d une FigureColoree,
     *
     * @param fc FigureColoree qui est la figure a construire
     * @param dm le modele observe qui permet de gerer l evolution
     */
    public FabricantFigures(FigureColoree fc, DessinModele dm) {
        this.figure_en_cours_de_fabrication = fc;
        this.nb_point_cliques = 0;
        this.points_cliques = new ArrayList<>(this.figure_en_cours_de_fabrication.nbClics());
        this.dm = dm;
    }

    /**
     * Methode qui permet de stopper le listener
     *
     * @param e MouseEvent
     */
    private void finListener(MouseEvent e) {
        ((Component) e.getSource()).removeMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Methode permettant d enregistrer les points cliques jusque nbClics
     * On modifie la figure avec les nouveaux points, et on l ajoute a la liste de FigureColoree de DessinModele
     *
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {

        this.points_cliques.add(new Point(e.getX(), e.getY()));
        this.nb_point_cliques++;
        if (this.nb_point_cliques == this.figure_en_cours_de_fabrication.nbClics()) {
            this.figure_en_cours_de_fabrication.modifierPoints(this.points_cliques);
            this.dm.ajoute(this.figure_en_cours_de_fabrication);
            this.nb_point_cliques = 0;
            this.points_cliques.clear();
            this.finListener(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
