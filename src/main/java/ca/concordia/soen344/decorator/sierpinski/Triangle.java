package ca.concordia.soen344.decorator.sierpinski;

import java.awt.*;

public class Triangle {

    /**
     * Triangle points
     */
    protected Point p1, p2, p3;

    protected Triangle() {
    }

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

    /**
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    /**
     * @param g The graphics to draw this triangle
     */
    public void draw(Graphics g) {
        Polygon p = new Polygon();
        p.addPoint(p1.x, p1.y);
        p.addPoint(p2.x, p2.y);
        p.addPoint(p3.x, p3.y);
        g.drawPolygon(p);
    }
}
