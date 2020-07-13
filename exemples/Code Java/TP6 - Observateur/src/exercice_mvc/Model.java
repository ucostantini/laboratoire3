package exercice_mvc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Observable;

public class Model extends Observable {
    LinkedList<String> list = new LinkedList<>();

    public Model() {
        this.list = new LinkedList<>();
    }

    public void ajouterMot(String s) {
        this.list.add(s);
        setChanged();
        notifyObservers();
    }

    public LinkedList<String> getList() {
        return list;
    }

    public String getMax() {
        return Collections.max(this.list);
    }

    public String getMin() {
        return Collections.min(this.list);
    }
}
