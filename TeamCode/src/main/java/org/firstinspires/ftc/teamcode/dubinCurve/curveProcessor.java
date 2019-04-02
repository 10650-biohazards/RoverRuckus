package org.firstinspires.ftc.teamcode.dubinCurve;

public class curveProcessor {

    public myArc firstArc;
    public myArc secondArc;
    public myStraight straight;

    public double slope;

    public double xDiff;
    public double yDiff;

    public curveProcessor() {

    }

    public void findCurves(Node start, Node end) {
        firstArc = new myArc(start, true);
        secondArc = new myArc(end, true);

        xDiff = secondArc.findCenter().x - firstArc.findCenter().x;
        yDiff = secondArc.findCenter().y - firstArc.findCenter().y;
        slope = Math.atan(yDiff / xDiff);

        RSR(start, end);
    }

    public void RSR(Node start, Node end) {
        firstArc.setLength((-slope + (Math.PI/2)) - start.ang);
        secondArc.setLength(Math.toRadians(end.ang) - (-slope + (Math.PI/2)));

        double dist = Math.sqrt(Math.pow(secondArc.fin().x - firstArc.fin().x, 2) + Math.pow(secondArc.fin().y - firstArc.fin().y, 2));

        straight = new myStraight(dist);
    }

    public void telemtry() {
        System.out.println("Slope: " + (-Math.toDegrees(slope) + 90));
        System.out.println("First Arc length: " + Math.toDegrees(firstArc.length));
        System.out.println("Second Arc length: " + Math.toDegrees(secondArc.length));

    }
}