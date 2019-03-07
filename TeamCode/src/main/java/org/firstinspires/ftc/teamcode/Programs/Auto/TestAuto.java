package org.firstinspires.ftc.teamcode.Programs.Auto;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.LanderBracketDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.GagarinRobot;
import org.firstinspires.ftc.teamcode.PID;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MarkerSubsystem;
import org.firstinspires.ftc.teamcode.Utility;
import org.firstinspires.ftc.teamcode.autoHandlers.intakeHandler;

import FtcExplosivesPackage.ExplosiveAuto;

@Autonomous(name = "42^42")
public class TestAuto extends ExplosiveAuto {
    private GagarinRobot robot;

    Utility u = new Utility(this);

    private GoldAlignDetector detect = new GoldAlignDetector();

    PID testPID = new PID();

    private DriveSubsystem drive;
    private ArmSubsystem arm;
    private IntakeSubsystem intake;
    private LiftSubsystem lift;
    private MarkerSubsystem claim;

    intakeHandler intakeH;

    @Override
    public void initHardware() {
        robot = new GagarinRobot(this);
        this.drive = robot.drive;
        this.arm = robot.arm;
        this.intake = robot.intake;
        this.lift = robot.lift;
        this.claim = robot.mark;

        intakeH = new intakeHandler(intake, this);
    }

    @Override
    public void initAction() {

        drive.gyro.startAng = 0;
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
        detect.setSpeed(DogeCV.DetectionSpeed.BALANCED);
        detect.enable();

        detect.blindCamera(320);

        claim.enable();
    }

    @Override
    public void body() throws InterruptedException {
        intakeH.enable();

        //detect.enable();

        //drive.followWall(0, false);

        //drive.move_straight_PID(2000);
        //drive.move_straight_PID(-2000);

        //claim.dump();
        //u.waitMS(3000);

        //testPID.setup(0,0,1,0,1,10000);



        while (opModeIsActive()) {

            boolean left = detect.getXPosition() < 200 && detect.getXPosition() != 0;
            boolean center = detect.getXPosition() > 200 && detect.getXPosition() < 500 && detect.getXPosition() != 0.0;

            if (left) {
                telemetry.addData("LEFT", "");
            } else if (center) {
                telemetry.addData("CENTER", "");
            } else {
                telemetry.addData("RiGHT", "");
            }

            if (detect.bestRect != null) {
                telemetry.addData("WE GOT HIM", " ");
            } else {
                telemetry.addData("HE GOT AWAY", " ");
            }

            telemetry.addData("X", detect.getXPosition());
            telemetry.addData("Y", detect.getYPosition());
            telemetry.update();
        }


        /*ArrayList<Double> array = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            array.add(robot.ultra.getDistance(DistanceUnit.INCH));
        }

        while (this.opModeIsActive()) {
            array.add(robot.gyro.getYaw());
            array.remove(0);

            double total = 0;

            for (int i = 0; i < 10; i++) {
                total += array.get(i);
            }

            //this.telemetry.addData("no", total / 10);
            //this.telemetry.addData("yes", robot.liftMotor.getCurrentPosition());
            //this.telemetry.update();
        }
        */
    }

    @Override
    public void exit() throws InterruptedException { }
}