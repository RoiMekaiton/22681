package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="killallniggas", group="Iterative OpMode niggas")
// @Disabled
public class killAllNiggs extends OpMode
{
    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        frontRight = hardwareMap.get(DcMotor.class, "FrontR" );
        frontLeft = hardwareMap.get(DcMotor.class, "FrontL" );
        backRight = hardwareMap.get(DcMotor.class, "BackR" );
        backLeft = hardwareMap.get(DcMotor.class, "BackL" );

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        runtime.reset();
    }
    @Override
    public void loop() {

        double y = -gamepad1.left_stick_y;
        double rot = gamepad1.right_stick_x;

        double frontRpower = y - rot;
        double frontLpower = y + rot;
        double backRpower = y - rot;
        double backLpower = y + rot;

        double norm = Math.max(Math.max(frontLpower, frontRpower), Math.max(backLpower, backRpower));

        if (norm > 1)
        {
            frontRpower /= norm;
            frontLpower /= norm;
            backRpower /= norm;
            backLpower /= norm;
        }

        frontRight.setPower(frontRpower);
        frontLeft.setPower(frontLpower);
        backRight.setPower(backRpower);
        backLeft.setPower(backLpower);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}
