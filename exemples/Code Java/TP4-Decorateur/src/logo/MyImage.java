package logo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class MyImage extends JPanel {
    BufferedImage image;
    Dimension size = new Dimension();

    public MyImage(String path) {
        try{
            this.image = ImageIO.read(new File(path));
            size.setSize(image.getWidth(), image.getHeight());
        }
        catch(Exception e)
        {
            System.out.println("Error creating image with path "+path);
            System.out.println(e);
        }            
    }
    
    

    protected void paintComponent(Graphics g) {
        int x = (getWidth() - size.width)/2;
        int y = (getHeight() - size.height)/2;
        g.drawImage(image, x, y, this);
    }

    

    public void display(){
        try{
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(new JScrollPane(this));
            f.setSize(image.getWidth(),image.getHeight());
            f.setLocation(10,10);
            f.setVisible(true);
        }
        catch(Exception e){}
    }
    
    

    public void paintOver(String otherimage, int decalx, int decaly){
    	BufferedImage newImage=null;
        
        try{
            newImage = ImageIO.read(new File(otherimage));
        }
        catch(Exception e){
            System.out.println("Error creating image with path "+otherimage);
            System.out.println(e);
        }
        
        int sizex = newImage.getWidth();
        int sizey = newImage.getHeight();
        
		Graphics g=this.image.getGraphics();
		g.drawImage(newImage, decalx, decaly, decalx+sizex, decaly+sizey, 0, 0, sizex, sizey, null);
		g.dispose();
    }
    
    

    public void textOver(String txt, int x, int y){     
        Graphics g=this.image.getGraphics();
        
		g.drawString(txt,x,y);
		g.dispose();
    }
}
