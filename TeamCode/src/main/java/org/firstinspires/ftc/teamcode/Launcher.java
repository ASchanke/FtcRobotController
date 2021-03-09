package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Launcher {
    private Servo servo;
    private ElapsedTime timer;
    private DcMotor motor;
    private DcMotor CRs;

    public Launcher(Servo servo, DcMotor CRs) {
        this.servo = servo;
        this.CRs = CRs;
        CRs.setDirection(DcMotorSimple.Direction.FORWARD);
        CRs.setPower(0.0);;
    }
    public void on() {
        CRs.setPower(1.0);
    }

    public void launch() {
        servo.setPosition(1);
        timer.reset();
        while(timer.milliseconds() < 2000){}
        servo.setPosition(0);
        while(timer.milliseconds() < 2000){}//need to make this async so it doesnt interfere with other stuff
    }

    public void off() {
        CRs.setPower(0.0);
    }

}
