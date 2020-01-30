package ca.concordia.soen344.decorator.sierpinski;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawPanel extends JPanel {

    private List<Triangle> triangles;

    public DrawPanel(List<Triangle> triangles) {
        this.triangles = triangles;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Triangle t : triangles) {
            t.draw(g);
        }
    }
}
