package vues;

import modele.ImageModel;

import javax.swing.*;
import java.awt.*;


public class PanneauPrincipal extends JPanel {

        private final ImageModel modelImage;


        PanneauPrincipal(ImageModel modelImage){
            this.modelImage = modelImage;
        }

        public void paintVignette(Graphics g){
            if (modelImage.isImageReady()) {
                g.drawImage(modelImage.getImage(), 860,80, this);
            }
        }

        public void paint(Graphics g){
                super.paint(g);
                g.drawRect(850,75,270,270);
                paintVignette(g);
            }
}


