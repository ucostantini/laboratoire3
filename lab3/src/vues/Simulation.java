package vues;

import modele.ImageModel;

public class Simulation {

    public static void main(String[] args) {

        ImageModel modelImage = new ImageModel();
        FenetrePrincipale fenetre = new FenetrePrincipale(modelImage);
        Environnement environnement = new Environnement(fenetre);
        environnement.execute();


    }

}
