package org.firstinspires.ftc.teamcode.Programs.Tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.PID;

import FtcExplosivesPackage.ExplosiveTele;

@SuppressWarnings("unused")
//@TeleOp(name = "Gagarin TeleOp")
public class Gagarin_TeleOp extends ExplosiveTele {

    private mineral right_chamber = mineral.NULL;
    private mineral left_chamber = mineral.NULL;
    private PID liftPID = new PID();
    private PID armPID = new PID();
    private PID turnPID = new PID();
    private PID slidePID = new PID();
    private PID testPID = new PID();

    private boolean firstCycle = true;
    private double rightPower, leftPower, rightPowerB, leftPowerB;

    private int slideTarget = 0;

    private double servo = 0;

    private boolean number = true;
    private boolean number2 = true;
    private long resetTime = 0;
    private long resetTime2 = 0;
    private Hardware h;
    private double MULTIPLIER = 1.0;
    private double armTarg;
    private double startTime = System.currentTimeMillis();

    private double intakePow = 0;

    private double limit(double maVal, double miVal, double input) {
        return input > maVal ? maVal : input < miVal ? miVal : input;
    }

    private void hold_ang() {
        boolean buffer = System.currentTimeMillis() < resetTime + 1000;
        if(gamepad1.b && !buffer){
            resetTime = System.currentTimeMillis();
            number2 = !number2;
            firstCycle = true;

        }

        if (!number2) {
            if (firstCycle){
                double nomAng = h.gyro.getYaw();
                turnPID.setup(0.1, 0, 0, 0.2, 0.5, nomAng);
                firstCycle = false;
            }
            double modifier = turnPID.status(h.gyro.getYaw());

            rightPower += modifier;
            leftPower -= modifier;
            rightPowerB += modifier;
            leftPowerB -= modifier;
        }
    }


    private void setMotors() {

        leftPower = 0;
        leftPowerB = 0;
        rightPower = 0;
        rightPowerB = 0;

        //forward
        if (Math.abs(gamepad1.left_stick_y) <= 0.1) {
            leftPower = 0;
            leftPowerB = 0;
            rightPower = 0;
            rightPowerB = 0;
        } else {
            rightPower = gamepad1.left_stick_y;
            leftPower = gamepad1.left_stick_y;
            rightPowerB = gamepad1.left_stick_y;
            leftPowerB = gamepad1.left_stick_y;
        }


        //strafe
        if (Math.abs(gamepad1.left_stick_x) > 0.05) {
            leftPower += gamepad1.left_stick_x;
            rightPower -= gamepad1.left_stick_x * 0.965;
            rightPowerB += gamepad1.left_stick_x * 0.99;
            leftPowerB -= gamepad1.left_stick_x * 0.96;
        }

        //turn
        if (Math.abs(gamepad1.right_stick_x) > 0.05) {
            rightPower  += gamepad1.right_stick_x;
            leftPowerB  -= gamepad1.right_stick_x;
            leftPower   -= gamepad1.right_stick_x;
            rightPowerB += gamepad1.right_stick_x;
        }

        hold_ang();


        boolean buffer = System.currentTimeMillis() < resetTime + 1000;
        if(gamepad1.a && !buffer){
            resetTime = System.currentTimeMillis();
            number = !number;

        }
        if (!number) {
            MULTIPLIER = 0.5;
        }
        if(number) {
            MULTIPLIER = 1;
        }

        leftPower *= MULTIPLIER;
        leftPowerB *= MULTIPLIER;
        rightPower *= MULTIPLIER;
        rightPowerB *= MULTIPLIER;

        h.fright.setPower(-rightPower);
        h.fleft.setPower(-leftPower);
        h.bright.setPower(rightPowerB);
        h.bleft.setPower(leftPowerB);
    }

    public void lift() {
        telemetry.addData("LYFT Encoder", h.lift.getCurrentPosition());


        if(gamepad2.a){
            h.lift.setPower(1);
        } else if(gamepad2.b){
            h.lift.setPower(-1);
        } else {
            h.lift.setPower(0);
        }
    }

    private void slide() {
        final int SLIDE_CHANGE_RATE = 10;

        if (Math.abs(gamepad2.left_stick_y) > 0.05) {
            slideTarget += gamepad2.left_stick_y * SLIDE_CHANGE_RATE;
        }

        slidePID.setup(0.0005,0, 0, 0.2,42, slideTarget);

        h.slideMotor.setPower(gamepad2.left_stick_y);
    }


    public void arm() {
        double currAng = h.potent.getVoltage();

        double ARM_CHANGE_RATE = 0.05;

        if (gamepad2.dpad_up) {
            armTarg -= ARM_CHANGE_RATE;
        } else if (gamepad2.dpad_down) {
            armTarg += ARM_CHANGE_RATE;
        }

        telemetry.addData("Arm Position", h.potent.getVoltage());
        telemetry.addData("Arm Target", armTarg);

        armTarg = limit(1.14, .75, armTarg);

        armPID.setup(3, 0, 0, 0.15, 0.01, armTarg);
        if (!armPID.done()) {
            h.arm.setPower(armPID.status(currAng));
        }
    }

    private void automated_ejection_system() {

        telemetry.addData("Right", "");
        telemetry.addData("distance", h.AES_dist_r.getDistance(DistanceUnit.INCH));
        telemetry.addData("blue", h.AES_color_r.blue());
        telemetry.addData("red", h.AES_color_r.red());
        telemetry.addData("green", h.AES_color_r.green());
        telemetry.addData("mineral", right_chamber);

        telemetry.addData("Left", "");
        telemetry.addData("distance", h.AES_dist_l.getDistance(DistanceUnit.INCH));
        telemetry.addData("blue", h.AES_color_l.blue());
        telemetry.addData("red", h.AES_color_l.red());
        telemetry.addData("green", h.AES_color_l.green());
        telemetry.addData("mineral", left_chamber);

        if (h.AES_dist_r.getDistance(DistanceUnit.INCH) < 3) {
            if (h.AES_color_r.blue() > 255) {
                right_chamber = mineral.SILVER;
            } else {
                right_chamber = mineral.GOLD;
            }
        } else {
            right_chamber = mineral.NULL;
        }

        if (h.AES_dist_l.getDistance(DistanceUnit.INCH) < 3) {
            if (h.AES_color_l.blue() > 255) {
                left_chamber = mineral.SILVER;
            } else {
                left_chamber = mineral.GOLD;
            }
        } else {
            left_chamber = mineral.NULL;
        }

        boolean bothOccupied = left_chamber != mineral.NULL && right_chamber != mineral.NULL;

        if (right_chamber != left_chamber && left_chamber != mineral.NULL && right_chamber != mineral.NULL){
            if (left_chamber == mineral.SILVER){
                h.left_trapdoor.setPosition(1);
                startTime = System.currentTimeMillis();
            }
            if (right_chamber == mineral.SILVER){
                h.right_trapdoor.setPosition(0);
                startTime = System.currentTimeMillis();
            }
        }

        if (System.currentTimeMillis() > startTime + 1000 && gamepad2.right_trigger < 0.05) {
            h.right_trapdoor.setPosition(1);
        }

        if (System.currentTimeMillis() > startTime + 1000 && gamepad2.left_trigger < 0.05) {
            h.left_trapdoor.setPosition(0);
        }

        if (gamepad2.right_trigger > 0.05) {
            h.right_trapdoor.setPosition(0);
        }

        if (gamepad2.left_trigger > 0.05) {
            h.left_trapdoor.setPosition(1);
        }
    }

    public void intake(){
        if (gamepad2.x) {
            h.intake.setPower(intakePow);
        } else if (gamepad2.y) {
            h.intake.setPower(-intakePow);
        } else {
            h.intake.setPower(0);
        }

        telemetry.addData("MORE POWER", intakePow);

        boolean buffer = System.currentTimeMillis() < resetTime2 + 200;

        if (gamepad2.right_bumper && !buffer) {
            resetTime2 = System.currentTimeMillis();
            intakePow += 0.1;
        } else if (gamepad2.left_bumper && !buffer) {
            resetTime2 = System.currentTimeMillis();
            intakePow -= 0.1;
        }
    }



    @Override
    public void initHardware() {
        h = new Hardware(this);
        resetTime = System.currentTimeMillis();

        armTarg = h.potent.getVoltage();

        h.bright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        h.bright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        h.fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        h.fright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        h.bleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        h.bleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        h.fleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        h.fleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void initAction() {}

    @Override
    public void firstLoop() {
        h.marker.setPosition(0.32);
        testPID.setup(0,0,2.5,0,1,10000);
        double startTime = -System.currentTimeMillis();
    }

    @Override
    public void bodyLoop() {
        setMotors();
        lift();
        intake();
        arm();
        slide();
        automated_ejection_system();

        telemetry.addData("potentiometer", h.potent.getVoltage());

        telemetry.addData("Right Back Power", rightPowerB);
        telemetry.addData("Right Power", rightPower);
        telemetry.addData("Left Back Power", leftPowerB);
        telemetry.addData("Left Power", leftPower);

        telemetry.addData("Right Back Encoder", h.bright.getCurrentPosition());
        telemetry.addData("Right Encoder", h.fright.getCurrentPosition());
        telemetry.addData("Left Back Encoder", h.bleft.getCurrentPosition());
        telemetry.addData("Left Encoder", h.fleft.getCurrentPosition());

        telemetry.addData("WHEEEEEEEEE", testPID.status(-System.currentTimeMillis() + startTime));
    }

    @Override
    public void exit() {}

    public enum mineral{
        SILVER,
        GOLD,
        NULL
    }
}