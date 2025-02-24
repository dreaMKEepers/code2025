package org.firstinspires.ftc.OtherCodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//This is a basic drive chain code
//Encoder resolution 537.7 at the Output Shaft

@TeleOp(name = "Viper0")
public class Viper0 extends LinearOpMode {
    static final double MOTOR_TICK_COUNT = 537.7;
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        
        DcMotor Viper0 = hardwareMap.dcMotor.get("Viper0");
        Viper0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        waitForStart();
        
        //move motor arm 90 degrees;
        

        
        if (isStopRequested()) return;
        
        while (opModeIsActive()) {
            //Viper0.DcMotor.RunMode STOP_AND_RESET_ENCODER;
        
        
                    /* Lift Motor */
            if (gamepad1.left_bumper) {
                Viper0.setPower(1);
                // actuator.setPosition(1);
                // actuator.setPosition(0);
                // actuator.setTargetPosition(1);
                telemetry.addData("Current Position left", Viper0.getCurrentPosition());
            } else if (gamepad1.right_bumper) {
                Viper0.setPower(-1);
                //   actuator.setPosition(0.5);
                telemetry.addData("Current Position right", Viper0.getCurrentPosition());

            } else {
                Viper0.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                int currentPosition = Viper0.getCurrentPosition();
                Viper0.setPower(0);
                // actuator.setTargetPosition();
                // actuator.setPosition(0.5);
                // public static final DcMotor.ZeroPowerBehavior BRAKE
                // look into while loops
                telemetry.addData("Current Position ", Viper0.getCurrentPosition());
            }
        telemetry.update();
        
            
        }
        
}
}
