package modele;


import java.awt.*;

public class ImageModel {
    private String imageChemin;
    private Image image;
    private boolean isImageReady = false;


    public void setImage(String chemin){
        imageChemin = chemin;
        image = Toolkit.getDefaultToolkit().getImage(chemin);
        isImageReady = true;
    }
    public Image getImage(){
        return image;
    }

    public boolean isImageReady() {
        return isImageReady;
    }


}
