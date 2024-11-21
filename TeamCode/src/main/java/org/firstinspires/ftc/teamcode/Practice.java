package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;




@TeleOp
public class Practice extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");


        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        DcMotor liftDcMotor = hardwareMap.get(DcMotor.class, "liftDcMotor");
        CRServo grabServoMotor = hardwareMap.crservo.get("grabServoMotor");
        CRServo wristServoMotor = hardwareMap.crservo.get("wristServoMotor");


        waitForStart();


        telemetry.addData("start ", "");
        if (isStopRequested()) return;

        while (opModeIsActive()) {

            telemetry.addData("Curr Position: ", liftDcMotor.getCurrentPosition());

            /* Drive Code */


            while (gamepad1.dpad_up){
                frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                frontRightMotor.setPower(1);


                frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                frontLeftMotor.setPower(1);
            }

            while (gamepad1.dpad_down) {
                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                frontRightMotor.setPower(1);


                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                frontLeftMotor.setPower(1);
            }


            /* Grab Motor*/
            while(gamepad2.y) {
                grabServoMotor.setPower(1);
                telemetry.addData("gamepad2.y is pressed", gamepad2.y);
            }


            while(gamepad2.a) {
                grabServoMotor.setPower(-1);
                telemetry.addData("gamepad2.a is pressed", gamepad2.a);
            }


            /* Lift Motor */
            while (gamepad2.left_bumper){
                liftDcMotor.setPower(1);
                telemetry.addData("Current Position left", liftDcMotor.getCurrentPosition());
            }
            while (gamepad2.right_bumper) {
                liftDcMotor.setPower(-1);
                telemetry.addData("Current Position right", liftDcMotor.getCurrentPosition());
            }




            /* Wrist Motor */
            // while (gamepad2.left_trigger){
            //     wristServoMotor.setDirection(0);
            //     telemetry.addData("wrist move left", liftDcMotor.getCurrentPosition());
            // }
            // while (gamepad2.right_trigger) {
            //     wristServoMotor.setDirection(1);
            //   telemetry.addData("wrist move right", actuator.getCurrentPosition());
            // }
            liftDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            int currentPosition = liftDcMotor.getCurrentPosition();
            liftDcMotor.setPower(0);
            // actuator.setTargetPosition();
            // actuator.setPosition(0.5);
            // public static final DcMotor.ZeroPowerBehavior BRAKE
            // look into while loops
            telemetry.addData("Current Position ", liftDcMotor.getCurrentPosition());
        }

        telemetry.update();

    } // end opModeActive loop

}