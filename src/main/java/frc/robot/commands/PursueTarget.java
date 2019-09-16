/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class PursueTarget extends Command {
  private final double DESIRED_TARGET_AREA = 5;
  private final double targetAreaProportion = 0.09;

  private final double turnProportion = 0.009;

  public PursueTarget() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.limelight.ledsOn(true);
    Robot.limelight.setVisionMode();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double drivePower = (Robot.limelight.getTargetArea() - DESIRED_TARGET_AREA) * targetAreaProportion;
    double turnPower = Robot.limelight.getHorizontalOffset() * turnProportion;
    Robot.driveTrain.tankDrive(drivePower + turnPower, drivePower - turnPower);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.oi.driveStick.getRawAxis(OI.Axis.LY.getAxisNumber())) > 0.1
      || Math.abs(Robot.oi.driveStick.getRawAxis(OI.Axis.RX.getAxisNumber())) > 0.1;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
