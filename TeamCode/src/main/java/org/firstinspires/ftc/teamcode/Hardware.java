package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import FtcExplosivesPackage.ExplosiveNavX;

public class Hardware {

    /**
     * Motor creation
     */
    public DcMotor fright, fleft, bright, bleft, lift, slideMotor, intake, arm;
    public AnalogInput potent;
    public ColorSensor AES_color_r, AES_color_l;
    public DistanceSensor AES_dist_r, AES_dist_l;
    /**
     * Servo creation
     */
    public Servo marker, left_trapdoor, right_trapdoor;
    public ExplosiveNavX gyro;

    /**
     * @param OP initializing drive train motors
     */
    public Hardware(OpMode OP) {
        fright = OP.hardwareMap.get(DcMotor.class, "right");
        fleft = OP.hardwareMap.get(DcMotor.class, "left");
        bright = OP.hardwareMap.get(DcMotor.class, "rightB");
        bleft = OP.hardwareMap.get(DcMotor.class, "leftB");
        slideMotor = OP.hardwareMap.get(DcMotor.class, "Post-Progressive Jazz Funk");

        gyro = new ExplosiveNavX(OP, "41", 0);

        potent = OP.hardwareMap.get(AnalogInput.class, "Diamond in the Rough");

        /**
         * reversing motors
         */
        bleft.setDirection(DcMotor.Direction.REVERSE);
        fright.setDirection(DcMotor.Direction.REVERSE);

        AES_color_r = OP.hardwareMap.get(ColorSensor.class, "color right");
        AES_color_l = OP.hardwareMap.get(ColorSensor.class, "color left");

        AES_dist_r = OP.hardwareMap.get(DistanceSensor.class, "color right");
        AES_dist_l = OP.hardwareMap.get(DistanceSensor.class, "color left");


        /**
         * initializing mechanism motors & Servo
         */
        lift = OP.hardwareMap.get(DcMotor.class, "actuator");
        //extender = hardwareMap.get(DcMotor.class, "extender");
        intake = OP.hardwareMap.get(DcMotor.class, "intake");

        marker = OP.hardwareMap.get(Servo.class, "drunkard servo");
        arm = OP.hardwareMap.get(DcMotor.class, "ARMaan");
        left_trapdoor = OP.hardwareMap.get(Servo.class, "left trapdoor");
        right_trapdoor = OP.hardwareMap.get(Servo.class, "right trapdoor");
    }
}
