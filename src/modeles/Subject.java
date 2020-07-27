package modeles;

import vues.Observateur;

public interface Subject {

  void notifyObservers();

  void addObserver(Observateur observer);
}
