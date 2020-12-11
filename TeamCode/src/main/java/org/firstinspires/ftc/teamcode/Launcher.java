package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Launcher {
    private Servo servo;
    private ElapsedTime timer;
    private DcMotor motor;

    public Launcher(Servo servo, DcMotor motor ) {
        this.servo = servo;
        this.motor = motor;
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void on() {
        motor.setPower(1.0);
    }

    public void launch() {
        servo.setPosition(1);
        timer.reset();
        while(timer.milliseconds() < 2000){}
        servo.setPosition(0);
        while(timer.milliseconds() < 2000){}//need to make this async so it doesnt interfere with other stuff
    }

    public void off() {
        motor.setPower(0.0);
    }

}
