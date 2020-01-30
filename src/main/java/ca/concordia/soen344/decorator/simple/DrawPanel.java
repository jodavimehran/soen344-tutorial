package ca.concordia.soen344.decorator.simple;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    private final Triangle triangle;

    public DrawPanel(Triangle triangle) {
        super();
        this.triangle = triangle;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        triangle.draw(g);
    }
}
