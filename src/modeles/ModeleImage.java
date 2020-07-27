package modeles;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import vues.Observateur;

public class ModeleImage implements Subject {

  private Image image;
  private final List<Observateur> abonnes;

  public ModeleImage() {
    this.abonnes = new ArrayList<>();

  }

  public Image getImage() {
    return this.image;
  }

  public void setImage(Image image) {
    this.image = image;
    this.notifyObservers();
  }

  @Override
  public void notifyObservers() {
      for (Observateur abonne : this.abonnes) {
          abonne.update();
      }
  }

  @Override
  public void addObserver(Observateur observer) {
    this.abonnes.add(observer);
  }
}
