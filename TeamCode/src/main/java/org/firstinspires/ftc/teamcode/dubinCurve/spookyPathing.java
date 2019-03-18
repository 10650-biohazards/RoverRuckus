package org.firstinspires.ftc.teamcode.dubinCurve;

import org.firstinspires.ftc.teamcode.dubinCurve.Node;
import org.firstinspires.ftc.teamcode.dubinCurve.myArc;

public class spookyPathing {

    static Node start = new Node(0, 0, 0);
    static Node end = new Node(4,0, 0);

    static myArc firstArc = new myArc(start, true);
    static myArc secondArc = new myArc(end, true);

    public static void main(String args[]) {
        double xDiff = secondArc.findCenter().x - firstArc.findCenter().x;
        double yDiff = secondArc.findCenter().y - firstArc.findCenter().y;
        double slope = Math.atan(yDiff / xDiff);

        firstArc.setLength((-slope + (Math.PI/2)) - start.ang);
        secondArc.setLength(end.ang - (-slope + (Math.PI/2)));

        System.out.println("Slope: " + (-Math.toDegrees(slope) + 90));
        System.out.println("First Arc: " + Math.toDegrees(firstArc.length));
        System.out.println("Second Arc: " + Math.toDegrees(secondArc.length));
    }
}