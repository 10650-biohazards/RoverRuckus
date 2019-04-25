package org.firstinspires.ftc.teamcode.dubinCurve;

public class spookyPathing {

    static Node start = new Node(0, 0, 90);
    static Node end = new Node(2,-1, 135);

    static curveProcessor curve = new curveProcessor(null);

    public static void main(String args[]) {
        curve.findCurves(start, end);
        curve.telemtry(start, end);
    }
}