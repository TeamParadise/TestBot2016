package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PositionRobotForShootingRevised extends Command
{
	public double centerFrameX = 232;
	public double centerFrameY = 160;
	public double currentX = 0;
	public double currentY = 0;
	public double tolerance = 10;
	private boolean xIsAligned = false;
	private boolean yIsAligned = false;
	private double currentActuatorPos;
	private double actuatorPosIncrement = 0.5;
	private NetworkTable table;
	private double x[];
	private double y[];
	private boolean noCanDo;
	private double driveDirection;
	private double actuatorSetpoint;

	public PositionRobotForShootingRevised()
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
		/*
		// If linear actuator is enabled and on target, turn it off:
		if (Robot.linearActuator.getPIDController().isEnabled() && Robot.linearActuator.onTarget())
		{
			Robot.linearActuator.disable();
		}
		
		x = table.getNumberArray("centerX", (double[]) null);
		y = table.getNumberArray("centerY", (double[]) null);
		if (null == x || null == y)
		{
			noCanDo = true;
		}
		else
		{
			currentX = x[0];
			currentY = y[0];
		}
		
		if (!noCanDo && !xIsAligned)
		{
			if (Math.abs(centerFrameX - currentX) >= tolerance)
			{
				// Direction might need to be configured on actual Robot
				driveDirection = (centerFrameX - currentX) > 0
						? -0.55
						: 0.55;
				SmartDashboard.putNumber("Drive Direction", driveDirection);
				Robot.robotDrive.arcadeDrive(0, driveDirection);
			}
			else
			{
				SmartDashboard.putNumber("Drive Direction", 0);
				Robot.robotDrive.arcadeDrive(0, 0);
				xIsAligned = true;
			}
		}
		
		if (!noCanDo && !yIsAligned && !Robot.linearActuator.getPIDController().isEnabled())
		{
			if (Math.abs(centerFrameY - currentY) >= tolerance)
			{
				currentActuatorPos = Robot.linearActuator.getPosition();
				actuatorSetpoint = (centerFrameY - currentY) > 0
						? currentActuatorPos - actuatorPosIncrement
						: currentActuatorPos + actuatorPosIncrement;
				Robot.linearActuator.setSetpoint(actuatorSetpoint);
				Robot.linearActuator.enable();
			}
			else
			{
				yIsAligned = true;
			}
		}
		
		SmartDashboard.putNumber("CenterX", centerFrameX);
		SmartDashboard.putNumber("Current X", currentX);
		SmartDashboard.putNumber("Center Y", centerFrameY);
		SmartDashboard.putNumber("Current Y", currentY);
		SmartDashboard.putBoolean("Is Aligned X", xIsAligned);
		SmartDashboard.putBoolean("Is Aligned Y", yIsAligned);
		SmartDashboard.putBoolean("No Can Do", noCanDo);*/
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return true;//noCanDo || (xIsAligned && yIsAligned);
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.robotDrive.arcadeDrive(0, 0);
		Robot.linearActuator.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		end();
	}
}