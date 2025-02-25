package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@TeleOp
public class Broken extends LinearOpMode {

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


        DcMotor liftDCMotor = hardwareMap.get(DcMotor.class, "liftDCMotor");
        // CRServo grabServoMotor = hardwareMap.crservo.get("grabServoMotor");
        // CRServo wristServoMotor = hardwareMap.crservo.get("wristServoMotor");


        waitForStart();


        telemetry.addData("start ", "");
        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // telemetry.addData("Curr Position: ", liftDcMotor.getCurrentPosition());

            /* Drive Code */
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 3);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);


            // // /* Grab Motor*/
            // while(gamepad2.left_) {
            // grabServoMotor.setPower(1);
            //   telemetry.addData("gamepad2.y is pressed", gamepad2.y);
            // }


            // while(gamepad2.a) {
            // grabServoMotor.setPower(-1);
            //   telemetry.addData("gamepad2.a is pressed", gamepad2.a);
            // }


            //  /* Lift Motor */
            // while (gamepad2.a){
            //       liftDCMotor.setPower(1);
            //         telemetry.addData("Current Position left", liftDCMotor.getCurrentPosition());
            // }
            // while (gamepad2.b) {
            //         liftDCMotor.setPower(-1);
            //   telemetry.addData("Current Position right", liftDCMotor.getCurrentPosition());
            // }
            //  while(gamepad2.atRest()) {
            // liftDCMotor.setPower(0));
            // }




            // /* Wrist Motor */
            // // while (gamepad2.left_trigger){
            // //     wristServoMotor.setDirection(0);
            // //     telemetry.addData("wrist move left", liftDcMotor.getCurrentPosition());
            // // }
            // // while (gamepad2.right_trigger) {
            // //     wristServoMotor.setDirection(1);
            // //   telemetry.addData("wrist move right", actuator.getCurrentPosition());
            // // }
            //     liftDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //     int currentPosition = liftDcMotor.getCurrentPosition();
            //      liftDcMotor.setPower(0);
            //     // actuator.setTargetPosition();
            //     // actuator.setPosition(0.5);
            //     // public static final DcMotor.ZeroPowerBehavior BRAKE
            //     // look into while loops
            telemetry.addData("Current Position ", liftDCMotor.getCurrentPosition());
            //     }
        } // end of
        telemetry.update();

    } // end runOpMode loop

}








