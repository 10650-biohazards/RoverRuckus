package org.firstinspires.ftc.teamcode.Programs.Tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ArmCommand;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.LiftCommand;
import org.firstinspires.ftc.teamcode.Commands.MarkerCommand;
import org.firstinspires.ftc.teamcode.GagarinRobot;
import org.firstinspires.ftc.teamcode.PID;

import FtcExplosivesPackage.ExplosiveTele;

@TeleOp (name = "Florida State Championships")
public class States extends ExplosiveTele {

    public GagarinRobot robot;

    private double startTime;

    private PID testPID = new PID();

    @Override
    public void initHardware() {
        robot = new GagarinRobot(this, hardwareMap);

        ArmCommand arm = new ArmCommand(this, robot.slideMotor, robot.rackMotor, robot.potent);
        DriveCommand drive = new DriveCommand(this, robot.fleft, robot.fright, robot.bleft, robot.bright, robot.gyro);
        IntakeCommand intake = new IntakeCommand(this, robot.door, robot.intakeMotor, robot.lRotator, robot.rRotator, robot.potent);
        LiftCommand lift = new LiftCommand(this, robot.liftMotor);
        MarkerCommand mark = new MarkerCommand(this, robot.markServo);


        arm.enable();
        drive.enable();
        intake.enable();
        lift.enable();
        mark.enable();
    }

    @Override
    public void initAction() {
        robot.markServo.setPosition(0);
    }

    @Override
    public void firstLoop() {
        startTime = System.currentTimeMillis();
        testPID.setup(0,0,-3,0,1,10000);
    }

    @Override
    public void bodyLoop() {

    }

    @Override
    public void exit() {

    }
}