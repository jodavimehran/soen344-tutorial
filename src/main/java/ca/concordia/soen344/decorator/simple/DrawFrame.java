package ca.concordia.soen344.decorator.simple;

import javax.swing.*;

public class DrawFrame extends JFrame {
    private static final int SIZE = 500;

    public DrawFrame() {
        super();
        this.setTitle("Fractal");
        this.setVisible(true);
        this.setSize(SIZE, SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new DrawFrame();
    }
}
