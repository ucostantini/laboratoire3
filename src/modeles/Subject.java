package modeles;

import vues.Observateur;

/**
 * Interface utilisé pour l'observateur
 */
public interface Subject {
  /**
   * "avertir" d'un changement
   */
  void notifyObservers();

  /**
   * Ajoute un observateur
   * @param observer
   */
  void addObserver(Observateur observer);
}
