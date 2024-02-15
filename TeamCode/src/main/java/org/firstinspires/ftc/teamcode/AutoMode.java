package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name="Auto: SkyStone Detector", group="Autonomous")
public class AutoMode extends LinearOpMode {
    // Handle hardware stuff...

    int width = 320;
    int height = 240;
    // store as variable here so we can access the location
    SkystoneDetector detector = new SkystoneDetector(width);
    OpenCvCamera webcam1;

    @Override
    public void runOpMode() {
        // robot logic...
        WebcamName webcameName = hardwareMap.get(WebcamName.class, "webcame1");
        // https://github.com/OpenFTC/EasyOpenCV/blob/master/examples/src/main/java/org/openftc/easyopencv/examples/InternalCameraExample.java
        // Initialize the back-facing camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam1 = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        // Connect to the camera
//        webcam1.openCameraDevice();
        webcam1 = OpenCvCameraFactory.getInstance().createWebcam(webcameName, cameraMonitorViewId);

        // Use the SkystoneDetector pipeline
        // processFrame() will be called to process the frame
        webcam1.setPipeline(detector);
        // Remember to change the camera rotation
        webcam1.startStreaming(width, height, OpenCvCameraRotation.SIDEWAYS_LEFT);

        //...

        SkystoneDetector.SkystoneLocation location = detector.getLocation();
        if (location != SkystoneDetector.SkystoneLocation.NONE) {
            // Move to the left / right
            telemetry.addLine("Left/right");
        } else {
            // Grab the skystone
            telemetry.addLine("skystone");
        }

        // more robot logic...
    }

}