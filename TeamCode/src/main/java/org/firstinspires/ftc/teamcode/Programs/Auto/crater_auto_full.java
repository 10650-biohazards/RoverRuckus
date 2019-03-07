package org.firstinspires.ftc.teamcode.Programs.Auto;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.GagarinRobot;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MarkerSubsystem;
import org.firstinspires.ftc.teamcode.Utility;
import org.firstinspires.ftc.teamcode.autoHandlers.intakeHandler;
import org.firstinspires.ftc.teamcode.autoHandlers.liftHandler;
import org.firstinspires.ftc.teamcode.autoHandlers.slideHandler;

import FtcExplosivesPackage.ExplosiveAuto;

@SuppressWarnings("unused")
@Autonomous(name = "Crater Full Auto", group = "Crater Start")
public class crater_auto_full extends ExplosiveAuto {

    private GagarinRobot robot;
    private standardStart start;

    private GoldAlignDetector detect = new GoldAlignDetector();
    private Utility u = new Utility(this);

    private DriveSubsystem drive;
    private LiftSubsystem lift;
    private MarkerSubsystem claim;
    private ArmSubsystem arm;
    private IntakeSubsystem intake;

    private liftHandler liftH;
    private slideHandler slideH;
    private intakeHandler intakeH;

    @Override
    public void initHardware() {
        robot = new GagarinRobot(this);
        this.drive = robot.drive;
        this.arm = robot.arm;
        this.intake = robot.intake;
        this.lift = robot.lift;
        this.claim = robot.mark;

        liftH = new liftHandler(lift, this);
        slideH = new slideHandler(arm, this);
        intakeH = new intakeHandler(intake, this);

        start = new standardStart(drive);
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

        claim.enable();
    }

    @Override
    public void body() {
        //liftH.enable();
        //slideH.enable();
        intakeH.enable();

        lift.lower_robot();
        drive.gyro.resetPitch();

        if (detect.getXPosition() < 200 && detect.getXPosition() != 0) {
            start.mineralPos = standardStart.position.LEFT;
        } else if (detect.getXPosition() > 200 && detect.getXPosition() < 500 && detect.getXPosition() != 0.0) {
            start.mineralPos = standardStart.position.CENTER;
        } else {
            start.mineralPos = standardStart.position.RIGHT;
        }

        detect.disable();

        start.go();

        claim.dump();
        u.waitMS(600);

        if (start.mineralPos == standardStart.position.RIGHT) {
            // turn towards second mineral
            drive.move_turn_gyro(290);
            drive.move_straight_PID(1150);
            // turn towards depot
            drive.move_turn_gyro(257);
        } else if (start.mineralPos == standardStart.position.CENTER) {
            drive.move_turn_gyro(340);
            drive.move_straight_PID(1000);
            drive.move_straight_PID(-1050);
            drive.move_turn_gyro(267);
        } else if (start.mineralPos == standardStart.position.LEFT) {
            drive.move_turn_gyro(185);
            drive.move_straight_PID(-1500);
            drive.move_straight_PID(1650);
            drive.move_turn_gyro(267);
        }

        drive.followWall(270, true);
    }

    @Override
    public void exit() {

    }
}