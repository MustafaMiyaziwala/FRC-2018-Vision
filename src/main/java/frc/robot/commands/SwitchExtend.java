package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SwitchExtend extends Command {

	public SwitchExtend() {
		requires(Robot.switchArm);
	}

	public void initialize() {
		Robot.switchArm.extend();
	}

	@Override
	protected boolean isFinished() {
		return Robot.switchArm.getRampSolenoid().get();
	}
}
