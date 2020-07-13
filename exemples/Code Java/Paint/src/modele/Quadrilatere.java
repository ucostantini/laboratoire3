package modele;

import java.io.Serializable;


public class Quadrilatere extends Polygone implements Serializable {

    public Quadrilatere() {
        super();
    }

    @Override
    public int nbClics() {
        return 4;
    }

}
