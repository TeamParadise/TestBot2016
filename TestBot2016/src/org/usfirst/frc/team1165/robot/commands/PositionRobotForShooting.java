package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class PositionRobotForShooting extends Command
{
	private final double centerFrameX = 60;
	private final double centerFrameY = 60;
	private double currentX;
	private double currentY;
	private double offset = 10;
	private boolean alignX = false;
	private boolean alignY = false;
	private double currentActuatorPos;
	private final double actuatorPosIncrement = 0.1;
	NetworkTable table;
	double values[] = new double[0];

	public PositionRobotForShooting()
	{
		requires(Robot.robotDrive);
		//requires(Robot.linearActuator);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		table = NetworkTable.getTable("GRIP/myContoursReport");
		double x[] = table.getNumberArray("centerX");
		double y[] = table.getNumberArray("centerY");
		if (x.length == 1)
		{
			for (double i : x)
			{
				try
				{
					currentX = x[0];
					currentY = y[0];
				}
				catch (Exception e)
				{
					currentX = -1;
					currentY = -1;
				}
			} // Obtain X and Y values from GRIP/myContoursReport
		}
		else
		{
			currentX = -1;
			currentY = -1;
		}
		if (currentX != -1 && currentY != -1)
		{
			if (Math.abs(centerFrameX - currentX) >= offset)
			{
				if (centerFrameX - currentX > 0)
				{
					//Direction might need to be configured based on actual robot
					Robot.robotDrive.arcadeDrive(0, RobotMap.ROTATE_SPEED);
				}
				else if (centerFrameX - currentY < 0)
				{
					//Direction might need to be configured on actual Robot
					Robot.robotDrive.arcadeDrive(0, -RobotMap.ROTATE_SPEED);
				}
			}
			else
			{
				Robot.robotDrive.arcadeDrive(0, 0);
				alignX = true;
			}
			/*if (Math.abs(centerFrameY - currentY) >= offset)
			{
				currentActuatorPos = Robot.linearActuator.getPosition();
				if (centerFrameY - currentY > 0)
				{
					Robot.linearActuator.setSetpoint(currentActuatorPos-actuatorPosIncrement);
				}
				else if(centerFrameY - currentY < 0)
				{
					Robot.linearActuator.setSetpoint(currentActuatorPos+actuatorPosIncrement);}
			}
			else
			{
				alignY = true;
			}*/
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return alignX;// &&alignY;
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.robotDrive.arcadeDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
	}
}