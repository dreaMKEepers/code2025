package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServoImpl;


@TeleOp

public class ViperArm extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        // DcMotor viperarmExtentions = hardwareMap.dcMotor.get("viperslideExtend");
        CRServo wristServo = hardwareMap.get(CRServo.class, "wristServo");
        CRServo grabServo = hardwareMap.crservo.get("grabServo");
        
        // viperarmExtentions.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        while (opModeIsActive()) {
            // viperslides
            // if (gamepad1.left_bumper) {
            //     viperarmExtentions.setPower(0.60);
            // } else if (gamepad1.right_bumper) {
            //     viperarmExtentions.setPower(-0.60);
            // } else{
            //     viperarmExtentions.setPower(0);
            // }
            // wrist Servo
            if (gamepad1.x){
                wristServo.setPower(0.5);
                telemetry.addData("Wrist:", "Set to 1");
            } else if (gamepad1.b) {
                wristServo.setPower(-0.5);
                telemetry.addData("Wrist:", "Set to -1");
            } else {
                wristServo.setPower(0);
                telemetry.addData("Wrist:", "Set to 0");
            }
            
            //grab Servo
            if (gamepad1.y) {
                grabServo.setPower(1);
                telemetry.addData("Grab:", "Set to 1");
            } else if (gamepad1.a) {
                grabServo.setPower(-1);
                telemetry.addData("Grab", "Set to -1");
            } else {
                grabServo.setPower(0);
            }
            telemetry.update();
    }
}
}