package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team1165.util.Gamepad;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem
{
	public double centerFrameX = 232;
	public double centerFrameY = 160;
	public double currentX = 0;
	public double currentY = 0;
	public double offset = 10;
	private boolean alignX = false;
	private boolean alignY = false;
	private double currentActuatorPos;
	private double actuatorPosIncrement = 0.1;
	NetworkTable table;
	double values[] = new double[0];
	double x[];
	double y[];

	public CANTalon canTalon4;
	public CANTalon canTalon5;
	public CANTalon canTalon6;
	public CANTalon canTalon7;
	RobotDrive robotDrive;
	double twist;

	public DriveTrain()
	{
		canTalon4 = new CANTalon(4);
		canTalon5 = new CANTalon(5);
		canTalon6 = new CANTalon(6);
		canTalon7 = new CANTalon(7);
		robotDrive = new RobotDrive(canTalon6, canTalon7, canTalon4, canTalon5);
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new DriveWithJoystick());
	}

	public void tankDrive()
	{
		robotDrive.tankDrive(Robot.oi.gamepad, Gamepad.Axis.LEFT_Y.getValue(), Robot.oi.gamepad,
				Gamepad.Axis.RIGHT_Y.getValue(), false);
	}

	public void arcadeDrive()
	{
		twist = Robot.oi.leftStick.getTwist() * 0.7524;
		if (Robot.oi.reduceTwistFlag) twist = twist * 0.826;
		// robotDrive.arcadeDrive(Robot.oi.leftStick,Joystick.AxisType.kY.value,Robot.oi.leftStick,Joystick.AxisType.kZ.value,true);

		// Vision Stuff (Experimental)
		try
		{
			robotDrive.arcadeDrive(Robot.oi.leftStick.getY(), twist);
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
		SmartDashboard.putNumber("CenterX", centerFrameX);
		SmartDashboard.putNumber("Current X", currentX);
		SmartDashboard.putNumber("Center Y", centerFrameY);
		SmartDashboard.putNumber("Current Y", currentY);

	}

	public void arcadeDrive(double magY, double twist)
	{
		this.twist = twist;
		// robotDrive.arcadeDrive(Robot.oi.leftStick,Joystick.AxisType.kY.value,Robot.oi.leftStick,Joystick.AxisType.kZ.value,true);
		robotDrive.arcadeDrive(magY, twist);
	}
}