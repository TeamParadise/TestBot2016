package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PositionRobotForShooting extends Command
{
	public double centerFrameX = 232;
	public double centerFrameY = 160;
	public double currentX = 0;
	public double currentY = 0;
	public double offset = 10;
	private boolean alignX = false;
	private boolean alignY = false;
	private double currentActuatorPos;
	private double actuatorPosIncrement = 0.5;
	NetworkTable table;
	double values[] = new double[0];
	double x[];
	double y[];

	public PositionRobotForShooting()
	{
		requires(Robot.robotDrive);
		// requires(Robot.linearActuator);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		alignX = false;
		alignY = false;
		try
		{
			table = NetworkTable.getTable("GRIP/myContoursReport");
			x = table.getNumberArray("centerX");
			y = table.getNumberArray("centerY");
			if (x.length == 1 && y.length == 1)
			{
				currentX = x[0];
				currentY = y[0];
				// Obtain X and Y values from GRIP/myContoursReport
			}
			else
			{
				currentX = -1;
				currentY = -1;
			}
		}
		catch (Exception e)
		{
			currentX = -1;
			currentY = -1;
		}
		//Robot.linearActuator.setSetpoint(1.115);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		try
		{
			table = NetworkTable.getTable("GRIP/myContoursReport");
			x = table.getNumberArray("centerX");
			y = table.getNumberArray("centerY");
			currentX = x[0];
			currentY = y[0];
		}
		catch (Exception e)
		{
			currentX = -1;
			currentY = -1;
		}
		if (!alignX)
		{
			if (Math.abs(centerFrameX - currentX) >= offset)
			{
				alignX = false;
				if (centerFrameX - currentX > 0)
				{
					// Direction might need to be configured based on actual
					// robot
					SmartDashboard.putNumber("Drive Direction", -0.5);
					Robot.robotDrive.arcadeDrive(0, -0.55);
				}
				else if (centerFrameX - currentX < 0)
				{
					// Direction might need to be configured on actual Robot
					SmartDashboard.putNumber("Drive Direction", 0.5);
					Robot.robotDrive.arcadeDrive(0, 0.55);
				}
			}
			else
			{
				SmartDashboard.putNumber("Drive Direction", 0);
				Robot.robotDrive.arcadeDrive(0, 0);
				alignX = true;
			}
		}
		if (!alignY)
		{
			if (Math.abs(centerFrameY - currentY) >= offset && Robot.linearActuator.onTarget())
			{
				currentActuatorPos = Robot.linearActuator.getPosition();
				if (centerFrameY - currentY > 0)
				{
					Robot.linearActuator.setSetpoint(currentActuatorPos - actuatorPosIncrement);
				}
				else if (centerFrameY - currentY < 0)
				{
					Robot.linearActuator.setSetpoint(currentActuatorPos + actuatorPosIncrement);
				}
			}
			else
			{
				alignY = true;
			}
		}
		SmartDashboard.putNumber("CenterX", centerFrameX);
		SmartDashboard.putNumber("Current X", currentX);
		SmartDashboard.putNumber("Center Y", centerFrameY);
		SmartDashboard.putNumber("Current Y", currentY);
		SmartDashboard.putBoolean("Is Aligned X", alignX);
		SmartDashboard.putBoolean("Is Aligned Y", alignY);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return alignX && alignY;
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