/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import frc.robot.Constants;


public class sPanel extends SubsystemBase {

  //Creating the colours to be matched.
  private final Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color greenTarget = ColorMatch.makeColor(0.197, 0.361, 0.240);
  private final Color redTarget = ColorMatch.makeColor(0.321, 0.333, 0.154);
  private final Color yellowTarget = ColorMatch.makeColor(0.325, 0.594, 0.113);

  public static ColorSensorV3 colorSensor;
  public static ColorMatch colorMatcher;
  public static WPI_VictorSPX panelMotor; 

  public sPanel() {
    //Setting up the colour sensor.
    I2C.Port i2cPort = I2C.Port.kOnboard;
    ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    ColorMatch colorMatcher = new ColorMatch();

    //Setting up the motor.
    panelMotor = new WPI_VictorSPX(Constants.controlPanel);

    //Adding the colors to be matched to the colorMatcher object.
    colorMatcher.addColorMatch(blueTarget);
    colorMatcher.addColorMatch(greenTarget);
    colorMatcher.addColorMatch(redTarget);
    colorMatcher.addColorMatch(yellowTarget);
  }

  public void setSpeed(double speedLevel){
    panelMotor.set(ControlMode.PercentOutput, speedLevel);
  }

  public String checkColor(){
    Color detectedColor = colorSensor.getColor();
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    if (match.color == blueTarget){
      return "B";
    } else if (match.color == greenTarget){
      return "G";
    }else if (match.color == redTarget){
      return "R";
    }else if (match.color == yellowTarget){
      return "Y";
    }
    return null;
  }

  public Boolean detectColor(String c){
    Color detectedColor = colorSensor.getColor();
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

    if (match.color == blueTarget){
      return c.equals("B");
    } else if (match.color == greenTarget){
      return c.equals("G");
    }else if (match.color == redTarget){
      return c.equals("R");
    }else if (match.color == yellowTarget){
      return c.equals("Y");
    }
    return null;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}