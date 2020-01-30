package ca.concordia.soen344.decorator.simple;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Polygon p = new Polygon();
        p.addPoint(0, 400);
        p.addPoint(200, 0);
        p.addPoint(400, 400);
        g.drawPolygon(p);
    }
}
