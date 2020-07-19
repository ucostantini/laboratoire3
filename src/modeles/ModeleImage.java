package modeles;

import vues.Observateur;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class ModeleImage implements Subject {
    private Image image;
    private List<Observateur> abonnes;

    public ModeleImage(Image image) {
        this.image = image;
        this.abonnes = new ArrayList<>();
    }

    public Image getImage()
    {
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
