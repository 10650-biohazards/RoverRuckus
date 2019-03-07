package org.firstinspires.ftc.teamcode.autoHandlers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Handler;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;

public class slideHandler extends Handler {

    ArmSubsystem arm;

    public slideHandler(ArmSubsystem arm, LinearOpMode op) {
        super(27000, 30000, op);
        this.arm = arm;
    }

    @Override
    public void action() {
        arm.slide.setPower(1);
    }

    @Override
    public void noAction() {
        arm.slide.setPower(0);
    }
}
