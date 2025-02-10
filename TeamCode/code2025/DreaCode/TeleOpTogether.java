package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

    public class TeleOpTogether extends LinearOpMode {
            DcMotor actuator=hardwareMap.get(DcMotor.class, "actuator");

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        actuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     

        waitForStart();
        
        //arm controls
        
        
        
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                telemetry.addData("Motor Position", actuator.getCurrentPosition());
                telemetry.addData("Motor Power", actuator.getPower());
                double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
                double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
                double rx = gamepad1.right_stick_x;
                telemetry.addData("x: ", x);
                telemetry.addData("y: ", y);
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
            
                //if (gamepad1.y) {
                //    actuator.setPower(50);
                //
                //} else {
                //    actuator.setPower(0);
                //}
                
                //loop 
                
                telemetry.update();
                if (gamepad1.y) {
                    actuator.setTargetPosition(13300);
                    actuator.setPower(1);
        actuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    
                     //       for (int count = 0; count < 7000; count++) {
                     //   actuator.setPower(1);
                     //   }
                     //   if (count>=7000) {
                     //       actuator.setPower(0);
                     //   }
                    }
                    
                if (gamepad1.a ){
                    actuator.setTargetPosition(0);
                    actuator.setPower(1);
        actuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
                 
                
            }
        }
                if(actuator.isBusy() == false){
                    actuator.setPower(0);
                }
       
        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double speed = 1000; // Speed changes the power of the motors
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 3) / speed;
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
            if (isStopRequested()) {

            }
    }    //adding arm

    }
        
}
