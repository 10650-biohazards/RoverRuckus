package org.firstinspires.ftc.teamcode.Subsystems;

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

        x += RADIUS * Math.sin(startNode.ang + (Math.PI/2));
        y += RADIUS * Math.sin(startNode.ang + (Math.PI));

        return new myPoint(x, y);
    }

    public void setLength(double length) {
        this.length = length;
    }
}
