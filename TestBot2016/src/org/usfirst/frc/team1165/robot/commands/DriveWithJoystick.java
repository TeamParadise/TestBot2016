package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoystick extends Command
{

	public DriveWithJoystick()
	{
		requires(Robot.robotDrive);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{

		Robot.robotDrive.canTalon4.setInverted(true);
		Robot.robotDrive.canTalon5.setInverted(true);
		Robot.robotDrive.canTalon6.setInverted(true);
		Robot.robotDrive.canTalon7.setInverted(true);
		Robot.robotDrive.arcadeDrive();
		/*
		 * SmartDashboard.putNumber("CenterX", Robot.vision.getCenterX());
		 * SmartDashboard.putNumber("Current X", Robot.vision.getCurrentX());
		 * SmartDashboard.putNumber("Center Y",Robot.vision.getCenterY());
		 * SmartDashboard.putNumber("Current Y", Robot.vision.getCurrentY());
		 */
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
