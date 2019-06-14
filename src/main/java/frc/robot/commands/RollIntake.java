/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class RollIntake extends Command {
	private double power = 0;
	private boolean rollIn = true;
	private boolean direction = true;
	
	Timer timer;

	double time = 10000000;
	public RollIntake(double power, boolean rollIn) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.intake);
    	this.power = power;
    	this.rollIn = rollIn;
    	
	}
	public RollIntake(double power, double time, boolean rollIn) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.intake);
		this.time = time;
    	this.power = power;
    	this.rollIn = rollIn;
	}
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		timer = new Timer();
		timer.start();
		Robot.intake.setPower(power,true, rollIn);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//direction = (1 == Math.round(reverseTimer.get()) % 2);
		Robot.intake.setPower(power, direction, rollIn);
		SmartDashboard.putNumber("IntakeCurrent", Robot.intake.getAvgCurrentDraw());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.intake.setPower(0,true, rollIn);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.intake.setPower(0, true,rollIn);
	}
}
