package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PositionRobotForShooting extends Command
{
	NetworkTable table;
	double values[] = new double[0];
	public PositionRobotForShooting()
	{
		
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		table = NetworkTable.getTable("GRIP/myContoursReport");
		double x[] = table.getNumberArray("centerX");
		double y[] = table.getNumberArray("centerY");
		for(double i:x)
		{
			SmartDashboard.putNumber("X ", x[(int) i]);
			SmartDashboard.putNumber("Y", y[(int)i]);
		}
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
