package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServoImpl;


@TeleOp
public class Practice extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare motors and servos
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        DcMotor liftDCMotor = hardwareMap.get(DcMotor.class, "liftDCMotor");
        CRServo grabServoMotor = hardwareMap.crservo.get("grabServoMotor");
        CRServo wristServoMotor = hardwareMap.crservo.get("wristServoMotor");

        // Set motor directions
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        liftDCMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {
            // Mecanum drive logic
            double y = -gamepad1.left_stick_y; // Inverted Y-axis
            double x = gamepad1.left_stick_x * 1.1; // Adjust for strafing
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 2); // Adjust speed, LOWING NUMBER = INCRASED SPEED 1 FASTER, 3 SLOWER
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            // Grab motor logic
            if (gamepad2.left_bumper) {
                grabServoMotor.setPower(1); // pull blocks in
                telemetry.addData("Grab Servo", "forward");
            } else if (gamepad2.right_bumper) {
                grabServoMotor.setPower(-1); // Move lift down
                telemetry.addData("Grab Motor", "reverse");
            } else {
                grabServoMotor.setPower(0); // Stop the servo motor
                telemetry.addData("Grab Servo", "Stopped");
            }

            // Wrist motor logic
            if (gamepad2.dpad_left) {
                wristServoMotor.setPower(0.5); // Moves wrist left
                telemetry.addData("Wrist Servo", "0.5");
            } else if (gamepad2.dpad_right) {
                wristServoMotor.setPower(-0.5); // Moves wrist right
                telemetry.addData("Wrist Motor", "reverse");
            } else {
                wristServoMotor.setPower(0); // Stops wrist
                telemetry.addData("Wrist Servo", "Stopped");
            }


            // Lift motor example
            if (gamepad2.a) {
                liftDCMotor.setPower(1); // Move lift up
                telemetry.addData("Lift Motor", "Moving Up");
            } else if (gamepad2.b) {
                liftDCMotor.setPower(-1); // Move lift down
                telemetry.addData("Lift Motor", "Moving Down");
            } else {
                liftDCMotor.setPower(0); // Stop the lift motor
                telemetry.addData("Lift Motor", "Stopped");
            }

            telemetry.update();
        }
    }
}