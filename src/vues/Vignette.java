package vues;

import modeles.ModeleImage;

import javax.swing.*;
import java.awt.*;

public class Vignette extends JPanel implements Observateur {

   private ModeleImage modeleImage;


    public Vignette(ModeleImage image)
    {
        this.modeleImage = image;

        //this.setLayout(null);
        //this.setBounds(1000,70,150,200);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    public void setImage(ModeleImage modeleImage){
        this.modeleImage = modeleImage;
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g);
        if(modeleImage.getImage() != null){
            g.drawImage(modeleImage.getImage().getScaledInstance(150, 200, Image.SCALE_DEFAULT),0,0,this);
        }
    }

    @Override
    public void update()
    {
        repaint();
    }
}
