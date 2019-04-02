package org.firstinspires.ftc.teamcode.dubinCurve;

public class myArc {

    final double RADIUS = 0.5;
    Node startNode;
    public double length;
    boolean right;

    public myArc(Node startNode, boolean right) {
        this.startNode = startNode;
        this.right = right;
    }

    public myPoint findCenter() {
        double x = startNode.x;
        double y = startNode.y;

        x += RADIUS * Math.sin(Math.toRadians(startNode.ang) + (Math.PI/2));
        y += RADIUS * Math.sin(Math.toRadians(startNode.ang) + (Math.PI));

        return new myPoint(x, y);
    }

    public void setLength(double length) {
        this.length = length;
    }

    public myPoint fin() {
        double x, y;

        if (right) {
            y = findCenter().y + (Math.sin(Math.toRadians(startNode.ang) + length) * RADIUS);
            x = findCenter().x - (Math.cos(Math.toRadians(startNode.ang) + length) * RADIUS);
        } else {
            y = 0;
            x = 0;
        }

        return new myPoint(x, y);
    }
}
