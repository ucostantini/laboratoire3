package controleur;

import modele.*;
import modele.Rectangle;
import vue.VueDessin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.*;

/**
 * PanneauChoix controleur principal contenant les menus, boutons et la vue
 */
public class PanneauChoix extends JPanel {

    private modele.DessinModele dModele;
    private VueDessin vDessin;
    private FigureColoree fc;
    private Color couleur;
    private JMenuBar jm;

    /**
     * constructeur du menu, des boutons, auditeurs et gere les evenements vis a vis de la vue
     *
     * @param vd the vd
     * @param p  the p
     */
    public PanneauChoix(VueDessin vd, JPanel p) {


        this.vDessin = vd;
        this.dModele = vd.getDessin();

        JPanel p0 = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();


        //construction des menus
        JMenuBar jmb1 = new JMenuBar();
        JMenu jm1 = new JMenu("Fichier");
        JMenuItem jmi11 = new JMenuItem("Ouvrir");
        JMenuItem jmi12 = new JMenuItem("Enregistrer");
        JMenuItem jmi13 = new JMenuItem("Enregistrer sous");
        JMenuItem jmi14 = new JMenuItem("Quitter");

        JMenu jm2 = new JMenu("Edition");
        JMenuItem jmi21 = new JMenuItem("Effacer");

        jm1.add(jmi11);
        jm1.add(jmi12);
        jm1.add(jmi13);
        jm1.addSeparator();
        jm1.add(jmi14);

        jm2.add(jmi21);

        jmb1.add(jm1);
        jmb1.add(jm2);

        this.jm = jmb1;

        ActionListener al1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //on ouvre un fichier
                if (e.getSource() == jmi11) {
                    JFileChooser fichier = new JFileChooser();
                    fichier.setFileSelectionMode(JFileChooser.FILES_ONLY);

                    if (fichier.showOpenDialog(PanneauChoix.this) == JFileChooser.APPROVE_OPTION) {
                        String src = "";
                        src += fichier.getSelectedFile().getAbsolutePath();
                        PanneauChoix.this.load(src);
                    }
                }
                //on enregistre un fichier
                else if (e.getSource() == jmi12) {
                    PanneauChoix.this.save(".");
                }
                //on enregistre le fichier sous...
                else if (e.getSource() == jmi13) {
                    JFileChooser dossier = new JFileChooser();
                    dossier.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                    if (dossier.showOpenDialog(PanneauChoix.this) == JFileChooser.APPROVE_OPTION) {
                        String dest = "";
                        dest += dossier.getSelectedFile().getAbsolutePath();
                        System.out.println(dest);
                        PanneauChoix.this.save(dest);
                    }
                } else if (e.getSource() == jmi14) {
                    System.exit(0);
                } else if (e.getSource() == jmi21) {
                    PanneauChoix.this.dModele.getLfc().clear();
                    PanneauChoix.this.vDessin.repaint();
                }

            }
        };


        //construction des boutons
        JRadioButton b1 = new JRadioButton("Nouvelle figure");
        JRadioButton b2 = new JRadioButton("Tracé à main levée");
        JRadioButton b3 = new JRadioButton("Manipulations");
        ButtonGroup bg = new ButtonGroup();

        bg.add(b1);
        bg.add(b2);
        bg.add(b3);

        JComboBox<String> jcb1 = new JComboBox<>(new String[]{"Choisissez", "Quadrilatère", "Triangle", "Carre", "Rectangle", "Cercle", "Ellipse", "Losange"});
        JButton jb1 = new JButton("Couleur");

        jcb1.enable(false);

        this.couleur = Color.BLACK;

        ActionListener al2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //on supprime les auditeurs existants
                PanneauChoix.this.delListener();


                //on selectionne une couleur
                if (e.getSource() == jb1)
                    PanneauChoix.this.couleur = JColorChooser.showDialog(null, "couleur de la figure", Color.WHITE);


                //on dessine une figure
                if (b1.isSelected()) {

                    //on construit la figure
                    if (jcb1.getSelectedIndex() != 0) {
                        PanneauChoix.this.fc = choixFigure(jcb1.getSelectedIndex());
                        PanneauChoix.this.vDessin.construit(PanneauChoix.this.fc);
                        PanneauChoix.this.fc.changeCouleur(getCouleur());
                    }

                    jcb1.enable(true);

                    //on fait un tracé à main levée
                } else if (b2.isSelected()) {

                    PanneauChoix.this.vDessin.trace(PanneauChoix.this.couleur);
                    jcb1.enable(false);

                    //on manipule une figure
                } else if (b3.isSelected()) {

                    jcb1.enable(false);
                    PanneauChoix.this.vDessin.activeManipulationSouris();
                }
                p2.repaint();
            }
        };


        //ajout des auditeurs
        jmi11.addActionListener(al1);
        jmi12.addActionListener(al1);
        jmi13.addActionListener(al1);
        jmi14.addActionListener(al1);

        jmi21.addActionListener(al1);

        b1.addActionListener(al2);
        b2.addActionListener(al2);
        b3.addActionListener(al2);
        jb1.addActionListener(al2);
        jcb1.addActionListener(al2);


        //ajout des JPanel et des layouts
        p0.add(jmb1);

        p1.add(b1);
        p1.add(b2);
        p1.add(b3);

        p2.add(jcb1);
        p2.add(jb1);


        p.setLayout(new BorderLayout());
        p.add(p0, BorderLayout.NORTH);
        p.add(p1, BorderLayout.CENTER);
        p.add(p2, BorderLayout.SOUTH);

        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.add(p, BorderLayout.NORTH);
        jp.add(this.vDessin, BorderLayout.CENTER);

    }

    /**
     * Methode renvoyant la figure voulue par l utilisateur
     *
     * @param x index du JComboBox
     * @return la FigureColoree correspondant
     */
    private FigureColoree choixFigure(int x) {
        PanneauChoix.this.fc = null;
        switch (x) {
            case 0:
                break;
            case 1:
                PanneauChoix.this.fc = new Quadrilatere();
                break;
            case 2:
                PanneauChoix.this.fc = new Triangle();
                break;
            case 3:
                PanneauChoix.this.fc = new Carre();
                break;
            case 4:
                PanneauChoix.this.fc = new Rectangle();
                break;
            case 5:
                PanneauChoix.this.fc = new Cercle();
                break;
            case 6:
                PanneauChoix.this.fc = new Ellipse();
                break;
            case 7:
                PanneauChoix.this.fc = new Losange();
                break;

        }
        return (PanneauChoix.this.fc);
    }


    /**
     * Methode permettant d avoir la couleur de la figure
     *
     * @return la couleur
     */
    public Color getCouleur() {
        return this.couleur;
    }

    /**
     * Methode enregistrant les figures a un instant t
     *
     * @param destination le chemin de destination
     */
    private void save(String destination) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(destination + "/save.ihm", false));
            oos.writeObject(this.dModele);
            oos.close();
            JOptionPane.showMessageDialog(null, "Fichier enregistré avec succès", "Message d'information", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "Erreur lors de l'enregristrement du fichier\n"
                    + e.getMessage(), "Message d'erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Methode qui ouvre et recupere les figures d un fichier
     *
     * @param source la source du fichier
     */
    private void load(String source) {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(source));
            DessinModele dm = (DessinModele)(ois.readObject());
            this.dModele.setLfc(dm.getLfc());
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture du fichier\n"
                    + e.getClass().getSimpleName() + ": " + e.getMessage(), "Message d'erreur", JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (FigureColoree f : this.dModele.getLfc())
            VueDessin.nbFigures++;

        this.vDessin.repaint();
    }

    private void delListener() {
        MouseListener[] ml = this.vDessin.getMouseListeners();
        for (MouseListener aMl : ml) this.vDessin.removeMouseListener(aMl);
    }

    /**
     * Recupere le menu
     *
     * @return jm la JMenuBar
     */
    public JMenuBar getJm() {
        return this.jm;
    }
}
