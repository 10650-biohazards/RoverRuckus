package org.firstinspires.ftc.teamcode.Programs.Tele;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.SampleRevBlinkinLedDriver;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;
import org.firstinspires.ftc.teamcode.BNO055Gyro;
import org.firstinspires.ftc.teamcode.PID;
import org.firstinspires.ftc.teamcode.Subsystems.MarkerSubsystem;
import org.firstinspires.ftc.teamcode.Utility;

import java.util.concurrent.TimeUnit;

import FtcExplosivesPackage.ExplosiveAuto;

@TeleOp (name = "42 ^ 42")
public class TestTele extends ExplosiveAuto {

    double resetTime;

    double currPos = 0.5;

    double zoomZoom;

    private PID anglePID;
    private PID powerPID;

    BNO055Gyro intakeGyro;

    private PID pid = new PID();

    private ModernRoboticsI2cRangeSensor ultra;
    private Servo servo, servo2;

    private DcMotor motor;

    private Utility u = new Utility(this);

    private MarkerSubsystem mark;

    private final static int LED_PERIOD = 10;

    /*
     * Rate limit gamepad button presses to every 500ms.
     */
    private final static int GAMEPAD_LOCKOUT = 500;

    RevBlinkinLedDriver blinkinLedDriver;
    RevBlinkinLedDriver.BlinkinPattern pattern;

    Telemetry.Item patternName;
    Telemetry.Item display;
    DisplayKind displayKind;
    Deadline ledCycleDeadline;
    Deadline gamepadRateLimit;

    protected enum DisplayKind {
        MANUAL,
        AUTO
    }

    @Override
    public void initHardware() {
        anglePID = new PID();
        powerPID = new PID();

        ultra = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "SONIC THE HEDGEHOG");
        servo = hardwareMap.get(Servo.class, "drunkard servo");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        motor = hardwareMap.get(DcMotor.class, "motor");

        anglePID.setup(5, 0, 0, 270, 42, 3);

        mark = new MarkerSubsystem(this, servo);

        displayKind = DisplayKind.AUTO;

        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE;
        blinkinLedDriver.setPattern(pattern);

        display = telemetry.addData("Display Kind: ", displayKind.toString());
        patternName = telemetry.addData("Pattern: ", pattern.toString());

        ledCycleDeadline = new Deadline(LED_PERIOD, TimeUnit.SECONDS);
        gamepadRateLimit = new Deadline(GAMEPAD_LOCKOUT, TimeUnit.MILLISECONDS);

        intakeGyro = new BNO055Gyro(hardwareMap, "Crete");
    }

    @Override
    public void initAction() {
        zoomZoom = servo.getPosition();
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeGyro.initialize();
    }

    @Override
    public void body() throws InterruptedException {

        while (opModeIsActive()) {
            telemetry.addData("X", intakeGyro.x());
            telemetry.addData("Y", intakeGyro.y());
            telemetry.addData("Z", intakeGyro.z());
            telemetry.update();
        }

    }

    @Override
    public void exit() {

    }
}