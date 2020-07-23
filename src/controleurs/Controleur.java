package controleurs;

import modeles.ModeleImage;
import modeles.ModelePerspective;
import vues.Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;

public class Controleur extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    private Vue vue1;
    private Vue vue2;

    private ModelePerspective perspective1;
    private ModelePerspective perspective2;
    private ModeleImage image;

    private JMenuBar barreMenu;


    public Controleur(Vue vue1, Vue vue2, ModeleImage image, ModelePerspective perspective1, ModelePerspective perspective2) throws Exception
    {
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.vue1 = vue1;
        this.vue2 = vue2;
        this.perspective1 = perspective1;
        this.perspective2 = perspective2;
        this.image = image;

        //Chargement de l image
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Selectionner une image");
        fileChooser.setAcceptAllFileFilterUsed(false);

        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            //Cet methode appelle notifyObservers
            this.image.setImage(ImageIO.read(fileChooser.getSelectedFile()));
        }

        //Chargement de la barre de menu
        JMenuBar barreMenu = new JMenuBar();
        JMenu menu = new JMenu("Fichier...");

        JMenuItem enregistrer = new JMenuItem("Enregistrer sous...");
        JMenuItem ouvrir = new JMenuItem("Ouvrir...");

        enregistrer.addActionListener(actionEvent -> enregistrer());
        ouvrir.addActionListener(actionEvent -> ouvrirFichier());

        menu.add(enregistrer);
        menu.add(ouvrir);

        barreMenu.add(menu);

        this.add(barreMenu);


    }

    private void enregistrer()
    {
        //enregisrter les perspectives + image
    }

    private void ouvrirFichier()
    {
        //ouvrir le fichier sauvegarde
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent)
    {

    }
}
