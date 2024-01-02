package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="FuckV3", group="Iterative OpMode FUCK")
// @Disabled
public class FuckV3 extends OpMode
{
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
    public void init() {
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
    }
    @Override
    public void loop() {
        telemetry.addData("Left elevator Ticks:", leftElevator.getCurrentPosition()); // returns to the driver hub
        telemetry.addData("Right elevator Ticks:", rightElevator.getCurrentPosition()); // the elevators tick position
        telemetry.addData("Front left direction:", frontLeft.getDirection());
        telemetry.addData("Front right direction:", frontRight.getDirection());
        telemetry.addData("Back left direction:", backLeft.getDirection());
        telemetry.addData("Back right direction:", backLeft.getDirection());

        double x = gamepad1.left_stick_x; // left stick x is responsible for the robots horizontal movement
        double y = - gamepad1.left_stick_y; // left stick y is responsible for the robots forward and backward movement
        double rot = gamepad1.right_stick_x; // right stick yx is responsible for the rotation of the robot
        double elevatorPower = - gamepad2.right_stick_y; // reponsible for the elevation of the elvator
        double armPower = - gamepad2.left_stick_y; // responsible for the movement of the arm

        double frontRightPower = y - x - rot;
        double frontLeftPower = y + x + rot;
        double backRightPower = y + x - rot;
        double backLeftPower = y - x + rot;
        // math in order to make the robot move to wherever the joystick points.

        double norm = Math.max(Math.max(Math.abs(frontRightPower), Math.abs(frontLeftPower)),
                Math.max(Math.abs(backRightPower), Math.abs(backLeftPower)));
        // checking whether there is a power value larger than 1

        if (norm > 1) {
            frontRightPower /= norm;
            frontLeftPower /= norm;
            backRightPower /= norm;
            backLeftPower /= norm;
        } // if theres a vallue larger than 1, reduces all of the values

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
        backLeft.setPower(backLeftPower);

        leftElevator.setPower(elevatorPower / 2);
        rightElevator.setPower(elevatorPower / 2);

        if(gamepad2.a) // elevator encoder code
        {
            elevatorReturn();
        }

        rightArm.setPower(armPower/2);
        leftArm.setPower(armPower/2);


        if(gamepad2.right_bumper)//claw open
        {
            telemetry.addData("SERVO: ","X IS BEING PRESSED");
            leftClaw.setPower(0.2);
            rightClaw.setPower(0.2);
        }
        else if(gamepad2.left_bumper)//claw close
        {

            telemetry.addData("SERVO: ","Y IS BEING PRESSED");
            leftClaw.setPower(-0.2);
            rightClaw.setPower(-0.2);
        }
        else
        {
            leftClaw.setPower(0);
            rightClaw.setPower(0);
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
    public void elevatorReturn()
    {
        rightElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if (rightElevator.getCurrentPosition() > 40 && leftElevator.getCurrentPosition() > 40) {
            leftElevator.setTargetPosition(40);
            rightElevator.setTargetPosition(40);
            leftElevator.setPower(0.5);
            rightElevator.setPower(0.5);
            leftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (rightElevator.getCurrentPosition() <= 40 && leftElevator.getCurrentPosition() <= 40)
        {
            leftElevator.setTargetPosition(0);
            rightElevator.setTargetPosition(0);
            leftElevator.setPower(0.2);
            rightElevator.setPower(0.2);
            leftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        else if (rightElevator.getCurrentPosition() == 0)
        {
            rightElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
}
