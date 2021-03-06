/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import frc.robot.Constants;


public class sPanel extends SubsystemBase {

  // Creating the colors to be matched.
  private final Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color greenTarget = ColorMatch.makeColor(0.197, 0.361, 0.240);
  private final Color redTarget = ColorMatch.makeColor(0.321, 0.333, 0.154);
  private final Color yellowTarget = ColorMatch.makeColor(0.325, 0.594, 0.113);

  public static I2C.Port i2cPort;
  public static ColorSensorV3 colorSensor;
  public static ColorMatch colorMatcher;
  public static WPI_VictorSPX panelMotor;
  public static DoubleSolenoid panelPiston;

  public static Color detectedColor;
  public static ColorMatchResult match;

  public sPanel() {
    // Setting up the color sensor.
    i2cPort = I2C.Port.kOnboard;
    colorSensor = new ColorSensorV3(i2cPort);
    colorMatcher = new ColorMatch();

    // Setting up the motor.
    panelMotor = new WPI_VictorSPX(Constants.panelMotorPort);
    panelPiston = new DoubleSolenoid(0, Constants.panelForwardChannel, Constants.panelReverseChannel);

    // Adding the colors to be matched to the colorMatcher object.
    colorMatcher.addColorMatch(blueTarget);
    colorMatcher.addColorMatch(greenTarget);
    colorMatcher.addColorMatch(redTarget);
    colorMatcher.addColorMatch(yellowTarget);
  }

  // Defining a few methods that control the motor speed
  public void panelNormalSpeed() {
    panelMotor.set(Constants.normalPanelSpeed);
  }

  public void panelSlowSpeed() {
    panelMotor.set(Constants.slowPanelSpeed);
  }

  public void panelHatchUp() {
    panelPiston.set(Value.kForward);
  }

  public void panelHatchDown() {
    panelPiston.set(Value.kReverse);
  }

  String colorString;
  // Detects current color and compares it to the color required.
  public Boolean detectColor(String targetColor) {
    detectedColor = colorSensor.getColor();
    match = colorMatcher.matchClosestColor(detectedColor);

    if (match.color == blueTarget) {
      colorString = "B";
      return targetColor == colorString;
    } 
    else if (match.color == greenTarget) {
      colorString = "G";
      return targetColor == colorString;
    } 
    else if (match.color == redTarget) {
      colorString = "R";
      return targetColor == colorString;
    } 
    else if (match.color == yellowTarget) {
      colorString = "Y";
      return targetColor == colorString;
    } 
    else {
      return false;
    }
  }

  public void stop() {
    panelMotor.set(0);
    panelPiston.set(Value.kOff);
  }


  @Override
  public void periodic() {
   
    //SmartDashboard.putNumber("Red", detectedColor.red);
    //SmartDashboard.putNumber("Green", detectedColor.green);
    //SmartDashboard.putNumber("Blue", detectedColor.blue);
    //SmartDashboard.putNumber("Confidence", match.confidence);
    //SmartDashboard.putString("Detected color", colorString);
  
  }
    // This method will be called once per scheduler run
}
