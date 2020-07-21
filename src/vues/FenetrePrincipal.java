package vues;
import javax.imageio.ImageIO;
import javax.swing.*;

import modeles.ModeleImage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FenetrePrincipal extends JFrame {

    private Vignette vignette;
    private Vue vue;


    FenetrePrincipal() throws IOException{

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Lab3");
        this.setSize(1200,800);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        vignette = new Vignette();
        
        vue = new Vue();
        ModeleImage mImage = new ModeleImage(ImageIO.read(new File("C:\\Users\\Khanh\\Downloads\\PCIRBTH_Hero_Prinny.png")));
        
        vue.setImage(mImage);
        vignette.setImage(mImage);
        
        this.add(vignette);
        this.add(vue);
        this.setLayout(null);
        this.setVisible(true);

    }

}
