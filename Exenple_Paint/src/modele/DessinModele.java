package modele;

import vue.VueDessin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * DessinModele classe contenant la liste de FigureColoree et permettant de mettre a jour la vue
 */
public class DessinModele extends Observable implements Serializable {
    private ArrayList<FigureColoree> lfc;
    private boolean manip;

    /**
     * Constructeur
     */
    public DessinModele() {
        this.lfc = new ArrayList<>();
        this.manip = false;
    }


    /**
     * Methode ajoutant une figure a la liste de figures et previent l observeur du changement
     *
     * @param fc figure ajoutee
     */
    public void ajoute(FigureColoree fc) {
        this.manip = false;
        this.lfc.add(fc);
        VueDessin.nbFigures++;
        setChanged();
        notifyObservers();
    }


    /**
     * Methode prevenant l oberserveur pendant la manipulation
     */
    public void manipulation() {
        this.manip = true;
        setChanged();
        notifyObservers();
    }


    /**
     * Retourne la liste de figures du modele
     *
     * @return lfc la liste
     */
    public ArrayList<FigureColoree> getLfc() {
        return this.lfc;
    }

    /**
     * Permet de modifier la liste de figures
     *
     * @param lfc la liste
     */
    public void setLfc(ArrayList<FigureColoree> lfc) {
        this.lfc = lfc;
    }

    /**
     * On est en train de manipuler ou non
     *
     * @return le boolean manip
     */
    public boolean isManip() {
        return this.manip;
    }
}

