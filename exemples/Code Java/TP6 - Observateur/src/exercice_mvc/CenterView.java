package exercice_mvc;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class CenterView extends JTextArea implements Observer  {
    Model m;

    public CenterView(Model m, int i, int i1) {
        super(i,i1);
        this.m = m;
    }

    @Override
    public void update(Observable observable, Object o) {
        this.append(this.m.getList().getLast()+"\n");
    }
}
