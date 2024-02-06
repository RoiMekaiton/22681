package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.RunCommand;
import org.firstinspires.ftc.teamcode.commands.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;


@Config
@Autonomous(group = "drive")
public class BackAndForth extends CommandOpMode {

    public static double DISTANCE = 50;

    private MecanumDriveSubsystem drive;
    private TrajectoryFollowerCommand forwardFollower, backwardFollower;

    @Override
    public void initialize() {
        drive = new MecanumDriveSubsystem(new SampleMecanumDrive(hardwareMap), false);
        Trajectory forwardTrajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(DISTANCE)
                .build();
        forwardFollower = new TrajectoryFollowerCommand(drive, forwardTrajectory);
        backwardFollower = new TrajectoryFollowerCommand(drive,
                drive.trajectoryBuilder(forwardTrajectory.end())
                    .back(DISTANCE)
                    .build()
        );
        SequentialCommandGroup backAndForthCommand = new SequentialCommandGroup(forwardFollower, backwardFollower);
        schedule(new RunCommand(() -> {
            if (backAndForthCommand.isFinished() || !backAndForthCommand.isScheduled()) {
                backAndForthCommand.schedule();
            }
        }));
    }

}