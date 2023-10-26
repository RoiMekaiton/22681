// ahmed and salim

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;


@TeleOp(name="FuckV2", group="Linear Opmode")
// @Disabled
public class FuckV2 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor FrontLeft = hardwareMap.get(DcMotor.class, "FrontL"); // declaring the variables of the wheels
    DcMotor FrontRight = hardwareMap.get(DcMotor.class, "FrontR");
    DcMotor BackRight = hardwareMap.get(DcMotor.class, "BackR");
    DcMotor BackLeft = hardwareMap.get(DcMotor.class, "BackL");
    DcMotor arm = hardwareMap.get(DcMotor.class, "arm");

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        FrontLeft.setDirection(DcMotor.Direction.REVERSE); // reversing the wheels that are wired backwards
        BackLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double x = gamepad1.left_stick_x; // left stick x is responsible for the robots horizontal movement
            double y = - gamepad1.left_stick_y; // left stick y is responsible for the robots forward and backward movement
            double rot = gamepad1.right_stick_x; // right stick yx is responsible for the rotation of the robot
            double armPower = - gamepad2.left_stick_y;

            double frontRightPower = y - x - rot;
            double frontLeftPower = y + x + rot;
            double backRightPower = y + x - rot;
            double backLeftPower = y - x + rot;
            // math...

            double norm = Math.max(Math.max(Math.abs(frontRightPower), Math.abs(frontLeftPower)), Math.max(Math.abs(backRightPower), Math.abs(backLeftPower)));
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

            arm.setPower(armPower / 2);


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}