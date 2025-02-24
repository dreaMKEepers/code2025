package org.firstinspires.ftc.teamcode.AutoFiles;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="qualifier red score")
public class qualifier_red_score extends LinearOpMode{
    
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
    frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    waitForStart();
    if(opModeIsActive()) {
    // forward
    frontRightMotor.setPower(0.32);
    frontLeftMotor.setPower(0.32);
    backRightMotor.setPower(0.32);
    backLeftMotor.setPower(0.32);
    Thread.sleep(1177);
    viperSlideMotor.setPower(1);
    frontRightMotor.setPower(0);
    frontLeftMotor.setPower(0);
    backRightMotor.setPower(0);
    backLeftMotor.setPower(0);
    Thread.sleep(30000);
    viperSlideMotor.setPower(0);
    }
}
}

