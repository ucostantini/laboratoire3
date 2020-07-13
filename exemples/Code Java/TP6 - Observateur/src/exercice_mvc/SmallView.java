package exercice_mvc;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class SmallView extends JTextField implements Observer {

    public SmallView(int i) {
        super(i);
    }

    @Override
    public void update(Observable observable, Object o) {
        this.setText(((Model)observable).getMin());
    }
}
