package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Scrimmage extends LinearOpMode {
    
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        /* 
        Reverse the right side motors. This may be wrong for your setup.
        If your robot moves backwards when commanded to go forwards,
        reverse the left side instead.
        See the note about this earlier on this page.
        */
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        DcMotor actuator = hardwareMap.get(DcMotor.class, "liftDCMotor"); // change to liftDCMotor
        CRServo grabServoMotor = hardwareMap.crservo.get("grabServoMotor");
        //TODO: add wrist CRServo motor 
        
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            
            /* Drive Code */
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            /* 
            Denominator is the largest motor power (absolute value) or 1
            This ensures all the powers maintain the same ratio,
            but only if at least one is out of the range [-1, 1]
            */ 
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 3);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
            
            /* Grab Servo Code */
            if (gamepad1.y) { // push block out 
               grabServoMotor.setPower(1);
            } else if (gamepad1.a) { // grab block 
                grabServoMotor.setPower(-1);
            }
        
        
        /* Lift Motor */
        if (gamepad1.left_bumper){
            actuator.setPower(1);
        } else if(gamepad1.right_bumper) {
            actuator.setPower(-1);
        } else {
          actuator.setPower(0);
        }
     
        telemetry.update();
        
        } // end opModeActive loop 
        
    }
}
