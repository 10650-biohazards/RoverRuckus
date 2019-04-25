package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "Royal Blue")
public class robotTest extends OpMode {

    DcMotor lazyBum;

    @Override
    public void init() {
        lazyBum = hardwareMap.get(DcMotor.class, "motor");
    }

    @Override
    public void loop() {
        double power = gamepad1.left_stick_y;

        if (gamepad1.a) {
            lazyBum.setPower(1);
         }
    }
}
