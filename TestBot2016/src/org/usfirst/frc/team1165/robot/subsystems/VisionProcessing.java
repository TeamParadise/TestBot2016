package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.commands.ReportGRIPValues;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class VisionProcessing extends Subsystem 
{
	private final double centerFrameX = 240;
	private final double centerFrameY = 135;
	private double currentX;
	private double currentY;
	private double offset = 10;
	private boolean alignX = false;
	private boolean alignY = false;
	private double currentActuatorPos;
	private final double actuatorPosIncrement = 0.1;
	NetworkTable table;
	double values[] = new double[0];
	double x[] = new double[0];
	public VisionProcessing()
	{
		table = NetworkTable.getTable("GRIP/myContoursReport");
		double x[] = table.getNumberArray("centerX");
		double y[] = table.getNumberArray("centerY");
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

    public void initDefaultCommand()
    {
    	setDefaultCommand(new ReportGRIPValues());
    }
    public double getCurrentX()
    {
    	return currentX;
    }
    public double getCurrentY()
    {
    	return currentY;
    }
    public double getCenterX()
    {
    	return centerFrameX;
    }
    public double getCenterY()
    {
    	return centerFrameY;
    }
    public double getOffset()
    {
    	return offset;
    }
    public double getActuatorPosIncrement()
    {
    	return actuatorPosIncrement;
    }
}