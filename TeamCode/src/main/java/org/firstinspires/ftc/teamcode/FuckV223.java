package org.firstinspires.ftc.teamcode;
// example
// import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="FuckV223", group="Autonomous")
public class FuckV223 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    DcMotor backLeft;
    DcMotor leftElevator;
    DcMotor rightElevator;
    DcMotor leftArm;
    DcMotor rightArm;
    CRServo leftClaw;
    CRServo rightClaw;


    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");

        frontLeft = hardwareMap.get(DcMotor.class, "FrontL"); // declaring the variables of the wheels
        frontRight = hardwareMap.get(DcMotor.class, "FrontR");
        backRight = hardwareMap.get(DcMotor.class, "BackR");
        backLeft = hardwareMap.get(DcMotor.class, "BackL");
        leftElevator = hardwareMap.get(DcMotor.class, "elevatorL");
        rightElevator = hardwareMap.get(DcMotor.class, "elevatorR");
        leftArm = hardwareMap.get(DcMotor.class, "armL");
        rightArm = hardwareMap.get(DcMotor.class, "armR");
        leftClaw = hardwareMap.get(CRServo.class, "clawL");
        rightClaw = hardwareMap.get(CRServo.class, "clawR");

        frontLeft.setDirection(DcMotor.Direction.REVERSE); // reversing the engines that spin to the wrong side.
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        leftElevator.setDirection(DcMotor.Direction.REVERSE);
        leftArm.setDirection(DcMotor.Direction.REVERSE);
        leftClaw.setDirection(DcMotor.Direction.REVERSE);

        leftElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        runtime.reset();
        waitForStart();

        moveForw(1, 5000);

        moveBack(2, 5000);
      }
    public void moveForw (int power, int time) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
      }
    public void moveBack (int power, int time) {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}