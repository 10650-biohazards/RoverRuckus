package org.firstinspires.ftc.teamcode.Programs.Auto;

import org.firstinspires.ftc.teamcode.GagarinRobot;
import org.firstinspires.ftc.teamcode.dubinCurve.Node;
import org.firstinspires.ftc.teamcode.dubinCurve.curveProcessor;

import FtcExplosivesPackage.ExplosiveAuto;

public class worldsAuto extends ExplosiveAuto{

    private GagarinRobot robot;
    private curveProcessor curve;

    private Node currentNode;

    @Override
    public void initHardware() {
        robot = new GagarinRobot(this);
        curve = new curveProcessor(robot.drive);
    }

    @Override
    public void initAction() {
        currentNode = new Node(42, 42, robot.gyro.getYaw());
    }

    @Override
    public void body() throws InterruptedException {
        curve.move(new Node(0, 0, robot.gyro.getYaw()), new Node(5, 5, 90));
    }

    @Override
    public void exit() throws InterruptedException {

    }
}
