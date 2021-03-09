package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class WobbleGoal {
    private Servo armServo;
    public WobbleGoal(Servo armServo) {
        this.armServo = armServo;
    }
    public void raise() {
        armServo.setPosition(0);

    }

    public void lower() {
        armServo.setPosition(1);

    }

}
