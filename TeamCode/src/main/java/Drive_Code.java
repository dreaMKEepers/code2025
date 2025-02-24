package org.firstinspires.ftc.teamcode.FinalQualifierFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServoImpl;


@TeleOp
public class Drive_Code extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare motors and servos
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        // Set motor directions
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            // Mecanum drive logic
            double y = -gamepad1.left_stick_y; // Inverted Y-axis
            double x = gamepad1.left_stick_x * 1.1; // Adjust for strafing
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 2); // Adjust speed, LOWING NUMBER = INCRASED SPEED 1 FASTER, 3 SLOWER
            //dash code
            if (gamepad1.a) {
                denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1); // Adjust speed, LOWING NUMBER = INCRASED SPEED 1 FASTER, 3 SLOWER
                telemetry.addData("Dash", "ON");
            }
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
            
            telemetry.update();
        }
    }
}
