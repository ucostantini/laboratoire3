package vues;

import javax.swing.*;

public class BarreMenu extends JMenuBar {
    private JMenu menu;
    private JMenuItem ouvrirFichier;
    private JMenuItem enregistrerFichier;

    public BarreMenu(){
        this.menu = new JMenu("Fichier");
        this.ouvrirFichier = new JMenu("Ouvrir");
        this.enregistrerFichier = new JMenu("Enregister");

        //ouvrirFichier.addActionListener();

    }
}
