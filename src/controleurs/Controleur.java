package controleurs;

import java.awt.*;
import java.awt.event.*;
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

public class Controleur extends JPanel implements MouseListener, MouseMotionListener, KeyListener,
        MouseWheelListener {

  private final ModeleImage image;

  private final Map<Vue, ModelePerspective> bindings;
  ModelePerspective perspective1;
  ModelePerspective perspective2;
  GestionnaireCommandes gcPers1 = GestionnaireCommandes.getInstance();
  GestionnaireCommandes gcPers2 = GestionnaireCommandes.getInstance();


  Point fin;
  Point debut;



  public Controleur(Vue vue1, Vue vue2, ModeleImage image, ModelePerspective perspective1,
      ModelePerspective perspective2) throws Exception {
    this.setBorder(BorderFactory.createLineBorder(Color.black));
    this.image = image;
    this.perspective1 = perspective1;
    this.perspective2 = perspective2;

    vue1.addKeyListener(this);
    vue1.addMouseListener(this);
    vue1.addMouseMotionListener(this);
    vue1.addMouseWheelListener(this);

    vue2.addKeyListener(this);
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

    JMenuItem undoPers1 = new JMenuItem("Annuler l'action perspective 1...");
    JMenuItem undoPers2 = new JMenuItem("Annuler l'action perspective 2...");
    JMenuItem enregistrer = new JMenuItem("Enregistrer sous...");
    JMenuItem ouvrir = new JMenuItem("Ouvrir...");

    enregistrer.addActionListener(actionEvent -> enregistrer());
    ouvrir.addActionListener(actionEvent -> ouvrirFichier());
    undoPers1.addActionListener(actionEvent -> retablirPers1());
    undoPers2.addActionListener(actionEvent -> {
      retablirPers2();
    });

    menu.add(undoPers1);
    menu.add(undoPers2);
    menu.add(ouvrir);
    menu.add(enregistrer);

    barreMenu.add(menu);

    this.add(barreMenu);


  }

  private void retablirPers1()  {
    gcPers1.undoCommande(perspective1);
  }

  private void retablirPers2()  {
    gcPers2.undoCommande(perspective2);
  }

  private void annulerAction() {

    System.out.println(perspective1.zoomLevel);

    System.out.println(perspective1.zoomAncien);

  }

  private void enregistrer() {
    //enregisrter les perspectives + image
  }

  private void ouvrirFichier() {
    //ouvrir le fichier sauvegarde
  }

  @Override
  public void mouseClicked(MouseEvent mouseEvent) {
    perspective1.zoomAncien = perspective1.zoomLevel;
  }

  @Override
  public void mousePressed(MouseEvent mouseEvent) {
    this.debut = mouseEvent.getPoint();
    ModelePerspective mp = this.bindings.get((Vue)mouseEvent.getSource());
    if (!mp.listPosition.contains(fin)) {
      mp.listPosition.add(debut);
    }
    mp.setDragStartScreen(mouseEvent.getPoint());
    mp.setDragEndScreen(null);
  }

  @Override
  public void mouseReleased(MouseEvent mouseEvent) {
    this.fin = mouseEvent.getPoint();
    ModelePerspective mp = this.bindings.get((Vue)mouseEvent.getSource());
    mp.listPosition.add(fin);
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
    if (this.bindings.get(source) == perspective1){
      gcPers1.executerCommande(new TranslateCommande(), this.bindings.get(source), mouseEvent);
    }else
      gcPers2.executerCommande(new TranslateCommande(), this.bindings.get(source), mouseEvent);
  }

  @Override
  public void mouseMoved(MouseEvent mouseEvent) {


  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
    //if (mouseWheelEvent.isControlDown()) {
      Vue source = (Vue) mouseWheelEvent.getSource();
    if (this.bindings.get(source) == perspective1){
      gcPers1.executerCommande(new ZoomCommande(), this.bindings.get(source), mouseWheelEvent);
    }else
      gcPers2.executerCommande(new ZoomCommande(), this.bindings.get(source), mouseWheelEvent);
    //}
  }


  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("test");

  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("test");

  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println("test");

  }
}
