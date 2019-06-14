package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchRetract extends Command {

	public SwitchRetract() {
		requires(Robot.switchArm);
	}

	public void initialize() {
		Robot.switchArm.retract();
	}

	@Override
	protected boolean isFinished() {
		return !Robot.switchArm.getRampSolenoid().get();
	}
}
