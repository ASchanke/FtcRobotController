package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;


//written for blue in position as shown in manual

@Autonomous(name="Auto OpMode", group="Linear Opmode")
public class AutoOpMode extends LinearOpMode {

    private MecanumWheels drive;
    private WobbleGoal wgoal;
    private Launcher launcher;
    private Loader loader;

    private DcMotor launchMotor;
    private DcMotor leftFrontMotor;
    private DcMotor rightBackMotor;
    private DcMotor rightFrontMotor;

    private Servo handServo;
    private Servo armServo;

    private Servo launchServo;
    private CRServo leftBackMotor;

    private CRServo loaderMotor;
    private CRServo loaderServo0;
    private CRServo loaderServo1;

    private boolean loaderOn;
    private boolean launcherOn;

    private ColorSensor sensorColor;

    private RingDetection ringDetector;

    // Declare OpMode members.
    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftBackMotor = hardwareMap.get(CRServo.class, "leftBackMotor");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");

        handServo = hardwareMap.get(Servo.class, "handServo");
        armServo = hardwareMap.get(Servo.class, "armServo");

        launchServo = hardwareMap.get(Servo.class, "launchServo");
        launchMotor = hardwareMap.get(DcMotor.class, "launchMotor");

        loaderMotor = hardwareMap.get(CRServo.class, "loaderMotor");
        loaderServo0 = hardwareMap.get(CRServo.class, "loaderMotor");
        loaderServo1 = hardwareMap.get(CRServo.class, "loaderServo1");

        sensorColor = hardwareMap.colorSensor.get("sensorColor");

        drive = new MecanumWheels(leftBackMotor, leftFrontMotor, rightBackMotor, rightFrontMotor);
        wgoal = new WobbleGoal(armServo, handServo);
        launcher = new Launcher(launchServo, launchMotor);
        loader = new Loader(loaderMotor, loaderServo0, loaderServo1);
        ringDetector = new RingDetection();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        timer.reset();
        ringDetector.startDetection();
        launcher.on();
        wgoal.raise();

        String ringPos = ringDetector.getPos();
        //String ringPos = "A";

        driveUntilLine(false, true);//color - RED = true, direction - Forward = true

        launcher.launch();
        launcher.launch();
        launcher.launch();

        if (ringPos.equals("A")){
            drive.drive(Math.PI/2, 1, 0);//7 feet forward
            timer.reset();
            while(timer.milliseconds() < 7000){}
            drive.drive(0, 1, 0);//1 foot right
            timer.reset();
            while(timer.milliseconds() < 1000){}
            drive.drive(0, 0, 0);
        }
        else if (ringPos.equals("B")){
            drive.drive(Math.PI/2, 1, 0);//9 feet forward
            timer.reset();
            while(timer.milliseconds() < 9000){}
            drive.drive(Math.PI, 1, 0);//0 feet left
            timer.reset();
            while(timer.milliseconds() < 0){}
            drive.drive(0, 0, 0);
        }
        else if (ringPos.equals("C")){
            drive.drive(Math.PI/2, 1, 0);//11 feet forward
            timer.reset();
            while(timer.milliseconds() < 11000){}
            drive.drive(0, 1, 0);//1 foot right
            timer.reset();
            while(timer.milliseconds() < 1000){}
            drive.drive(0, 0, 0);
        }
        wgoal.lower();

        driveUntilLine(false, false);


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + timer.toString());
            telemetry.update();
        }

    }

    // forward is true, back is false
    public void driveUntilLine(boolean isRed, boolean direction) {
        sensorColor.enableLed(true);
        if (isRed) {
            while (sensorColor.red() < 2){//test threshold
                if (direction) {
                    drive.drive(Math.PI/2, 1, 0);
                }
                else {
                    drive.drive(Math.PI/2, -1, 0);
                }
            }
        }
        else {
            while (sensorColor.blue() < 2) {//test threshold
                if (direction) {
                    drive.drive(Math.PI/2, 1, 0);
                }
                else {
                    drive.drive(Math.PI/2, -1, 0);
                }
            }
        }
        sensorColor.enableLed(false);
    }
}
