package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;

class MecanumWheels {

    // initializes the 4 local Powers & Motors, & sets them to 0
    private DcMotor frontRight;
    private double frontRightPower = 0;
    private DcMotor frontLeft;
    private double frontLeftPower = 0;
    private DcMotor backRight;
    private double backRightPower = 0;
    private DcMotor backLeft;
    private double backLeftPower = 0;

    public MecanumWheels (DcMotor leftBack, DcMotor leftFront, DcMotor rightBack, DcMotor rightFront){
    // sets the local motors to Graber's motors
    frontRight = rightFront;
    frontLeft = leftFront;
    backRight = rightBack;
    backLeft = leftBack;
    //sets the local motor's directions to forward
    frontLeft.setDirection(Direction.REVERSE);
    frontRight.setDirection(Direction.FORWARD);
    backLeft.setDirection(Direction.REVERSE);
    backRight.setDirection(Direction.FORWARD);
    }

    // sets the 4 local to what Graber gives when the method is called
    public void drive (double angle, double magnitude, double rotationalVelocity) {
        setBackRightPower(angle, magnitude, rotationalVelocity);
        setFrontLeftPower(angle, magnitude, rotationalVelocity);
        setBackLeftPower(angle, magnitude, rotationalVelocity);
        setFrontRightPower(angle, magnitude, rotationalVelocity);
        frontRight.setPower(frontRightPower);
        frontLeft.setPower(frontLeftPower);
        backRight.setPower(backRightPower);
        backLeft.setPower(backLeftPower);
    }

    // setters
    private void setFrontRightPower(double angle, double magnitude, double rotationalVelocity) {
        frontRightPower = ((Math.sin(angle - (Math.PI/4.0) )) * magnitude - rotationalVelocity)/2;
    }
    private void setFrontLeftPower(double angle, double magnitude, double rotationalVelocity) {
        frontLeftPower = (Math.sin(angle + (Math.PI/4.0) ) * magnitude + rotationalVelocity)/2;
    }
    private void setBackRightPower(double angle, double magnitude, double rotationalVelocity) {
        backRightPower = ((Math.sin(angle + (Math.PI/4.0) )) * magnitude - rotationalVelocity)/2;
    }
    private void setBackLeftPower(double angle, double magnitude, double rotationalVelocity) {
        backLeftPower = (Math.sin(angle - (Math.PI/4.0) ) * magnitude + rotationalVelocity)/2;
    }


}
