package ca.concordia.soen344.decorator.sierpinski;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DrawFrame extends JFrame {
    private static final int SIZE = 1000;

    public DrawFrame() {
        super();
        this.setTitle("Fractal");
        this.setVisible(true);
        this.setSize(SIZE, SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        List<Triangle> parents = new ArrayList<>();
        parents.add(new Triangle(SIZE - 10));

        for (int i = 0; i < 9; i++) {
            List<Triangle> children = new ArrayList<>();
            for (int j = 0; j < parents.size(); j++) {
                children.add(new DecoratedTriangle(parents.get(j), DecoratedTriangle.Part.LEFT));
                children.add(new DecoratedTriangle(parents.get(j), DecoratedTriangle.Part.RIGHT));
                children.add(new DecoratedTriangle(parents.get(j), DecoratedTriangle.Part.TOP));
            }
            parents.clear();
            parents.addAll(children);
        }

        JPanel panel = new DrawPanel(parents);
        this.getContentPane().add(panel);
    }

    public static void main(String[] args) {
        new DrawFrame();
    }
}
