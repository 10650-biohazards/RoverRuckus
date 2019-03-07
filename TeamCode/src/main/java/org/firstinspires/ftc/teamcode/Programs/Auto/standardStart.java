package org.firstinspires.ftc.teamcode.Programs.Auto;

import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;

import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;

public class standardStart {

    private DriveSubsystem drive;

    private GoldAlignDetector detect = new GoldAlignDetector();
    public position mineralPos = null;

    public standardStart(DriveSubsystem drive) {
        this.drive = drive;
    }

    public void go() {
        //splits depending on where the mineral is located
        if (mineralPos == position.LEFT){
            drive.move_turn_gyro(240);
            drive.move_straight_PID(150);

            drive.move_turn_gyro(185);
            drive.move_straight_PID(1500);

            drive.move_turn_gyro(150);
            drive.move_straight_PID(625);

            drive.move_turn_gyro(270);
            drive.move_straight_PID(-2750, 7000);

        } else if (mineralPos == position.CENTER) {
            drive.move_turn_gyro(240);
            drive.move_straight_PID(150);

            //hit the cube
            drive.move_turn_gyro(225);
            drive.move_straight_PID(1100);
            drive.move_straight_PID(-900);
            //turn to wall
            drive.move_turn_gyro(167);
            drive.move_straight_PID(2000);
            //turn around
            drive.move_turn_gyro(270);
            //move toward depot
            drive.move_straight_PID(-2400,7000);

        } else {
            //angle towards cube
            drive.move_turn_gyro(260);
            //hit cube
            drive.move_straight_PID(1250);
            drive.move_straight_PID(-1000);
            //turn towards wall
            drive.move_turn_gyro(170);
            drive.move_straight_PID(2100);
            //turn around towards depot
            drive.move_turn_gyro(270);
            //towards depot
            drive.move_straight_PID(-2450);
        }
    }

    public enum position {
        RIGHT,
        CENTER,
        LEFT
    }
}
