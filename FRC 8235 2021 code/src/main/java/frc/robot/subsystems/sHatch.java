/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.commands.cHatch;
import frc.robot.Constants;


public class sHatch extends SubsystemBase {
  
  public static DoubleSolenoid hatchPiston;
  public static Compressor airCompressor;
  public static boolean enabled;
  public static boolean pressureSwitch;
  public static double current;

  // @Override 
  // public void setDefaultCommand(Command defaultCommand){
  //   super.setDefaultCommand(new cHatch());
  // }
  
  public sHatch() {
    hatchPiston = new DoubleSolenoid(Constants.forwardChannel, Constants.reverseChannel);
    airCompressor = new Compressor(Constants.airCompressorPort);
    airCompressor.start();
  }

  public void hatchOpen() {
    hatchPiston.set(DoubleSolenoid.Value.kForward);
  }

  public void hatchClosed() {
    hatchPiston.set(DoubleSolenoid.Value.kReverse);
  }

  public void stop(){
    hatchPiston.set(DoubleSolenoid.Value.kOff);
  }
  
  public void compressorControl(){
    airCompressor.setClosedLoopControl(true);
    airCompressor.setClosedLoopControl(false);

    enabled = airCompressor.enabled();
    pressureSwitch = airCompressor.getPressureSwitchValue();
    current = airCompressor.getCompressorCurrent();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
