package org.firstinspires.ftc.teamcode.AutoFiles;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="qualifier blue park")
public class qualifier_blue_park extends LinearOpMode{
    
    @Override
    public void runOpMode() throws InterruptedException{
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
    frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    waitForStart();
    if(opModeIsActive()) {
    // forward

    }
}
}

