package controleurs;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import modeles.ModeleImage;
import modeles.ModelePerspective;
import vues.Vue;

public class Controleur extends JPanel implements MouseListener, MouseMotionListener,
    MouseWheelListener {

  private final ModeleImage image;

  private final Map<Vue, ModelePerspective> bindings;


  public Controleur(Vue vue1, Vue vue2, ModeleImage image, ModelePerspective perspective1,
      ModelePerspective perspective2) throws Exception {
    this.setBorder(BorderFactory.createLineBorder(Color.black));
    this.image = image;

    vue1.addMouseListener(this);
    vue1.addMouseMotionListener(this);
    vue1.addMouseWheelListener(this);

    vue2.addMouseListener(this);
    vue2.addMouseMotionListener(this);
    vue2.addMouseWheelListener(this);

    this.bindings = new HashMap<>();
    this.bindings.put(vue1, perspective1);
    this.bindings.put(vue2, perspective2);

    //Chargement de l image
    JFileChooser fileChooser = new JFileChooser(
        FileSystemView.getFileSystemView().getHomeDirectory());
    fileChooser.setDialogTitle("Selectionner une image");
    fileChooser.setAcceptAllFileFilterUsed(false);

    fileChooser
        .addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));

    int returnValue = fileChooser.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      //Cet methode appelle notifyObservers
      this.image.setImage(ImageIO.read(fileChooser.getSelectedFile()));
    }

    //Chargement de la barre de menu
    JMenuBar barreMenu = new JMenuBar();
    JMenu menu = new JMenu("Menu...");

    JMenuItem undo = new JMenuItem("Annuler l'action...");
    JMenuItem redo = new JMenuItem("Rétablir...");
    JMenuItem enregistrer = new JMenuItem("Enregistrer sous...");
    JMenuItem ouvrir = new JMenuItem("Ouvrir...");

    enregistrer.addActionListener(actionEvent -> enregistrer());
    ouvrir.addActionListener(actionEvent -> ouvrirFichier());
    undo.addActionListener(actionEvent -> annulerAction());
    redo.addActionListener(actionEvent -> retablir());

    menu.add(undo);
    menu.add(redo);
    menu.add(ouvrir);
    menu.add(enregistrer);

    barreMenu.add(menu);

    this.add(barreMenu);


  }

  private void retablir() {
  }

  private void annulerAction() {
  }

  private void enregistrer() {
    //enregisrter les perspectives + image
  }

  private void ouvrirFichier() {
    //ouvrir le fichier sauvegarde
  }

  @Override
  public void mouseClicked(MouseEvent mouseEvent) {

  }

  @Override
  public void mousePressed(MouseEvent mouseEvent) {
    ModelePerspective mp = this.bindings.get((Vue)mouseEvent.getSource());
    mp.setDragStartScreen(mouseEvent.getPoint());
    mp.setDragEndScreen(null);
  }

  @Override
  public void mouseReleased(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseEntered(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseExited(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseDragged(MouseEvent mouseEvent) {
    Vue source = (Vue) mouseEvent.getSource();
    GestionnaireCommandes gc = GestionnaireCommandes.getInstance();
    gc.executerCommande(new TranslateCommande(), this.bindings.get(source), mouseEvent);
  }

  @Override
  public void mouseMoved(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
    if (mouseWheelEvent.isControlDown()) {
      Vue source = (Vue) mouseWheelEvent.getSource();
      GestionnaireCommandes gc = GestionnaireCommandes.getInstance();
      gc.executerCommande(new ZoomCommande(), this.bindings.get(source), mouseWheelEvent);
    }
  }
}
