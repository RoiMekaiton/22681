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

//        main thread
          mainmove(1, 1);

//        move(0.5, 1000);
//        move(-0.5, 1000);
//
//        moveLeftClaw(0.5, 1000);
//        moveLeftClaw(-0.5, 1000);
//
//        moveRightClaw(0.5, 1000);
//        moveRightClaw(-0.5, 1000);

//        moveElevator(0.5, 500);
//        moveElevator(-0.5, 500);

//        rotateLeft(0.5, 500); // in order to rotate 90 deg use time 500 for a full turn use time 1000
//        rotateRight(0.5, 500);
      }
    public void move (double power, int time) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
      }
    public void moveRightClaw (double power, int time) {
        rightClaw.setPower(power);
        sleep(time);
        rightClaw.setPower(0);
    }
    public void moveLeftClaw (double power, int time) {
        leftClaw.setPower(power);
        sleep(time);
        leftClaw.setPower(0);
    }
    public void moveElevator (double power, int time) {
        leftElevator.setPower(power);
        rightElevator.setPower(power);
        sleep(time);
        leftElevator.setPower(0);
        rightElevator.setPower(0);
    }
    public void rotateLeft (double power, int time) {
        backLeft.setPower(-power);
        frontLeft.setPower(-power);
        backRight.setPower(power);
        frontRight.setPower(power);
        sleep(time);
        backLeft.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);
    }
    public void rotateRight (double power, int time) {
        backLeft.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(-power);
        frontRight.setPower(-power);
        sleep(time);
        backLeft.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);
    }
    public void moveArm (double power, int time) {
        rightArm.setPower(power);
        leftArm.setPower(power);
        sleep(time);
        leftArm.setPower(0);
        rightArm.setPower(0);
    }
    public void mainmove (int time, int power) {
          move(0.5, 2500);
          rotateRight(0.5, 500);
          move(0.5, 1000);
          moveArm(0.05, 500);
    }
}