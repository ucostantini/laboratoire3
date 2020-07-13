package tp_notePartie2;

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

    public void choixIngredient(int i) {
        if(this.numPizzaCourante!=-1) {
            Pizza p = this.listePizza.get(this.numPizzaCourante);
            switch(i){
                case 0 :
                    p = new PizzaFromage(this.listePizza.get(this.numPizzaCourante));
                    break;
                case 1 :
                    p = new PizzaChampignons(this.listePizza.get(this.numPizzaCourante));
                    break;
                case 2 :
                    p = new PizzaChorizo(this.listePizza.get(this.numPizzaCourante));
                    break;
                case 3 :
                    p = new PizzaOeuf(this.listePizza.get(this.numPizzaCourante));
                    break;
                case 4 :
                    p = new PizzaOignons(this.listePizza.get(this.numPizzaCourante));
                    break;
                case 5 :
                    p = new PizzaOlivesN(this.listePizza.get(this.numPizzaCourante));
                    break;
                case 6 :
                    p = new PizzaOlivesV(this.listePizza.get(this.numPizzaCourante));
                    break;
                case 7 :
                    p = new PizzaRoquette(this.listePizza.get(this.numPizzaCourante));
                    break;
            }
            System.out.println(p.getDescription());
            this.listePizza.set(this.numPizzaCourante,p);
            setChanged();
            notifyObservers();
        }

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

    public void setNumPizzaCourante(int numPizzaCourante) {
        this.numPizzaCourante = numPizzaCourante;
        setChanged();
        notifyObservers();
    }

    public void setTaux(StrategyFidelite taux) {
        this.taux = taux;
        setChanged();
        notifyObservers();
    }
}
