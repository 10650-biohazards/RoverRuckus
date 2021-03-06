package org.firstinspires.ftc.teamcode.teleOpTracking;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.GagarinRobot;

import FtcExplosivesPackage.ExplosiveTele;

@TeleOp (name = "OPEN UP! FBI!")
public class driveTrack extends ExplosiveTele {

    GagarinRobot robot;
    DriveCommand drive;

    private double x = 0, y = 0;

    private double oldEnc = 0;

    private double oldTime;

    @Override
    public void initHardware() {
        robot = new GagarinRobot(this, hardwareMap);

        drive = new DriveCommand(this, robot.fleft, robot.fright, robot.bleft, robot.bright, robot.gyro);

        robot.bright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.bleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.fleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.bright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.fright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.bleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.fleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        drive.enable();
    }

    @Override
    public void initAction() {
        oldTime = System.currentTimeMillis();
    }

    @Override
    public void firstLoop() {

    }

    private double avgEnc() {
        double sum = 0;
        sum += robot.bright.getCurrentPosition();
        sum += robot.bleft.getCurrentPosition();
        sum += -robot.fright.getCurrentPosition();
        sum += -robot.fleft.getCurrentPosition();

        return sum / 4;
    }

    @Override
    public void bodyLoop() {
        double currAng = robot.gyro.getYaw();
        double currEnc = avgEnc();

        double deltaEnc = currEnc - oldEnc;

        y += Math.cos(Math.toRadians(currAng)) * deltaEnc;
        x += Math.sin(Math.toRadians(currAng)) * deltaEnc;

        oldEnc = currEnc;

        telemetry.addData("Current X position", " " + x / 1075);
        telemetry.addData("Current Y position", " " + y / 1075);
        telemetry.addData("Back right Encoder", " " + robot.bright.getCurrentPosition());
        telemetry.addData("Back left Encoder", "  " + robot.bleft.getCurrentPosition());
        telemetry.addData("Front left Encoder", " " + (-robot.fleft.getCurrentPosition()));
        telemetry.addData("Front right Encoder", "" + (-robot.fright.getCurrentPosition()));
        telemetry.addData("Gyro", "" + robot.gyro.getYaw());
        telemetry.update();
    }

    @Override
    public void exit() {

    }
}