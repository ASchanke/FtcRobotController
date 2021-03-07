package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Loader {
    private CRServo motor;
    private CRServo servo0;
    private CRServo servo1;
    public Loader(CRServo motor, CRServo servo0, CRServo servo1) {
        this.motor = motor;
        this.servo0 = servo0;
        this.servo1 = servo1;
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setPower(0.0);
        servo1.setDirection(DcMotorSimple.Direction.FORWARD);
        servo1.setPower(0.0);
        servo0.setDirection(DcMotorSimple.Direction.FORWARD);
        servo0.setPower(0.0);
    }
    public void on() {
        motor.setPower(1.0);
        servo0.setPower(1.0);
        servo1.setPower(1.0);
    }
    public void off() {
        motor.setPower(0.0);
        servo0.setPower(0.0);
        servo1.setPower(0.0);
    }
}
