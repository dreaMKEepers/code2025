// package org.firstinspires.ftc.teamcode;
// 
// import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import org.firstinspires.ftc.robotcore.external.Telemetry;
// 
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.hardware.DcMotorSimple;
// import com.qualcomm.hardware.bosch.BNO055IMU;
// import org.firstinspires.ftc.teamcode.CameraVisionAuto;
// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
// import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
// import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
// import org.firstinspires.ftc.vision.VisionPortal;
// import org.firstinspires.ftc.vision.tfod.TfodProcessor;
// 
// import java.util.List;
// @Autonomous(name = "AutonomousBlue")
// public class AutonomousBlue extends LinearOpMode{
//     public static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
//     double[] cameraValues = {0,0,0};
// 
//     // TFOD_MODEL_ASSET points to a model file stored in the project Asset location,
//     // this is only used for Android Studio when using models in Assets.
//     //private static final String TFOD_MODEL_ASSET = "train";
//     // TFOD_MODEL_FILE points to a model file stored onboard the Robot Controller's storage,
//     // this is used when uploading models directly to the RC using the model upload interface.
//     public static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/train2";
//     // Define the labels recognized in the model for TFOD (must be in training order!)
//     public static final String[] LABELS = {
//        "beacon",
//     };
// 
//     /**
//      * The variable to store our instance of the TensorFlow Object Detection processor.
//      */
//     public TfodProcessor tfod;
// 
//     /**
//      * The variable to store our instance of the vision portal.
//      */
//     
//     public VisionPortal visionPortal;
// 
//     public DcMotor frontLeftMotor;
//     public DcMotor backLeftMotor;
//     public DcMotor frontRightMotor;
//     public DcMotor backRightMotor;
//     public DcMotor odometryPodY;
//     
//     double encoderResolution = 2000; // 2000 pulses per one revolution
//     double mmToInches = 25.4;
//     double podDiameter = 48 / mmToInches; // diameter in inches
//     double podCircumference = Math.PI*podDiameter;
//     double pulsesToInches = 1/podCircumference*encoderResolution; // Muitply this to inches to get pulses
//     
//     // GYRO ROTATION
//     
//     BNO055IMU imu;
//     
//     @Override
//     public void runOpMode() throws InterruptedException {
//         
//         // Declare our motors
//         // Make sure your ID's match your configuration
//         // Reverse the right side motors. This may be wrong for your setup.
//         // If your robot moves backwards when commanded to go forwards,
//         // reverse the left side instead.
//         // See the note about this earlier on this page.
//         frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
//         backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
//         frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
//         backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
//         odometryPodY = hardwareMap.dcMotor.get("odometryPodY");
//         frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//         backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//         odometryPodY.setDirection(DcMotorSimple.Direction.REVERSE);
//         DcMotor actuator=hardwareMap.get(DcMotor.class, "actuator");
//         actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
// 
//         actuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//         
//         initTfod();
//         waitForStart();
//         telemetry.addData("Arm pos", actuator.getCurrentPosition());
//         actuator.setTargetPosition(13300);
//         actuator.setPower(1);
//         actuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//         while (actuator.isBusy()) {}
//         while (cameraValues[0] == 0) {
//             
//             telemetryTfod();
//                 sleep(20);
//            
//             if (cameraValues[0] != 0) {
//                 break;
//             }
//         }
//         double power = 0.275;
//         if (cameraValues[0]< 250){ //left
//             moveY(25,power);
//             rotateLeft(power);
//             moveY(5, power);
//         }
//         else if (cameraValues[0] > 500) { //right
//             moveY(27,power);
//             rotateLeft(-power);
//             moveY(7.5, power);
//         }
//         else {
//             moveY(31.5, power);
//         }
//         sleep(300);
//         moveY(-1.5, -power);
//         actuator.setTargetPosition(0);
//         
//         while(opModeIsActive()) {
// 
//             telemetry.addData("X ", cameraValues[0]);
//             telemetry.addData("Encoder pulses: ", odometryPodY.getCurrentPosition());
//             telemetry.update();
//         }
//     }
//     void moveY(double distance, double power){
//             int pulse = (int)(distance * pulsesToInches);
//             odometryPodY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//             
//             while (Math.abs(odometryPodY.getCurrentPosition())<Math.abs(pulse)){
//                 telemetry.addData("Position: ", odometryPodY.getCurrentPosition());
//                 telemetry.addData("FinalPosition: ", pulse);
//                 telemetry.update();
//                 frontLeftMotor.setPower(power);
//                 frontRightMotor.setPower(power);
//                 backLeftMotor.setPower(power);
//                 backRightMotor.setPower(power);
//             }
//                 frontLeftMotor.setPower(0);
//                 frontRightMotor.setPower(0);
//                 backLeftMotor.setPower(0);
//                 backRightMotor.setPower(0);
//     }
//     void rotateLeft(double power){
//         int pulse = 1750;
//         odometryPodY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//         
//         while (Math.abs(odometryPodY.getCurrentPosition())<Math.abs(pulse)){
//             telemetry.addData("Position: ", odometryPodY.getCurrentPosition());
//             telemetry.addData("FinalPosition: ", pulse);
//             telemetry.update();
//             frontLeftMotor.setPower(-power);
//             frontRightMotor.setPower(power);
//             backLeftMotor.setPower(-power);
//             backRightMotor.setPower(power);
//         }
//             frontLeftMotor.setPower(0);
//             frontRightMotor.setPower(0);
//             backLeftMotor.setPower(0);
//             backRightMotor.setPower(0);
//         
//     }
//     public void initTfod() {
// 
//         // Create the TensorFlow processor by using a builder.
//         tfod = new TfodProcessor.Builder()
// 
//             // With the following lines commented out, the default TfodProcessor Builder
//             // will load the default model for the season. To define a custom model to load, 
//             // choose one of the following:
//             //   Use setModelAssetName() if the custom TF Model is built in as an asset (AS only).
//             //   Use setModelFileName() if you have downloaded a custom team model to the Robot Controller.
//             //.setModelAssetName(TFOD_MODEL_ASSET)
//             .setModelFileName(TFOD_MODEL_FILE)
// 
//             // The following default settings are available to un-comment and edit as needed to 
//             // set parameters for custom models.
//             .setModelLabels(LABELS)
//             //.setIsModelTensorFlow2(true)
//             //.setIsModelQuantized(true)
//             //.setModelInputSize(300)
//             //.setModelAspectRatio(16.0 / 9.0)
// 
//             .build();
// 
//         // Create the vision portal by using a builder.
//         VisionPortal.Builder builder = new VisionPortal.Builder();
// 
//         // Set the camera (webcam vs. built-in RC phone camera).
//         if (USE_WEBCAM) {
//             builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
//         } else {
//             builder.setCamera(BuiltinCameraDirection.BACK);
//         }
// 
//         // Choose a camera resolution. Not all cameras support all resolutions.
//         //builder.setCameraResolution(new Size(640, 480));
// 
//         // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
//         //builder.enableLiveView(true);
// 
//         // Set the stream format; MJPEG uses less bandwidth than default YUY2.
//         //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);
// 
//         // Choose whether or not LiveView stops if no processors are enabled.
//         // If set "true", monitor shows solid orange screen if no processors enabled.
//         // If set "false", monitor shows camera view without annotations.
//         //builder.setAutoStopLiveView(false);
// 
//         // Set and enable the processor.
//         builder.addProcessor(tfod);
// 
//         // Build the Vision Portal, using the above settings.
//         visionPortal = builder.build();
// 
//         // Set confidence threshold for TFOD recognitions, at any time.
//         //tfod.setMinResultConfidence(0.75f);
// 
//         // Disable or re-enable the TFOD processor at any time.
//         //visionPortal.setProcessorEnabled(tfod, true);
// 
//     }   // end method initTfod()
// 
//     /**
//      * Add telemetry about TensorFlow Object Detection (TFOD) recognitions.
//      */
//     public void telemetryTfod() {
// 
//         List<Recognition> currentRecognitions = tfod.getRecognitions();
//         telemetry.addData("# Objects Detected", currentRecognitions.size());
//         
//         // Step through the list of recognitions and display info for each one.
//         for (Recognition recognition : currentRecognitions) {
//             double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
//             double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;
// 
//             telemetry.addData(""," ");
//             telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
//             telemetry.addData("- Position", "%.0f / %.0f", x, y);
//             telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
//             cameraValues[0] = x;
//             cameraValues[1] = y;
//             cameraValues[2] = (double) recognition.getConfidence();
//             
//             
// 
//         }   // end for() loop
//     }   // end method telemetryTfod()
// } 
// 