package org.firstinspires.ftc.teamcode.autoHandlers;

import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Handler;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;

public class liftHandler extends Handler {

    LiftSubsystem lift;

    public liftHandler(LiftSubsystem lift, LinearOpMode op) {
        super(10000, 15000, op);
        this.lift = lift;
    }

    @Override
    public void action() {
        lift.liftMotor.setPower(1);
    }

    @Override
    public void noAction() {
        lift.liftMotor.setPower(0);
    }
}
