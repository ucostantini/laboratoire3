package modeles;

import vues.Observateur;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModeleImage implements Subject {
    private Image image;
    private List<Observateur> abonnes;

    public ModeleImage()
    {
        this.abonnes = new ArrayList<>();

    }

    public void setImage(Image image)
    {
        this.image = image;
        this.notifyObservers();
    }

    public Image getImage() {
        return this.image;
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
