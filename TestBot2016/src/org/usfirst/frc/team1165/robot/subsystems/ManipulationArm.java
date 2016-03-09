package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.commands.DriveArmWithGamepad;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ManipulationArm extends Subsystem
{
	public CANTalon armMotor;

	public ManipulationArm()
	{
		armMotor = new CANTalon(3);
		armMotor.setInverted(true);
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new DriveArmWithGamepad());
	}

	public void moveArm()
	{
		double value = Robot.oi.gamepad.getY(Hand.kLeft) * 0.2568;
		moveArm(value);
	}
	public void moveArm(double speed)
	{
		double value = speed;
		if (/*(Robot.absoluteEncoder.atLowerLimit() && value < 0)
				||*/ (Robot.absoluteEncoder.atUpperLimit() && value > 0))
				//|| (Math.abs(Robot.oi.gamepad.getY(Hand.kLeft)) < 0.1) )
		{
			value = 0;
		}
		armMotor.set(value);
	}
	public void moveArmToPoint(double setpoint)
	{
		SmartDashboard.putNumber(" Current Value -Setpoint",Math.abs(Robot.absoluteEncoder.getCurrentValue() - setpoint) );
		if(Math.abs(Robot.absoluteEncoder.getCurrentValue() - setpoint)  < 15)// && setpoint < Robot.absoluteEncoder.getCurrentValue() + 15)
		{
			SmartDashboard.putNumber("Move Arm Value", 0.0);
			moveArm(0.0);	
		}
		else if(setpoint > Robot.absoluteEncoder.getCurrentValue() && !Robot.absoluteEncoder.atUpperLimit())
		{
			SmartDashboard.putNumber("Move Arm Value", 0.4);
			moveArm(0.4);
		}
		else if(setpoint < Robot.absoluteEncoder.getCurrentValue() && !Robot.absoluteEncoder.atLowerLimit())
		{
			SmartDashboard.putNumber("Move Arm Value", -0.4);
			moveArm(-0.4);
		}
	}
	public boolean onPoint(double setpoint)
	{
		return Math.abs(Robot.absoluteEncoder.getCurrentValue())-setpoint<25;	
	}
}