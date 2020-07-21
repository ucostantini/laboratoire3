package vues;

import vues.Observateur;

public interface Subject {
    public void notifyObservers();
    public void addObserver(Observateur observer);
}
