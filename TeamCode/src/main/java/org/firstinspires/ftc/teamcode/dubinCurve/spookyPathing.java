package org.firstinspires.ftc.teamcode.dubinCurve;

import org.firstinspires.ftc.teamcode.dubinCurve.Node;
import org.firstinspires.ftc.teamcode.dubinCurve.myArc;

public class spookyPathing {

    static Node start = new Node(0, 0, 0);
    static Node end = new Node(10,0, 180);

    static myArc firstArc = new myArc(start, true);
    static myArc secondArc = new myArc(end, true);

    static curveProcessor curve = new curveProcessor();

    public static void main(String args[]) {
        double xDiff = secondArc.findCenter().x - firstArc.findCenter().x;
        double yDiff = secondArc.findCenter().y - firstArc.findCenter().y;
        double slope = Math.atan(yDiff / xDiff);



        firstArc.setLength((-slope + (Math.PI/2)) - start.ang);
        secondArc.setLength(Math.toRadians(end.ang) - (-slope + (Math.PI/2)));

        //System.out.println("Slope: " + (-Math.toDegrees(slope) + 90));
        //System.out.println("First Arc: " + Math.toDegrees(firstArc.length));
        //System.out.println("Second Arc: " + Math.toDegrees(secondArc.length));

        System.out.println("First " + firstArc.fin().x);
        System.out.println(firstArc.fin().y);

        curve.findCurves(start, end);
        System.out.println(curve.straight.length);
    }
}