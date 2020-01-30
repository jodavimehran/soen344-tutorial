package ca.concordia.soen344.decorator.simple;

import java.awt.*;

public class Triangle {
    protected Point p1, p2, p3;

    /**
     * @param side the size of a side of the triangle
     */
    public Triangle(int side) {
        // equation for the height of equilateral triangle. h = a * √3 / 2, where a is a side of the triangle
        int triangleHeight = (int) Math.round(side * Math.sqrt(3.0) / 2.0);
        p1 = new Point(0, triangleHeight);
        p2 = new Point(side / 2, 0);
        p3 = new Point(side, triangleHeight);
    }

}
