package ca.concordia.soen344.decorator.sierpinski;

import java.awt.*;

public class DecoratedTriangle extends Triangle {

    private Triangle original;

    public DecoratedTriangle(Triangle original, Part part) {
        Point p4 = midpoint(original.p1, original.p2);
        Point p5 = midpoint(original.p2, original.p3);
        Point p6 = midpoint(original.p1, original.p3);
        switch (part) {
            case TOP:
                p1 = p4;
                p2 = original.p2;
                p3 = p5;
                break;
            case LEFT:
                p1 = original.p1;
                p2 = p4;
                p3 = p6;
                break;
            case RIGHT:
                p1 = p6;
                p2 = p5;
                p3 = original.p3;
                break;
        }
        this.original = original;
    }

    public static Point midpoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    public enum Part {
        TOP,
        RIGHT,
        LEFT
    }
}
