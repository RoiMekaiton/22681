package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
// import com.qualcomm.robotcore.util.Range;
import java.lang.Math;


@TeleOp(name="Fuck", group="Linear Opmode")
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
        DcMotor arm = hardwareMap.get(DcMotor.class, "arm");

        FrontLeft.setDirection(DcMotor.Direction.REVERSE); // reversing the wheels that are wired backwards
        BackLeft.setDirection(DcMotor.Direction.REVERSE);
        RightElevator.setDirection(DcMotor.Direction.REVERSE); 

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            Telemetry.addData("Left elevator Ticks:", leftElevator.getCurrentPosition()); // we want these to track the
            Telemetry.addData("Right elevator Ticks:", rightElevator.getCurrentPosition()); // elevators position.
            
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

            FrontLeft.setPower(frontLeftPower);
            FrontRight.setPower(frontRightPower);
            BackRight.setPower(backRightPower);
            BackLeft.setPower(backLeftPower);

            LeftElevator.setPower(elevatorPower / 2);
            RightElevator.setPower(elevatorPower / 2);

            if (gamepad2.a)
            {
                rightElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                leftElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                leftElevator.setTargetPostion(0); rightElevator.setTargetPostion(0); // 0 is a placeholder
                leftElevator.setPower(0.5); rightElevator.setPower(0.5);
                leftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                leftElevator.setTargetPostion(0); rightElevator.setTargetPostion(0); // 0 is a placeholder
                leftElevator.setPower(0.1); rightElevator.setPower(0.1);
                leftElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // leftElevator.setMode();
                // rightElevator.setMode(); need to put in the command that gives the motor back controller access
            }
            
            arm.setPower(armPower);
            
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}