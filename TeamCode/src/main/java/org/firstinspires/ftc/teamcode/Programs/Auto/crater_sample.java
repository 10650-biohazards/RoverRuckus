package org.firstinspires.ftc.teamcode.Programs.Auto;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.GagarinRobot;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MarkerSubsystem;
import org.firstinspires.ftc.teamcode.Utility;
import org.firstinspires.ftc.teamcode.autoHandlers.liftHandler;

import FtcExplosivesPackage.ExplosiveAuto;

@SuppressWarnings({"unused", "local variable"})
@Autonomous(name = "Crater Simple", group = "Crater Start")
public class crater_sample extends ExplosiveAuto {

    private GagarinRobot robot;
    //private lift_handler lift_handler;
    //private slide_handler slide_handler;

    private liftHandler liftH;

    private boolean right = false;
    private boolean left = false;
    private boolean center = false;

    private GoldAlignDetector detect = new GoldAlignDetector();
    private Utility u = new Utility(this);

    private DriveSubsystem drive;
    //private ArmSubsystem arm;
    //private IntakeSubsystem intake;
    private LiftSubsystem lift;
    private MarkerSubsystem claim;

    @Override
    public void initHardware() {
        robot = new GagarinRobot(this);
        this.drive = robot.drive;
        this.lift = robot.lift;
        this.claim = robot.mark;
        //lift_handler = new lift_handler(lift, this, detect);
        //slide_handler = new slide_handler(robot.arm, this);
        liftH = new liftHandler(lift, this);
    }

    @Override
    public void initAction() {

        claim.raise();

        drive.gyro.startAng = 225;
        robot.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.bright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.bright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.fright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.bleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.bleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.fleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.fleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        detect.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detect.useDefaults();
        detect.enable();

        //blue line
        detect.blindCamera(320);
    }

    @Override
    public void body() {
        //lift_handler.enable();
        //slide_handler.enable();
        //liftH.enable();

        lift.lower_robot();

        drive.gyro.resetPitch();

        left = detect.getXPosition() < 200 && detect.getXPosition() != 0;
        center = detect.getXPosition() > 200 && detect.getXPosition() < 500 && detect.getXPosition() != 0.0;

        detect.disable();

        //splits depending on where the mineral is located
        if (left) {
            telemetry.addData("LEFT", " ");
            telemetry.addData("X-pos", detect.getXPosition());

            drive.move_turn_gyro(240);
            drive.move_straight_PID(100);

            drive.move_turn_gyro(195);
            drive.move_straight_PID(2000);
        } else if (center) {
            telemetry.addData("CENTER", " ");
            telemetry.addData("X-pos", detect.getXPosition());

            drive.move_turn_gyro(240);
            drive.move_straight_PID(100);

            drive.move_turn_gyro(225);
            drive.move_straight_PID(1250);
        } else {
            telemetry.addData("RIGHT", " ");
            telemetry.addData("X-pos", detect.getXPosition());

            drive.move_turn_gyro(265);
            drive.move_straight_PID(2000);
        }


    }

    @Override
    public void exit() {

    }
}