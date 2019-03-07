package org.firstinspires.ftc.teamcode.autoHandlers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Handler;
import org.firstinspires.ftc.teamcode.PID;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class intakeHandler extends Handler {

    boolean aBoolean = true;
    private IntakeSubsystem intake;
    private PID intakePID = new PID();

    private Orientation currOrient;
    private double currPitch;
    final private double startingPitch = 180;

    public intakeHandler(IntakeSubsystem intake, LinearOpMode op) {
        super(28500, 30000, op);
        this.intake = intake;
        intakePID.setup(0.05,0,0,0,0, 0);
        intake.intakeGyro.initialize();
    }

    public double refine(double input) {
        if (input < 0) {
            input *= -1;
            input -= 180;
            return input;
        } else {
            return input;
        }
    }

    @Override
    public void action() {

        if (aBoolean) {
            currPitch = intake.intakeGyro.x();
            aBoolean = currPitch < -130;
        } else {
            currPitch = refine(intake.intakeGyro.x());
        }


        double movingSpeed = intakePID.status(currPitch);

        intake.rRotator.setPosition(0.0);
        intake.lRotator.setPosition(1.0);

        op.telemetry.addData("PiTcH", currPitch);
        op.telemetry.addData("SpEeD", movingSpeed);
    }

    @Override
    public void noAction() {
        intake.lRotator.setPosition(0.5);
        intake.rRotator.setPosition(0.5);
    }
}