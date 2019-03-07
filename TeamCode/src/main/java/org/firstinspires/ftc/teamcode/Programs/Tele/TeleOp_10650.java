package org.firstinspires.ftc.teamcode.Programs.Tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Hardware;


//@TeleOp(name = "no")
public class TeleOp_10650 extends OpMode {
    /**
     * set booleans and longs
     */
    private boolean number = true;
    boolean buffer2 = true;
    boolean high = true;
    private long resetTime = 0;
    long resetTime2 = 0;
    private Hardware h;
    private double MULTIPLIER = 1.0;

    @Override
    public void init() {
        h = new Hardware(this);
        resetTime = System.currentTimeMillis();
    }

    private void setMotors(){
        double rightPower = 0, leftPower = 0, rightPowerB = 0, leftPowerB = 0;


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

        if (Math.abs(gamepad1.left_stick_x) > 0.05) {
            leftPower += gamepad1.left_stick_x;
            rightPower -= gamepad1.left_stick_x;
            rightPowerB -= gamepad1.left_stick_x;
            leftPowerB += gamepad1.left_stick_x;
        }


        if (Math.abs(gamepad1.right_stick_x) > 0.05) {
            rightPower  -= gamepad1.right_stick_x;
            leftPowerB  -= gamepad1.right_stick_x;
            leftPower   += gamepad1.right_stick_x;
            rightPowerB += gamepad1.right_stick_x;
        }


        boolean buffer = System.currentTimeMillis() < resetTime + 1000;
        if(gamepad1.a && !buffer){
            resetTime = System.currentTimeMillis();
            number = !number;
        }
        if (!number) {
            MULTIPLIER *= 0.5;
        }
        if(number) {
            MULTIPLIER *= 1;
        }

        leftPower *= MULTIPLIER;
        leftPowerB *= MULTIPLIER;
        rightPower *= MULTIPLIER;
        rightPowerB *= MULTIPLIER;


        telemetry.addData("right power", rightPower);
        telemetry.addData("left power", leftPower);
        telemetry.addData("right power b", rightPowerB);
        telemetry.addData("left power b", leftPowerB);


        h.fright.setPower(rightPower);
        h.fleft.setPower(leftPower);
        h.bright.setPower(rightPowerB);
        h.bleft.setPower(leftPowerB);
    }


    public void lift(){

        telemetry.addData("LYFT Encoder", h.lift.getCurrentPosition());

        if(gamepad2.dpad_up){
            h.lift.setPower(1);
        } else if(gamepad2.dpad_down){
            h.lift.setPower(-1);
        } else {
            h.lift.setPower(0);
        }
    }

    /*
    public void intake(){
        if(gamepad2.dpad_up){
            h.intake.setPower(0.5);
        }
        else if(gamepad2.dpad_down){
            h.intake.setPower(-0.5);
        }
    }
    public void intakeFlap(){
        if(gamepad2.dpad_right){
            h.flap.setPower(0.4);
        }
        else if(gamepad2.dpad_left){
            h.flap.setPower(-0.4);
        }
    }
    /*public void arm() {
        double target = 42;
        double current = h.arm.getCurrentPosition();
        double e = target - current;
        final double P = 42;
        final double D = 42;
        if(gamepad2.a) {
            target+=7;
            h.arm.setPower(P*e+D);
        }
        if(gamepad2.b){
            target-=7;
            h.arm.setPower(P*e+D);
        }
         buffer2 = System.currentTimeMillis() < resetTime2 + 1000;
        if(gamepad1.a && !buffer2){
            resetTime2 = System.currentTimeMillis();
            high = !high;
        }
        if (!high && h.arm.getCurrentPosition() > 42) {
            h.arm.setPower(1);
        }
        else if (high && h.arm.getCurrentPosition() < 42) {
            h.arm.setPower(-1);
        }
        else {
            h.arm.setPower(0);
        }
    }*/


    /*public void extender() {
        double target = 42;
        double current = h.extender.getCurrentPosition();
        final double P = 42;
        final double D = 42;
        if (gamepad2.right_bumper) {
            target = 42;
        }
        if (gamepad2.left_bumper) {
            target = -42;
        }
        double e = target - current;
        h.extender.setPower(P * e + D);
    }*/

    @Override
    public void loop() {
        /*extender();
        arm();*/
        setMotors();
        lift();
        /*linearActuator();
        intake();
        intakeFlap();*/

        h.marker.setPosition(0.5);
    }
}