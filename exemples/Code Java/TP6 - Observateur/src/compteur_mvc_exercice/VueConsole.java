package compteur_mvc_exercice;

import java.util.Observable;
import java.util.Observer;

public class VueConsole implements Observer {

    @Override
    public void update(Observable observable, Object o) {
        System.out.println(((Model)observable).getCompteur());
    }
}
