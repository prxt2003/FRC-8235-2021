/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.sHatch;

public class cHatch extends CommandBase {
  
  private final sHatch hatchSubsystem;
  
  public cHatch() {
    hatchSubsystem = new sHatch();
    addRequirements(hatchSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (RobotContainer.primaryController.getYButtonPressed()) {
      hatchSubsystem.hatchOpen();
    }
    while (RobotContainer.primaryController.getAButtonPressed()) {
      hatchSubsystem.hatchClosed();
    }
    hatchSubsystem.stop();
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      hatchSubsystem.stop();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
