package org.firstinspires.ftc.teamcode.vision;

import org.opencv.core.Mat;

public class mineralFollower {

    private Mat displayMat = new Mat();
    private Mat workingMat = new Mat();
    private Mat yellowMat  = new Mat();
    private Mat hierarchy  = new Mat();

    private boolean onScreen = false;
    private boolean offLeft  = false;
    private boolean offRight = false;

    private double targXPos = 0;
    private double targYPos = 0;

    //Variables for the acceptable box of the contour
    private double maxX = 0;
    private double minX = 0;
    private double maxY = 0;
    private double minY = 0;

    public mineralFollower() {

    }
}
