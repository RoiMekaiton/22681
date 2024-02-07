package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.onbotjava.handlers.objbuild.WaitForBuild;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.TrajectoryFollowerCommand;

import java.util.Vector;

@TeleOp(name= "Road Runner Test")
public class RoadRunnerTreClass extends LinearOpMode {

    @Override
    public void runOpMode() {
      SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

      Trajectory depositWobbleGoal = drive.trajectoryBuilder(new Pose2d())
              .splineTo(new Vector2d(114, 31), Math.toRadians(45))
              .build();

      Trajectory firstPowerShot = drive.trajectoryBuilder(new Pose2d(114, 31, Math.toRadians(45)))
              .lineToLinearHeading(new Pose2d(60, 1, Math.toRadians(15)))
              .build();

      Trajectory secondPowerShot = drive.trajectoryBuilder(new Pose2d(60, 1), Math.toRadians(15))
              .lineToConstantHeading(new Vector2d(60, -6.5))
              .build();

      Trajectory thirdPowerShot = drive.trajectoryBuilder(new Pose2d(60, -6.5), Math.toRadians(15))
              .lineToConstantHeading(new Vector2d(60, -15))
              .build();

      Trajectory goToWobbleGoal = drive.trajectoryBuilder(new Pose2d(60, -15), Math.toRadians(15))
              .splineToSplineHeading(new Pose2d(20, 20, Math.toRadians(131)), Math.toRadians(-300))
              .build();

      Trajectory alignFor2ndWobbleGoal = drive.trajectoryBuilder(new Pose2d(20, 20), Math.toRadians(131))
              .splineToSplineHeading(new Pose2d(18, 36, Math.toRadians(0)), Math.toRadians(-90))
              .build();

      Trajectory deposit2ndWobbleGoal = drive.trajectoryBuilder(new Pose2d(18, 36), Math.toRadians(-3))
              .lineTo(new Vector2d(116, 36))
              .build();

      Trajectory returnToStartLine = drive.trajectoryBuilder(new Pose2d(116, 36), Math.toRadians(-31))
              .lineTo(new Vector2d(75, 0))
              .build();

      waitForStart();
      if(isStopRequested()) return;

      drive.followTrajectory(depositWobbleGoal);
      drive.followTrajectory(firstPowerShot);
      drive.followTrajectory(secondPowerShot);
      drive.followTrajectory(thirdPowerShot);
      drive.followTrajectory(goToWobbleGoal);
      drive.followTrajectory(alignFor2ndWobbleGoal);
      drive.followTrajectory(deposit2ndWobbleGoal);
      drive.followTrajectory(returnToStartLine);


    }
}
