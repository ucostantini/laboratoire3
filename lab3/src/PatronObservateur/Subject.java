package PatronObservateur;

public interface Subject {
    void notifyObservers();
    void addObserver(Observateur observer);
}
