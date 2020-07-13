package tp_notePartie2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlIngredients implements ActionListener {
    private ModeleCommande model;

    public ControlIngredients(ModeleCommande model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String ing = ((JButton)actionEvent.getSource()).getText();
        int i;
        switch(ing) {
            case "Fromage" : i=0;
                break;
            case "Champignons" : i=1;
                break;
            case "Chorizo" : i=2;
                break;
            case "Oeuf" : i=3;
                break;
            case "Oignons" : i=4;
                break;
            case "Olives noires" : i=5;
                break;
            case "Olives vertes" : i=6;
                break;
            case "Roquette" : i=7;
                break;
            default : i=-1;
        }
        this.model.choixIngredient(i);
    }
}
