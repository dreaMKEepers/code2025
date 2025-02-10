package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp


public class EmergencyArmMovement extends LinearOpMode {
    
    
    @Override
    public void runOpMode() throws InterruptedException {
        
        DcMotor actuator=hardwareMap.get(DcMotor.class, "actuator");
        waitForStart();
        while (opModeIsActive()){
            while (gamepad1.a){
                actuator.setPower(-1);
            }
            actuator.setPower(0);
            while (gamepad1.y){
                actuator.setPower(1);
            }
            actuator.setPower(0);
        }
    actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    
}