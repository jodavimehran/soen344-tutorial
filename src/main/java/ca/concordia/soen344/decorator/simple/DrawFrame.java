package ca.concordia.soen344.decorator.simple;

import javax.swing.*;

public class DrawFrame extends JFrame {
    private static final int SIZE = 1000;

    public DrawFrame() {
        super();
        this.setTitle("Fractal");
        this.setVisible(true);
        this.setSize(SIZE, SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new DrawPanel(new Triangle(SIZE-20));
        this.getContentPane().add(panel);
    }

    public static void main(String[] args) {
        new DrawFrame();
    }
}
