package modele;
import PatronObservateur.Observateur;
import PatronObservateur.Subject;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class ModelePerspective implements Subject {
    private int zoom;
    private Point translation;
    private List<Observateur> abonnes;

    public ModelePerspective() {
        this.zoom = 0;
        this.translation = new Point(0,0);
        this.abonnes = new ArrayList<>();
    }

    public int getZoom()
    {
        return zoom;
    }

    public void setZoom(int zoom)
    {
        this.zoom = zoom;
        this.notifyObservers();
    }

    public Point getTranslation()
    {
        return translation;
    }

    public void setTranslation(Point translation)
    {
        this.translation = translation;
        this.notifyObservers();
    }

    @Override
    public void notifyObservers()
    {
        for (Observateur abonne : this.abonnes)
            abonne.update();
    }


    @Override
    public void addObserver(Observateur observer)
    {
        this.abonnes.add(observer);
    }
}
