package org.firstinspires.ftc.teamcode.Programs.Tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BNO055Gyro;

import FtcExplosivesPackage.ExplosiveTele;

@TeleOp (name = "Yee")
public class Test extends ExplosiveTele {

    BNO055Gyro gyro;

    @Override
    public void initHardware() {
        gyro = new BNO055Gyro(hardwareMap, "imu");

        //DriveCommand drive = new DriveCommand(this, robot.fleft, robot.fright, robot.bleft, robot.bright, robot.gyro);
        //ArmCommand arm = new ArmCommand(this, robot.slideMotor, robot.rackMotor, robot.potent);

        //drive.enable();
        //arm.enable();
    }

    @Override
    public void initAction() {

    }

    @Override
    public void firstLoop() {

    }

    @Override
    public void bodyLoop() {
        telemetry.addData("BOI", gyro.x());
        telemetry.update();
    }

    @Override
    public void exit() {

    }
}