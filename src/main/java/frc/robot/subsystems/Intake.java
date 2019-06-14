/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;
import frc.robot.commands.VariableIntake;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	WPI_TalonSRX rightIntakeMotor, leftIntakeMotor;
	
	public Intake() {
		rightIntakeMotor = new WPI_TalonSRX(RobotMap.rightIntakeMotor);
		leftIntakeMotor = new WPI_TalonSRX(RobotMap.leftIntakeMotor);
		rightIntakeMotor.setNeutralMode(NeutralMode.Brake);
		leftIntakeMotor.setNeutralMode(NeutralMode.Brake);
	}
	
	public double getAvgCurrentDraw()	{
		return 0.5*(rightIntakeMotor.getOutputCurrent() + leftIntakeMotor.getOutputCurrent());
	}
			
	public void setPower(double abspower,boolean direction, boolean rollIn) {
		double power = Math.abs(abspower);
		
		if(rollIn) {
			SmartDashboard.putBoolean("Direction", direction);
			if(direction) {
				rightIntakeMotor.set(ControlMode.PercentOutput,power*1.65);
				leftIntakeMotor.set(ControlMode.PercentOutput,-power);
			}	else {
				rightIntakeMotor.set(ControlMode.PercentOutput,power);
				leftIntakeMotor.set(ControlMode.PercentOutput,-power*1.65);
			}
		} else {
			rightIntakeMotor.set(ControlMode.PercentOutput,-power);
			leftIntakeMotor.set(ControlMode.PercentOutput,power);
		}		
	}
	
	public void setPowerIndv(double power, boolean left) {
		if(left) {
			leftIntakeMotor.set(power);
		} else {
			rightIntakeMotor.set(power);
		}
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new VariableIntake());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
