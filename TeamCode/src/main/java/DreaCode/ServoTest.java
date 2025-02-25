package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "ServoTest")
public class ServoTest extends LinearOpMode {
  

  private CRServo grabServoMotor;

  /**
    * This function is executed when this OpMode is selected from the Driver Station.
    */
  @Override
  public void runOpMode() {
    grabServoMotor = hardwareMap.crservo.get("grabServoMotor");
    // grabServoMotor = hardwareMap.get(CRServo.class, "grabServoMotor"); // this works 
 

    // Put initialization blocks here.
    waitForStart();
    telemetry.addData("here", opModeIsActive());


    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
                            telemetry.addData("We are active", "");
// grabServoMotor.startMotor(); 
        if (gamepad1.y) {
              telemetry.addData("y pressd start","");
            
            telemetry.addData("power before ", grabServoMotor.getPower());
          
          grabServoMotor.setPower(1);
                      telemetry.addData("power after ", grabServoMotor.getPower());

                    // grabServoMotor.setPosition(10); 

          // telemetry.addData("now y pressed", grabServoMotor.getPosition());
                  telemetry.addData("port  ", grabServoMotor.getPortNumber());
    
          
        } else if (gamepad1.a) { 
            grabServoMotor.setPower(-1);

        }
        
        if(gamepad1.b ) { 
          telemetry.addData("b is pressed before ", grabServoMotor.getPower());
          grabServoMotor.setPower(0);
          telemetry.addData("b is pressed after ", grabServoMotor.getPower());

        }
                    

                    telemetry.addData("exit", grabServoMotor.getPower());
                  // telemetry.addData("exit what", grabServoMotor);

                    
        telemetry.addData("default port  ", grabServoMotor.getPortNumber());

        // Put loop blocks here.
        telemetry.update();
        
        if(gamepad1.a) {
          // grabServoMotor.setPosition(0);
          // telemetry.addData("a pressed", grabServoMotor.getPosition());

        }
      }
    }
  }
}