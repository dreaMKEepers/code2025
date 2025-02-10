package org.firstinspires.ftc.teamcode.FinalQualifierFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServoImpl;

@TeleOp
public class Qualifier_Drive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare motors and servos
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        DcMotor viperSlideMotor = hardwareMap.dcMotor.get("viperSlideMotor");
        CRServo leftElbowServo = hardwareMap.get(CRServo.class, "leftElbowServo");
        CRServo rightElbowServo = hardwareMap.get(CRServo.class, "rightElbowServo");
        CRServo clawServo = hardwareMap.crservo.get("clawServo");
        CRServo backupClawServo = hardwareMap.crservo.get("backupClawServo");
        viperSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 2); //default speed 
            
            // dash code
            if (gamepad1.a) {
                denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1); // speed 1 FASTER, 3 SLOWER
            telemetry.addData("Dash on", "");
                                                                                     
            }
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            // viperSlideMotor
            if (gamepad1.left_bumper) {
                viperSlideMotor.setPower(0.60);
                // telemetry.addData("viper slide up", "");
            } else if (gamepad1.right_bumper) {
                viperSlideMotor.setPower(-0.60);
                // telemetry.addData("viper slide down", "");
            } else {
                viperSlideMotor.setPower(0);
            }
            
            // elbowServos
            if (gamepad2.left_bumper) {
                leftElbowServo.setPower(0.5);
                rightElbowServo.setPower(-0.5);
                // telemetry.addData("Elbow up", "");
            } else if (gamepad2.right_bumper) {
                leftElbowServo.setPower(-0.5);
                rightElbowServo.setPower(0.5);
                // telemetry.addData("Elbow down", "");
            } else {
                leftElbowServo.setPower(0);
                rightElbowServo.setPower(0);
            }

            // Claw Servo
            if (gamepad2.y) {
                clawServo.setPower(1);
                backupClawServo.setPower(-1);
                telemetry.addData("Claw engaged", "");
            } else if (gamepad2.a) {
                clawServo.setPower(-1);
                backupClawServo.setPower(1);
                telemetry.addData("Claw disengaged", "");
            } else {
                clawServo.setPower(0);
                backupClawServo.setPower(0);
            }
            telemetry.update();
        }
    }
}
