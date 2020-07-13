package zeldiablo;

import moteurJeu.MoteurGraphique;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        JeuZeldiablo j = new JeuZeldiablo();
        DessinZeldiablo dessin = new DessinZeldiablo(j);
        MoteurGraphique mg = new MoteurGraphique(j, dessin);
        mg.lancerJeu(400, 400);
    }
}
