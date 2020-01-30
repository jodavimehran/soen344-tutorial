package ca.concordia.soen344.decorator.simple;

import java.awt.*;

public class DecoratedTriangle extends Triangle {

    private Triangle original;

    public DecoratedTriangle(Triangle original) {
        super(midpoint(original.p1, original.p2),
                midpoint(original.p2, original.p3),
                midpoint(original.p1, original.p3));
        this.original = original;
    }

    public static Point midpoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

}
