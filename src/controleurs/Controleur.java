package controleurs;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.NoninvertibleTransformException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import modeles.ImageSauvegarde;
import modeles.ModeleImage;
import modeles.ModelePerspective;
import vues.Vue;

/**
 * Classe qui est responsable de toutes les manipulations sur les perspectives
 */
public class Controleur extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

	private final ModeleImage image;

	private final Map<Vue, ModelePerspective> bindings;

	private Point fin;
	private Point debut;

	private ModelePerspective perspective1;
	private ModelePerspective perspective2;

	boolean isZoom = false;

	/**
	 * Constructeur du controleur
	 * @param vue1
	 * @param vue2
	 * @param image
	 * @param perspective1
	 * @param perspective2
	 * @throws Exception
	 */
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

		this.perspective1 = perspective1;
		this.perspective2 = perspective2;

		// Chargement de l image
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooser.setDialogTitle("Selectionner une image");
		fileChooser.setAcceptAllFileFilterUsed(false);

		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));

		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			// Cet methode appelle notifyObservers
			this.image.setImage(ImageIO.read(fileChooser.getSelectedFile()));
		}

		// Chargement de la barre de menu
		JMenuBar barreMenu = new JMenuBar();
		JMenu menu = new JMenu("Menu...");

		JMenuItem undo1 = new JMenuItem("Annuler perspective 1");
		JMenuItem undo2 = new JMenuItem("Annuler perspective 2");
		JMenuItem enregistrer = new JMenuItem("Enregistrer sous...");
		JMenuItem ouvrir = new JMenuItem("Ouvrir...");

		enregistrer.addActionListener(actionEvent -> enregistrer());
		ouvrir.addActionListener(actionEvent -> ouvrirFichier());
		undo1.addActionListener(actionEvent -> retablir(perspective1));
		undo2.addActionListener(actionEvent -> retablir(perspective2));

		menu.add(undo1);
		menu.add(undo2);
		menu.add(ouvrir);
		menu.add(enregistrer);

		barreMenu.add(menu);

		this.add(barreMenu);

	}

	/**
	 * Methode responsable de faire le undo sur la perspective
	 * @param mp, la perspective
	 */
	private void retablir(ModelePerspective mp) {
		GestionnaireCommandes gc = GestionnaireCommandes.getInstance();
		gc.undoCommande(mp);

	}

	/**
	 * Methode responsable d'enregistrer les perspectives dans un fichier avec les changements effectuer
	 */
	private void enregistrer() {
		// enregisrter les perspectives + image
		JFileChooser dossier = new JFileChooser();
		int returnValue = dossier.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			String dest = "";
			dest += dossier.getSelectedFile().getAbsolutePath();
			System.out.println(dest);

			ImageSauvegarde imgSave = new ImageSauvegarde(perspective1.getNiveauZoom(), perspective1.getTransformationCoordonnees(),
					perspective2.getNiveauZoom(), perspective2.getTransformationCoordonnees());
			try {
				FileOutputStream fileOutImage = new FileOutputStream(dest + ".ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOutImage);
				out.writeObject(imgSave);
				out.close();
				fileOutImage.close();
				JOptionPane.showMessageDialog(null, "Fichier enregistre avec succes", "Message d'information",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();

				JOptionPane.showMessageDialog(null, "Erreur lors de l'enregristrement du fichier\n" + e.getMessage(),
						"Message d'erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Methode responsable d'ouvrir un fichier enregister, contenant les perspectives
	 */
	private void ouvrirFichier() {
		// ouvrir le fichier sauvegarde
		JFileChooser fichier = new JFileChooser();
		fichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnValue = fichier.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			String src = "";
			src += fichier.getSelectedFile().getAbsolutePath();
			ImageSauvegarde imgSave = null;

			try {
				FileInputStream fileIn = new FileInputStream(src);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				imgSave = (ImageSauvegarde) in.readObject();
				in.close();
				fileIn.close();

				this.perspective1.setNiveauZoom(imgSave.getZoom1());
				this.perspective1.setZoomNoTranslate();
				this.perspective1.setTransformationCoordonnees(imgSave.getTranslation1());
				this.perspective1.notifyObservers();

				this.perspective2.setNiveauZoom(imgSave.getZoom2());
				this.perspective2.setZoomNoTranslate();
				perspective2.setTransformationCoordonnees(imgSave.getTranslation2());
				this.perspective2.notifyObservers();

			} catch (IOException | ClassNotFoundException i) {
				i.printStackTrace();
			}
		}
	}

	/**
	 * Methode qui detecte quand on clique avec la souris
	 * @param mouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {

		ModelePerspective mp = this.bindings.get(mouseEvent.getSource());
		mp.getSauvegardeNiveauxZoom().push(mp.getNiveauZoom());
		mp.getSauvegardePositionsZoom().push(mouseEvent.getPoint());
		GestionnaireCommandes gc = GestionnaireCommandes.getInstance();
		gc.ajouterCommande(new ZoomCommande(), mp);
		isZoom = true;
	}

	/**
	 * Methode qui detecte quand on aopuye su run composant avec la souris
	 * @param mouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		this.debut = mouseEvent.getPoint();
		ModelePerspective mp = this.bindings.get(mouseEvent.getSource());
		mp.setEcranDebutSouris(mouseEvent.getPoint());
		mp.setEcranFinSouris(null);
	}

	/**
	 * Methode qui detecte quand la souris est relacher
	 * @param mouseEvent
	 */
	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		if (!isZoom) {
			this.fin = mouseEvent.getPoint();
			ModelePerspective mp = this.bindings.get(mouseEvent.getSource());
			mp.getSauvegardePositions().push(new SimpleEntry<>(debut, fin));
			GestionnaireCommandes gc = GestionnaireCommandes.getInstance();
			gc.ajouterCommande(new TranslateCommande(), mp);
		}
		isZoom = false;
	}

	/**
	 * Methode qui detecte la presence de la souris sur un composant
	 * @param mouseEvent
	 */
	@Override
	public void mouseEntered(MouseEvent mouseEvent) {}

	/**
	 * Methode qui detecte la souris quite un composant
	 * @param mouseEvent
	 */
	@Override
	public void mouseExited(MouseEvent mouseEvent) {}

	/**
	 * Methode qui detecte quand la souris traine un composant
	 * @param mouseEvent
	 */
	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
		Vue source = (Vue) mouseEvent.getSource();
		ModelePerspective mp = this.bindings.get(source);
		GestionnaireCommandes gc = GestionnaireCommandes.getInstance();
		gc.executerCommande(new TranslateCommande(), mp, mouseEvent);
	}

	/**
	 * Methode qui detecte quand la souris bouge
	 * @param mouseEvent
	 */
	@Override
	public void mouseMoved(MouseEvent mouseEvent) {}

	/**
	 * Methode qui detecte quand la roulette de la souris roule
	 * @param mouseWheelEvent
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
		Vue source = (Vue) mouseWheelEvent.getSource();
		ModelePerspective mp = this.bindings.get(source);
		GestionnaireCommandes gc = GestionnaireCommandes.getInstance();
		gc.executerCommande(new ZoomCommande(), mp, mouseWheelEvent);
	}

}
