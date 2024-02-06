package main.java.org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.commands.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class RoadRunnerTrejC {
    SampleMecanumDrive driveTrain;

    public RoadRunnerTrejC (SampleMecanumDrive dT) {driveTrain = dT; }

    public Trajectory forwardsByInches (double x, double y, double heading, double inches) {
        Trajectory forwards = driveTrain.trajectoryBuilder(new Pose2d (x, y, heading))
            .forward(inches)
            .bulid();
        return forwards;
    }

    public Trajectory backwardsByInches (double x, double y, double heading, double inches) {
        Trajectory backwards = driveTrain.trajectoryBuilder(new Pose2d (x, y, heading))
            .back(inches)
            .bulid();
        return backwards;
    }

    public Trajectory strafeLeft (double x, double y, double heading, double inches) {
        Trajectory left = driveTrain.trajectoryBuilder(new Pose2d (x,y, heading))
            .strafeLeft(inches)
            .bulid();
        return left;
    }

    public Trajectory strafeRight (double x, double y, double heading, double inches) {
        Trajectory right = driveTrain.trajectoryBuilder(new Pose2d (x,y, heading))
            .strafeRight(inches)
            .bulid();
        return left;
    }

    public Trajectory lineTo (double currentX, double CurrentY, double CurrentHeading, double goalX, double goalY) {
        Trajectory lineToPostion = driveTrain.trajectoryBuilder(new Pose2d (currentX, currentY, CurrentHeading))
            .lineto(new Vector2d(goalX, goalY))
            .bulid();
        return lineToPostion;
    }

    public Trajectory strafeTo (double currentX, double CurrentY, double CurrentHeading, double goalX, double goalY) {
        Trajectory strafeToPostion = driveTrain.trajectoryBuilder(new Pose2d (currentX, currentY, CurrentHeading))
            .strafeTo(new Vector2d(goalX, goalY))
            .bulid();
        return strafeToPostion;
    }

    public Trajectory splineTo (double currentX, double CurrentY, double CurrentHeading, double goalX, double goalY) {
        Trajectory splineToPostion = driveTrain.trajectoryBuilder(new Pose2d (currentX, currentY, CurrentHeading))
            .splineTo(new Vector2d(goalX, goalY))
            .bulid();
        return splineToPostion;
    }
}
