package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//FUCKV3 IS OUR CURRENT TELEOP
@TeleOp(name="FuckV3", group="Iterative OpMode FUCK")
// @Disabled
public class FuckV3 extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor frontL;
    DcMotor frontR;
    DcMotor backR;
    DcMotor backL;
    DcMotor elevatorL;
    DcMotor elevatorR;
    DcMotor armL;
    DcMotor armR;
    Servo clawArmL;
    Servo clawArmR;
    Servo clawL;
    Servo clawR;

    double ticks = 537.7;
    double newTarget = 0;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        frontL = hardwareMap.get(DcMotor.class, "frontL"); // declaring the variables of the wheels
        frontR = hardwareMap.get(DcMotor.class, "frontR");
        backR = hardwareMap.get(DcMotor.class, "backR");
        backL = hardwareMap.get(DcMotor.class, "backL");
        elevatorL = hardwareMap.get(DcMotor.class, "elevatorL");
        elevatorR = hardwareMap.get(DcMotor.class, "elevatorR");
        armL = hardwareMap.get(DcMotor.class, "armL");
        armR = hardwareMap.get(DcMotor.class, "armR");
        clawArmL = hardwareMap.get(Servo.class, "clawArmL");
        clawArmR = hardwareMap.get(Servo.class, "clawArmR");
        clawL = hardwareMap.get(Servo.class, "clawL");
        clawR = hardwareMap.get(Servo.class, "clawR");

        frontL.setDirection(DcMotor.Direction.REVERSE); // reversing the engines that spin to the wrong side.
        elevatorL.setDirection(DcMotor.Direction.REVERSE);

        //armR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //armL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //armR.setDirection(DcMotor.Direction.REVERSE);

        runtime.reset();
    }
    @Override
    public void loop() {
        //telemetry.addData("Left elevator Ticks:", elevatorL.getCurrentPosition()); // returns to the driver hub
        //telemetry.addData("Right elevator Ticks:", elevatorR.getCurrentPosition()); // the elevators tick position
        //telemetry.addData("Left elevator Ticks:", elevatorL.getPower()); // returns to the driver hub
        //telemetry.addData("Right elevator Ticks:", elevatorR.getPower()); // the elevators tick position

        telemetry.addData("left arm Ticks:", armL.getCurrentPosition());
        telemetry.addData("right arm Ticks:", armR.getCurrentPosition());

        double x = gamepad1.right_stick_x; // left stick x is responsible for the robots horizontal movement
        double y = - gamepad1.right_stick_y; // left stick y is responsible for the robots forward and backward movement
        double rot = gamepad1.left_stick_x; // right stick yx is responsible for the rotation of the robot
        double elevatorPower = - gamepad2.right_stick_y; // reponsible for the elevation of the elvator
        double armPower = - gamepad2.left_stick_y; // responsible for the movement of the arm

        double frontRPower = y - x - rot;
        double frontLPower = y + x + rot;
        double backRPower = y + x - rot;
        double backLPower = y - x + rot;
        // math in order to make the robot move to wherever the joystick points.

        double norm = Math.max(Math.max(Math.abs(frontRPower), Math.abs(frontLPower)),
                Math.max(Math.abs(backRPower), Math.abs(backLPower)));
        // checking whether there is a power value larger than 1

        if (norm > 1) {
            frontRPower /= norm;
            frontLPower /= norm;
            backRPower /= norm;
            backLPower /= norm;
        } // if theres a vallue larger than 1, reduces all of the values

        frontL.setPower(frontLPower);
        frontR.setPower(frontRPower);
        backR.setPower(backRPower);
        backL.setPower(backLPower);

        telemetry.addData("FrontL power", frontLPower);
        telemetry.addData("FrontR power", frontRPower);
        telemetry.addData("BackL power", backLPower);
        telemetry.addData("BackR power", backRPower);

        elevatorL.setPower(elevatorPower / 2);
        elevatorR.setPower(elevatorPower / 2);

        armL.setPower(armPower);
        armR.setPower(armPower);

        if (gamepad1.right_bumper)
        {
            encoder(2);
        }

        if (gamepad2.x)
        {
            clawL.setPosition(0.6);
            clawR.setPosition(0.4);
        }
        else if (gamepad2.y)
        {
            clawL.setPosition(0.4);
            clawR.setPosition(0.6);
        }

        if (gamepad2.left_bumper)
        {
            clawArmL.setPosition(0.75);
            clawArmR.setPosition(0.25);
        }
        else if (gamepad2.right_bumper)
        {
            clawArmL.setPosition(0.25);
            clawArmR.setPosition(0.75);
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }

    public void encoder(double turnage)
    {
        newTarget = ticks/turnage;
        armR.setTargetPosition((int)newTarget);
        armL.setTargetPosition((int)newTarget);
        armR.setPower(0.6);
        armL.setPower(0.6);
        armR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
