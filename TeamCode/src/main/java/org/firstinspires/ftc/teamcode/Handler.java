package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class Handler implements Runnable {

    private double beginTime;
    private double endTime;
    public LinearOpMode op;

    public Handler(double beginTime, double endTime, LinearOpMode op) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.op = op;
    }

    public void enable() {
        Thread t = new Thread(this);
        t.start();
    }

    public abstract void action();

    public abstract void noAction();

    @Override
    public void run() {
        double startTime = System.currentTimeMillis();
        while (op.opModeIsActive()) {
            if (startTime + beginTime < System.currentTimeMillis() && startTime + endTime > System.currentTimeMillis()) {
                action();
            } else if (startTime + endTime < System.currentTimeMillis()) {
                noAction();
            }
        }
    }
}