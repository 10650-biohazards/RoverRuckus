package org.firstinspires.ftc.teamcode.Programs.Tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GagarinRobot;
import org.firstinspires.ftc.teamcode.Hardware;

import FtcExplosivesPackage.ExplosiveTele;

@TeleOp(name = "Reset TeleOp")
public class resetTeleOp extends ExplosiveTele {

    private GagarinRobot h;

    public void lift() {
        telemetry.addData("LYFT Encoder", h.liftMotor.getCurrentPosition());

        if (Math.abs(gamepad2.right_stick_y) > 0.05) {
            h.liftMotor.setPower(gamepad2.right_stick_y);
        } else {
            h.liftMotor.setPower(0);
        }
    }

    public void slide() {
        h.slideMotor.setPower(gamepad2.left_stick_y);
    }

    public void intake() {
        double speed;

        if (gamepad2.dpad_up) {
            speed = 0.5;
        } else if (gamepad2.dpad_down) {
            speed = -0.5;
        } else {
            speed = 0;
        }
        h.lRotator.setPosition(0.5 - speed);
        h.rRotator.setPosition(0.5 + speed);
    }

    @Override
    public void initHardware() {
        h = new GagarinRobot(this, hardwareMap);
    }

    @Override
    public void initAction() {}

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {
        lift();
        slide();
        intake();
    }

    @Override
    public void exit() {}
}
