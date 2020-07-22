package vues;

import javax.swing.*;

public class Environnement extends SwingWorker<Object, String> {
    private boolean actif = true;
    private FenetrePrincipale fenetre;

    public Environnement(FenetrePrincipale fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    protected Object doInBackground() throws Exception {
        while (actif) {

            fenetre.revalidate();
            fenetre.repaint();

            Thread.sleep(10);

        }
        return null;
    }

}