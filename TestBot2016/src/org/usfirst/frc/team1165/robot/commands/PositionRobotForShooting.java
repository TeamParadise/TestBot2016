package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PositionRobotForShooting extends Command
{
	private double centerFrameX = 240;
	private double centerFrameY = 135;
	private double currentX;
	private double currentY;
	private double offset;
	private boolean alignX = false;
	private boolean alignY = false;
	private double currentActuatorPos;
	private double actuatorPosIncrement;
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
		actuatorPosIncrement = Robot.vision.getActuatorPosIncrement();
		offset = Robot.vision.getOffset();
		centerFrameX = Robot.vision.getCenterX();
		centerFrameY = Robot.vision.getCenterY();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{

		currentX = Robot.vision.getCurrentX();
		currentY = Robot.vision.getCurrentY();
		if (Math.abs(centerFrameX - currentX) >= offset)
		{
			alignX = false;
			if (centerFrameX - currentX > 0)
			{		
				//Direction might need to be configured based on actual robot
				 SmartDashboard.putNumber("Drive Direction", RobotMap.ROTATE_SPEED);
				Robot.robotDrive.arcadeDrive(0, -RobotMap.ROTATE_SPEED);
			}
			else if (centerFrameX - currentX < 0)
			{
				//Direction might need to be configured on actual Robot
				 SmartDashboard.putNumber("Drive Direction", -RobotMap.ROTATE_SPEED);
				Robot.robotDrive.arcadeDrive(0, RobotMap.ROTATE_SPEED);
			}
		}
		else
		{
			SmartDashboard.putNumber("Drive Direction", 0);
			Robot.robotDrive.arcadeDrive(0, 0);
			alignX = true;
		}
		if (Math.abs(centerFrameY - currentY) >= offset)
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
		}
		SmartDashboard.putBoolean("Is Aligned X", alignX);
		SmartDashboard.putBoolean("Is Aligned Y", alignY);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return alignX &&alignY;
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