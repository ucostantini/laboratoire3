package vues;

import modele.ImageModel;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class MenuFenetre extends JMenuBar {

    ImageModel modelImage;

    public MenuFenetre(ImageModel modelImage) {
        this.modelImage = modelImage;
        ajouterMenuFichier();
    }

    /**
     * Créer le menu de Fichier
     */
    private void ajouterMenuFichier() {
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem menuCharger = new JMenuItem("Charger");
        JMenuItem menuQuitter = new JMenuItem("Quitter");

        menuCharger.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Sélectionnez un fichier de configuration");
            fileChooser.setAcceptAllFileFilterUsed(false);
            // Créer un filtre
            FileNameExtensionFilter filtre = new FileNameExtensionFilter(".png", "png");
            fileChooser.addChoosableFileFilter(filtre);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // TODO - Parser le fichier XML sélectionné
                File selectedFile = fileChooser.getSelectedFile();
                modelImage.setImage(selectedFile.getAbsolutePath());

            }
        });

        menuQuitter.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        menuFichier.add(menuCharger);
        menuFichier.add(menuQuitter);

        add(menuFichier);

    }


}
