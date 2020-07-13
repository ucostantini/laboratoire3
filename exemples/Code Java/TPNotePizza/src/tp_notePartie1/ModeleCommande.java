package tp_notePartie1;

import java.util.ArrayList;
import java.util.Observable;

public class ModeleCommande extends Observable {
    private int nbPizza;
    private int numPizzaCourante;
    private double prixCommande;

    private ArrayList<Pizza> listePizza;
    private StrategyFidelite taux;

    public ModeleCommande(){
        this.nbPizza=0;
        this.numPizzaCourante=-1;
        this.prixCommande=0;
        this.listePizza = new ArrayList<>();
        this.taux = new ClientNouveau();
    }

    //on ajoute une pizza --> mise a jour de la vue pour l afficher
    public void ajouterPizza(String s){
        if(this.nbPizza<4){
            this.listePizza.add((s.contains("creme") ? new PizzaCreme() : new PizzaTomate()));
            this.nbPizza++;
        }
        setChanged();
        notifyObservers();
    }

    public void calculPrixCommande() {
        this.prixCommande=0;
        for(Pizza p : this.listePizza)
                this.prixCommande+=p.cout();
        this.prixCommande*=this.taux.getTaux();
    }

    public int getNbPizza() {
        return nbPizza;
    }

    public int getNumPizzaCourante() {
        return numPizzaCourante;
    }

    public double getPrixCommande() {
        return prixCommande;
    }

    public ArrayList<Pizza> getListePizza() {
        return listePizza;
    }

    //on a selectionne une pizza --> mise a jour de la vue (pour le carre noir)
    public void setNumPizzaCourante(int numPizzaCourante) {
        this.numPizzaCourante = numPizzaCourante;
        setChanged();
        notifyObservers();
    }

    //le client change de type --> le prix doit changer, mise a jour de la vue
    public void setTaux(StrategyFidelite taux) {
        this.taux = taux;
        setChanged();
        notifyObservers();
    }
}
