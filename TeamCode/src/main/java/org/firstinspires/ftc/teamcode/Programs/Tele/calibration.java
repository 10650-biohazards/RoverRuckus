package org.firstinspires.ftc.teamcode.Programs.Tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GagarinRobot;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;

import FtcExplosivesPackage.ExplosiveTele;

@TeleOp (name = "dubi dubi doo")
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
        robot.bright.setPower(0);
        robot.fright.setPower(0);
        robot.bleft.setPower(gamepad1.left_stick_y);
        robot.fleft.setPower(-gamepad1.left_stick_y);
    }

    @Override
    public void exit() {

    }
}
