package stationmeteo_observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

public class AffichageStats implements Observer,Affichage {
    int nb;
    ArrayList<Float> temps;

    public AffichageStats(Observable observable) {
        observable.addObserver(this);
        this.nb=0;
        this.temps = new ArrayList<>();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof DonneesMeteo) {
            DonneesMeteo donneesMeteo = (DonneesMeteo)observable;
            this.temps.add(donneesMeteo.getTemperature());
            this.nb++;
            this.afficher();
        }
    }

    private double calculateAverage() {
        Float sum = (float)0.0;
        if(!this.temps.isEmpty()) {
            for (Float temp : this.temps)
                sum += temp;

            return sum.doubleValue() / this.temps.size();
        }
        return sum;
    }

    @Override
    public void afficher() {

        System.out.println("\nNbre de lectures: " + this.nb
                + " T°C Max/moy/min " + Collections.max(this.temps) + "/" + this.calculateAverage() + "/" + Collections.min(this.temps));
    }
}
