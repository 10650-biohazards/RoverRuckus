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

//@Autonomous (name = "Experimental")
public class experimentalAuto extends ExplosiveAuto {

    private GagarinRobot robot;
    private boolean right = false;
    private boolean left = false;
    private boolean center = false;

    private boolean doubleS;
    private boolean craterS;
    private boolean fullSingle;

    private GoldAlignDetector detect = new GoldAlignDetector();
    private Utility u = new Utility(this);

    private DriveSubsystem drive;
    private LiftSubsystem lift;
    private MarkerSubsystem claim;

    //private lift_handler liftHandler;
    //private slide_handler slideHandler;

    private liftHandler liftH;

    @Override
    public void initHardware() {
        robot = new GagarinRobot(this);
        this.drive = robot.drive;
        //this.arm = robot.arm;
        //this.intake = robot.intake;
        this.lift = robot.lift;
        this.claim = robot.mark;

        //liftHandler = new lift_handler(lift, this, detect);
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
        detect.blindCamera(200);

        claim.enable();


        boolean buffer;
        boolean done = false;
        double resetTime = System.currentTimeMillis();
        while (!done && !isStopRequested()) {
            buffer = resetTime + 200 < System.currentTimeMillis();

            if (gamepad1.dpad_up && buffer) {
                doubleS = true;
                resetTime = System.currentTimeMillis();
            } else if (gamepad1.dpad_down && buffer) {
                doubleS = false;
                resetTime = System.currentTimeMillis();
            }

            telemetry.addData("Double Sample?", " ");
            telemetry.addData("Yes", doubleS ? "*" : " ");
            telemetry.addData("No ", !doubleS ? "*" : " ");
            telemetry.update();

            if (gamepad1.a & buffer) {
                done = true;
                resetTime = System.currentTimeMillis();
            }
        }

        done = false;
        resetTime = System.currentTimeMillis();
        while (!done) {
            buffer = resetTime + 200 < System.currentTimeMillis();

            if (gamepad1.dpad_up && buffer) {
                craterS = true;
                resetTime = System.currentTimeMillis();
            } else if (gamepad1.dpad_down && buffer) {
                craterS = false;
                resetTime = System.currentTimeMillis();
            }

            telemetry.addData("Crater Start?", " ");
            telemetry.addData("Yes", craterS ? "*" : " ");
            telemetry.addData("No ", !craterS ? "*" : " ");
            telemetry.update();

            if (gamepad1.a & buffer) {
                done = true;
                resetTime = System.currentTimeMillis();
            }
        }

        done = false;
        resetTime = System.currentTimeMillis();
        while (!done) {
            buffer = resetTime + 200 < System.currentTimeMillis();

            if (gamepad1.dpad_up && buffer) {
                fullSingle = true;
                resetTime = System.currentTimeMillis();
            } else if (gamepad1.dpad_down && buffer) {
                fullSingle = false;
                resetTime = System.currentTimeMillis();
            }

            telemetry.addData("Full Single Sampling?", " ");
            telemetry.addData("Yes", fullSingle ? "*" : " ");
            telemetry.addData("No ", !fullSingle ? "*" : " ");
            telemetry.update();

            if (gamepad1.a && buffer) {
                done = true;
                resetTime = System.currentTimeMillis();
            }
        }

        drive.gyro.startAng = craterS ? 225 : 135;
    }

    public void doubleSample() {
        if (right) {
            // turn towards second mineral
            drive.move_turn_gyro(290);
            drive.move_straight_PID(2300);
            // turn towards depot
            drive.move_turn_gyro(257);
        } else if (center) {
            drive.move_turn_gyro(340);
            drive.move_straight_PID(2000);
            drive.move_straight_PID(-2100);
            drive.move_turn_gyro(267);
        } else if (left) {
            drive.move_turn_gyro(185);
            drive.move_straight_PID(-3000);
            drive.move_straight_PID(3300);
            drive.move_turn_gyro(267);
        }
    }

    public void singleSampleToDepot() {
        if (right){
            telemetry.addData("RIGHT", " ");
            telemetry.addData("X-pos", detect.getXPosition());
            right = true;

            //angle towards cube
            drive.move_turn_gyro(260);
            //hit cube
            drive.move_straight_PID(3000);
            drive.move_straight_PID(-2500);
            //turn towards wall
            drive.move_turn_gyro(170);
            drive.move_straight_PID(4500);
            //turn around towards depot
            drive.move_turn_gyro(272);
            //towards depot
            drive.move_straight_PID(-4900);

        } else if (center) {
            telemetry.addData("CENTER", " ");
            telemetry.addData("X-pos", detect.getXPosition());
            center = true;

            drive.move_turn_gyro(240);
            drive.move_straight_PID(300);

            //hit the cube
            drive.move_turn_gyro(225);
            drive.move_straight_PID(2200);
            drive.move_straight_PID(-1800);
            //turn to wall
            drive.move_turn_gyro(170);
            drive.move_straight_PID(4000);
            //turn around
            drive.move_turn_gyro(272);
            //move toward depot
            drive.move_straight_PID(-4800,7000);

        } else {
            telemetry.addData("LEFT", " ");
            telemetry.addData("X-pos", detect.getXPosition());
            left = true;

            drive.move_turn_gyro(240);
            drive.move_straight_PID(300);

            drive.move_turn_gyro(185);
            drive.move_straight_PID(3000);

            drive.move_turn_gyro(150);
            drive.move_straight_PID(1250);

            drive.move_turn_gyro(272);
            drive.move_straight_PID(-5500, 7000);
        }
    }

    public void depotSample() {
        if (right){
            telemetry.addData("Gold Mineral: ", "RIGHT");

            drive.move_turn_gyro(170);
            drive.move_straight_PID(4000);
            drive.move_turn_gyro(90);
            drive.move_straight_PID(3500);
            drive.move_turn_gyro(0);
        } else if (center) {
            telemetry.addData("Gold Mineral: ", "CENTER");

            drive.move_turn_gyro(145);
            drive.move_straight_PID(200);
            drive.move_turn_gyro(135);
            drive.move_straight_PID(5500);
            drive.move_turn_gyro(0);
        } else {
            telemetry.addData("Gold Mineral: ", "LEFT");

            drive.move_turn_gyro(145);
            drive.move_straight_PID(200);
            drive.move_turn_gyro(115);

            drive.move_straight_PID(4500, 5000);
            drive.move_turn_gyro(0);
            drive.move_straight_PID(-1500);
        }
    }

    @Override
    public void body() {

        liftH.enable();

        lift.lower_robot();
        drive.gyro.resetPitch();

        right = detect.getXPosition() < 300 && detect.getXPosition() != 0;
        center = detect.getXPosition() > 300 && detect.getXPosition() != 0.0;

        detect.disable();

        if (craterS && fullSingle) {
            singleSampleToDepot();
        }

        if (!craterS) {
            depotSample();
        }

        claim.dump();
        u.waitMS(600);
        claim.raise();

        if (doubleS) {
            doubleSample();
        }

        if (fullSingle || !craterS) {
            drive.followWall(craterS ? 270 : 0, true);
        }
    }

    @Override
    public void exit() {

    }

}
