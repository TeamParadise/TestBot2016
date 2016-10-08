package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PositionRobotForShootingRevised2 extends Command

{

	public double centerFrameX = 232;

	public double centerFrameY = 160;
	
	public double targetArea = 10000;

	public double currentX = 0;

	public double currentY = 0;
	
	public double currentArea = 0;

	public double tolerance = 10;
	
	public double areaTolerance = 500;

	private boolean xIsAligned = false;

	private boolean yIsAligned = false;
	
	private boolean areaIsAligned = false;

	private NetworkTable table;

	private double x[];

	private double y[];
	
	private double z[];

	private boolean noCanDo;

	private double driveDirection;
	
	private double areaDriveDirection;

	private double actuatorSpeed;

	public PositionRobotForShootingRevised2()

	{

		requires(Robot.robotDrive);

		requires(Robot.linearActuator);

	}

	// Called just before this Command runs the first time
	@Override

	protected void initialize()

	{

		xIsAligned = false;

		yIsAligned = false;

		noCanDo = false;

		// Note if there is no such table, an empty one will be created:

		table = NetworkTable.getTable("GRIP/myContoursReport");

	}

	// Called repeatedly when this Command is scheduled to run

	@Override

	protected void execute()

	{
		// Uncommented by Kesav 10/08/16
		try
		{
			x = table.getNumberArray("centerX", (double[]) null);

			y = table.getNumberArray("centerY", (double[]) null);

			z = table.getNumberArray("area", (double[]) null);
			
			if (null == x || null == y || null == z || x.length == 0 || y.length == 0 || z.length == 0)
			// suggested fix:
			// if (null == x || 0 == x.length || null == y || 0 == y.length)
			{

				noCanDo = true;

			}

			else

			{
				currentX = x[0];
				currentY = y[0];
				currentArea = z[0];
			}

			if (!noCanDo)
			{
				if (Math.abs(centerFrameX - currentX) >= tolerance)
				{
					// Direction might need to be configured on actual Robot
					driveDirection = (centerFrameX - currentX) > 0 ? -0.45 : +0.45;
					SmartDashboard.putNumber("Drive Direction", driveDirection);
				}
				else
				{
					SmartDashboard.putNumber("Drive Direction", 0);
					driveDirection = 0;
					xIsAligned = true;
				}
			}

			if (!noCanDo)
			{
				if (Math.abs(centerFrameY - currentY) >= tolerance)
				{
					// May need to tweak speeds
					actuatorSpeed = (centerFrameY - currentY) > 0 ? -0.25 : +0.25;
					Robot.linearActuator.setSpeed(actuatorSpeed);
				}
				else
				{
					Robot.linearActuator.setSpeed(0);
					yIsAligned = true;
				}
			}

			if(!noCanDo)
			{
				if(Math.abs(targetArea - currentArea) >= areaTolerance)
				{
					areaDriveDirection = (targetArea - currentArea) > 0 ? -0.525 : +0.525;
					SmartDashboard.putNumber("Drive Forwards", areaDriveDirection);
				}
				else
				{
					Robot.robotDrive.arcadeDrive(0 , 0);
					areaDriveDirection = 0;
					areaIsAligned = true;
				}
			}
			Robot.robotDrive.arcadeDrive(areaDriveDirection, driveDirection);
			
			SmartDashboard.putNumber("CenterX", centerFrameX);
			SmartDashboard.putNumber("Current X", currentX);
			SmartDashboard.putNumber("Center Y", centerFrameY);
			SmartDashboard.putNumber("Current Y", currentY);
			SmartDashboard.putNumber("Target Area", targetArea);
			SmartDashboard.putNumber("Current Area", currentArea);
			SmartDashboard.putBoolean("Is Aligned X", xIsAligned);
			SmartDashboard.putBoolean("Is Aligned Y", yIsAligned);
			SmartDashboard.putBoolean("Is Aligned Z", areaIsAligned);
			SmartDashboard.putBoolean("No Can Do", noCanDo);
			SmartDashboard.putBoolean("GRIP Crashed", false);
		}
		catch (Exception e)
		{
			SmartDashboard.putBoolean("GRIP Crashed", true);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return true;//noCanDo || (xIsAligned && yIsAligned && areaIsAligned);
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.robotDrive.arcadeDrive(0, 0);
		Robot.linearActuator.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		end();
	}
}