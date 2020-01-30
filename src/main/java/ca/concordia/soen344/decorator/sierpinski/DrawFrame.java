package ca.concordia.soen344.decorator.sierpinski;

import javax.swing.*;

public class DrawFrame extends JFrame {
    private static final int SIZE = 1000;

    public DrawFrame() {
        super();
        this.setTitle("Fractal");
        this.setVisible(true);
        this.setSize(SIZE, SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Triangle original = new Triangle(SIZE - 10);
        DecoratedTriangle child = new DecoratedTriangle(original);
        for (int i = 0; i < 5; i++) {
            child = new DecoratedTriangle(child);
        }

        JPanel panel = new DrawPanel(child);
        this.getContentPane().add(panel);
    }

    public static void main(String[] args) {
        new DrawFrame();
    }
}
