package org.firstinspires.ftc.teamcode;
// example
// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
// import com.qualcomm.robotcore.util.Range;
import java.lang.Math;


@TeleOp(name="FuckV2", group="Linear Opmode")
// @Disabled
public class FuckV2 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "FrontL"); // declaring the variables of the wheels
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "FrontR");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "BackR");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "BackL");
        DcMotor leftElevator = hardwareMap.get(DcMotor.class, "elevatorL");
        DcMotor rightElevator = hardwareMap.get(DcMotor.class, "elevatorR");
        DcMotor leftArm = hardwareMap.get(DcMotor.class, "armL");
        DcMotor rightArm = hardwareMap.get(DcMotor.class, "armR");

        frontLeft.setDirection(DcMotor.Direction.REVERSE); // reversing the engines that spin to the wrong side.
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        rightElevator.setDirection(DcMotor.Direction.REVERSE);
        rightArm.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

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
            } // assuming there is a value larger than 10 reducing all of the values for equal proportions

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);
            backLeft.setPower(backLeftPower);

            leftElevator.setPower(elevatorPower / 2);
            rightElevator.setPower(elevatorPower / 2);

            if (gamepad2.a)
            {
                rightElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                leftElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                leftElevator.setTargetPosition(0); rightElevator.setTargetPosition(0); // 0 is a placeholder
                leftElevator.setPower(0.5); rightElevator.setPower(0.5);
                leftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                leftElevator.setTargetPosition(0); rightElevator.setTargetPosition(0); // 0 is a placeholder
                leftElevator.setPower(0.1); rightElevator.setPower(0.1);
                leftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                leftElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                rightElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
            
            rightArm.setPower(armPower);
            leftArm.setPower(armPower);
            
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}