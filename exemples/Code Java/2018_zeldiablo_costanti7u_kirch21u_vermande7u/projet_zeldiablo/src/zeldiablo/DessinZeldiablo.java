package zeldiablo;

import moteurJeu.DessinJeu;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DessinZeldiablo implements DessinJeu {

    public static final int TAILLE = 40;

    private JeuZeldiablo jeu;

    public DessinZeldiablo(JeuZeldiablo zel){
        jeu = zel;
    }

    @Override
    public void dessiner(BufferedImage image) {
        // recupere un objet graphics sur l'image
        // c'est l'equivalent d'un crayon avec lequel on peut dessiner
        Graphics2D g = (Graphics2D) image.getGraphics();

        g.setColor(Color.PINK);
        g.fillOval(jeu.getPerso().getX()*TAILLE, jeu.getPerso().getY()*TAILLE, TAILLE, TAILLE);

        g.setColor(Color.BLACK);
        Case cases[][] = jeu.getLaby().getTab_cases();
        for(int i = 0; i < cases.length; i++){
            for(int j = 0; j < cases[i].length; j++){
                if(cases[i][j].estMur()){
                    g.fillRect(i*TAILLE, j*TAILLE, TAILLE, TAILLE);
                }
            }
        }
    }
}
