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

@SuppressWarnings("unused")
@Autonomous(name = "Depot Far Park", group = "Depot Start")
public class depot_auto_far extends ExplosiveAuto {

    private GagarinRobot robot;

    //private lift_handler liftHandler;
    //private slide_handler slideHandler;

    private liftHandler liftH;

    boolean left;
    boolean center;

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
        //this.arm = robot.arm;
        //this.intake = robot.intake;
        this.lift = robot.lift;
        this.claim = robot.mark;
        //liftHandler = new lift_handler(robot.lift, this, detect);
        //slideHandler = new slide_handler(robot.arm, this);
        liftH = new liftHandler(lift, this);
    }

    @Override
    public void initAction() {
        claim.raise();

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

        drive.gyro.startAng = 135;
    }

    @Override
    public void body() {
        //liftHandler.enable();
        //slideHandler.enable();
        //liftH.enable();

        lift.lower_robot();
        drive.gyro.resetPitch();

        left = detect.getXPosition() < 200 && detect.getXPosition() != 0;
        center = detect.getXPosition() > 200 && detect.getXPosition() < 500 && detect.getXPosition() != 0.0;

        detect.disable();

        if (left){
            telemetry.addData("Gold Mineral: ", "LEFT");

            drive.move_turn_gyro(145);
            drive.move_straight_PID(100);
            drive.move_turn_gyro(115);

            drive.move_straight_PID(2250, 5000);
            drive.move_turn_gyro(0);
            drive.move_straight_PID(-750);
        } else if (center) {
            telemetry.addData("Gold Mineral: ", "CENTER");

            drive.move_turn_gyro(145);
            drive.move_straight_PID(100);
            drive.move_turn_gyro(135);
            drive.move_straight_PID(2750);
            drive.move_turn_gyro(0);
        } else {
            telemetry.addData("Gold Mineral: ", "RIGHT");

            drive.move_turn_gyro(170);
            drive.move_straight_PID(2000);
            drive.move_turn_gyro(90);
            drive.move_straight_PID(1750);
            drive.move_turn_gyro(0);
        }

        claim.dump();
        u.waitMS(600);
        claim.raise();

        drive.move_turn_gyro(180);
        drive.followWall(180, false);
    }

    @Override
    public void exit() {}
}