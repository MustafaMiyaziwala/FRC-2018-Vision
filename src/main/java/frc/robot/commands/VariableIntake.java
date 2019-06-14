/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;

public class VariableIntake extends Command {
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	double powerIn = 0;
	double powerOut = 0;

	public VariableIntake() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time

	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		leftSpeed = Robot.oi.secondStick.getRawAxis(OI.Axis.LX.getAxisNumber());
		rightSpeed = Robot.oi.secondStick.getRawAxis(OI.Axis.RX.getAxisNumber());
		
		powerIn = Robot.oi.secondStick.getRawAxis(OI.Axis.RTrigger.getAxisNumber());
		powerOut = Robot.oi.secondStick.getRawAxis(OI.Axis.LTrigger.getAxisNumber())*0.75;
		
		if(Math.abs(leftSpeed) > 0.1) {
			Robot.intake.setPowerIndv(-leftSpeed, true);
		} 
		
		if (Math.abs(rightSpeed) > 0.1) {
			Robot.intake.setPowerIndv(-rightSpeed, false);
		}
		
		if (Math.abs(powerIn) > 0.05) {
			Robot.intake.setPower(powerIn, true, false);
		}  
		
		if (Math.abs(powerOut) > 0.05) {
			Robot.intake.setPower(powerOut,true, true);
		}
		
		if (Math.abs(leftSpeed)<0.1 && Math.abs(rightSpeed)<0.1 && Math.abs(powerIn) < 0.05 && Math.abs(powerOut) < 0.05) {
			Robot.intake.setPower(0,true, true);
		}
		
		SmartDashboard.putNumber("LeftSpeed", leftSpeed);
		SmartDashboard.putNumber("RightSpeed", rightSpeed);
		SmartDashboard.putNumber("PowerIn", powerIn);
		SmartDashboard.putNumber("PowerOut", powerOut);
	
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.intake.setPower(0,true, true);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}