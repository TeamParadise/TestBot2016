package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class InvertDriveForward extends Command
{

	public InvertDriveForward()
	{
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		Robot.oi.driveForward = !Robot.oi.driveForward;
	/*	if (Robot.oi.driveForward)
			Robot.camera.setCamera(RobotMap.tertiaryCameraName);
		else
			Robot.camera.setCamera(RobotMap.primaryCameraName);
	*/}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return true;
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
