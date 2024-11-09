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

        DcMotor actuator = hardwareMap.get(DcMotor.class, "actuator"); // change to liftDCMotor
        CRServo grabServoMotor = hardwareMap.crservo.get("grabServoMotor");
        //TODO: add wrist CRServo motor 
                

        waitForStart();

        telemetry.addData("start ", "");
        if (isStopRequested()) return;
           


        while (opModeIsActive()) {
            
        
            // telemetry.addData("ZeroPowerBehavior: ", actuator.getZeroPowerBehavior());
        telemetry.addData("Curr Position: ", actuator.getCurrentPosition());

            
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
            // if (gamepad1.y) { // push block out 
            //   grabServoMotor.setPower(1);
            // } else if (gamepad1.a) { // grab block 
            //     grabServoMotor.setPower(-1);
            // }
        
        while(gamepad1.y) { 
        grabServoMotor.setPower(1);
        
           telemetry.addData("gamepad1.y is pressed", gamepad1.y);
        } 
        
        /* Lift Motor */
        if (gamepad1.left_bumper){
              actuator.setPower(1);
             // actuator.setPosition(1); 
             // actuator.setPosition(0); 
           // actuator.setTargetPosition(1);
            telemetry.addData("Current Position left", actuator.getCurrentPosition());
        } else if(gamepad1.right_bumper) {
                actuator.setPower(-1);
             //   actuator.setPosition(0.5); 
        telemetry.addData("Current Position right", actuator.getCurrentPosition());

        } else {
            actuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            int currentPosition = actuator.getCurrentPosition();
             actuator.setPower(0);
            // actuator.setTargetPosition();
            // actuator.setPosition(0.5);
            // public static final DcMotor.ZeroPowerBehavior BRAKE 
            // look into while loops
            telemetry.addData("Current Position ", actuator.getCurrentPosition());
            } 
            
        //while(actuator.getPower() == 0) {
            
            //actuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //telemetry.addData("Brake is on", "");
            
           // setZeroPowerBehavior BRAKE;
         }
        
        
     
        telemetry.update();
        
        } // end opModeActive loop 
        
    }

