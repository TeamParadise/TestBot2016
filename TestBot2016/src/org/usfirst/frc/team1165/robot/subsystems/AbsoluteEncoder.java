package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.DisplayEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AbsoluteEncoder extends Subsystem
{
	private AnalogInput analogInput = new AnalogInput(2);
	private AnalogPotentiometer encoder = new AnalogPotentiometer(analogInput, 360.0, 0);
	public double initialValue;
    public double upperLimit;
    public double lowerLimit;
    public double terrainValue;
    public double initialLowerLimit = 97; //150;
    public double upperLimitDiff = 130; //distance from lowerLimit
    public double initalValueDiff = 48; //distance from LowerLimit
    public double terrainValueDiff = 30;
    public AbsoluteEncoder()
    {
    	initializeEncoder();
    }
    public void initDefaultCommand() 
    {
        setDefaultCommand(new DisplayEncoder());
    }
    public void report()
    {
    	SmartDashboard.putNumber("Encoder Value", encoder.get());
    	SmartDashboard.putNumber("Encoder Initial Value", initialValue);
    	SmartDashboard.putNumber("Encoder Lower Limit",lowerLimit);
    	SmartDashboard.putNumber("Encoder UpperLimit", upperLimit);
    	SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
    	SmartDashboard.putNumber("Terrain Value ", terrainValue);
    }
    public double getCurrentValue()
    {
    	return encoder.get();
    }
    public boolean atUpperLimit()
    {
    	if(SmartDashboard.getBoolean("Enable Absolute Upper Limit") || (DriverStation.getInstance().getMatchTime()<20 && DriverStation.getInstance().isOperatorControl()))
    	return encoder.get()>=upperLimit;
    	else
    		return encoder.get()>=initialValue;
    }
    public boolean atTerrainValue()
    {
    	return encoder.get() >= terrainValue -5;
    }
    public boolean atLowerLimit()
    {
    	return encoder.get()<=lowerLimit+5; //drive the motor a little far due to slop in the gearbox
    }
    public boolean atInitialValue()
    {
    	return encoder.get()<=initialValue + 10 &&encoder.get() >= initialValue-10;
    }
    public void resetEncoderArm()
    {
    	lowerLimit = encoder.get();
    	initialValue = lowerLimit + initalValueDiff;
    	upperLimit = lowerLimit + upperLimitDiff;
    	terrainValue = lowerLimit + terrainValueDiff;
    }
    public double getCorrectedValue()
    {
    	return encoder.get()-initialValue;
    }
    public void initializeEncoder()
    {
		lowerLimit = initialLowerLimit;
		initialValue = lowerLimit+initalValueDiff;
    	upperLimit = lowerLimit+upperLimitDiff;
    	terrainValue = lowerLimit + terrainValueDiff;
    }
}