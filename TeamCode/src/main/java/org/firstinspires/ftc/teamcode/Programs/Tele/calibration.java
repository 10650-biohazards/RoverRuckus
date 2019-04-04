package org.firstinspires.ftc.teamcode.Programs.Tele;

import org.firstinspires.ftc.teamcode.GagarinRobot;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;

import FtcExplosivesPackage.ExplosiveTele;

public class calibration extends ExplosiveTele {

    GagarinRobot robot;

    @Override
    public void initHardware() {
        robot = new GagarinRobot(this, hardwareMap);
    }

    @Override
    public void initAction() {

    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {
        telemetry.addData("Encoder", robot.bright.getCurrentPosition());
    }

    @Override
    public void exit() {

    }
}
