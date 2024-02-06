package main.java.org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.commands.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

@Autonomous(name= "Road Runner Test")
public class RoadRunnerTreClass extends LinearOpMode {

    @Override
    public void runOpMode() {
        SampleTankDrive driveTrain = new SampleTankDrive(hardwareMap);
        RoadRunnerTrejC rrTrajectories = new RoadRunnerTrejC(driveTrain);

        waitForStart();

        Trajectory turn = driveTrain.trajectoryBuilder(new Pose2d (0, 0, 0))
            .splneConstantHeading(new Vector2d (100, 100), Math.toRadians(0))
            .bulid();

        driveTrain.followTrajectory(turn);
        
        driveTrain.followTrajectory(rrTrajectories.splineTo(0, 0, 0, 100, 90));
    }
}
