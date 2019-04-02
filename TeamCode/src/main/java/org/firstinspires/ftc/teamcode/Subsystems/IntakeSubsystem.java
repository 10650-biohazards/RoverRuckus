package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.adafruit.AdafruitBNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.BNO055Gyro;
import org.firstinspires.ftc.teamcode.Utility;

import FtcExplosivesPackage.Subsystem;

public class IntakeSubsystem extends Subsystem {

    public DcMotor intakeMotor;
    Utility u;
    OpMode op;

    public Servo lRotator, rRotator;
    public BNO055Gyro intakeGyro;

    public IntakeSubsystem(LinearOpMode op, DcMotor intakeMotor, Servo lRotator, Servo rRotator) {
        super(op);
        this.intakeMotor = intakeMotor;
        this.lRotator = lRotator;
        this.rRotator = rRotator;
        this.intakeGyro = intakeGyro;
        this.op = op;
        u = new Utility(op);
    }

    public void intake(){
        intakeMotor.setPower(0.2);
        u.waitMS(3000);
        intakeMotor.setPower(0);
    }

    public void outttake(){
        intakeMotor.setPower(-0.2);
        u.waitMS(3000);
        intakeMotor.setPower(0);
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void stop() {

    }
}