package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.DisplayEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
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
    public double initialLowerLimit = 131;
    public double upperLimitDiff = 53; //distance from initialValue
    public double lowerLimitDiff = 51; //distance from initialValue
    
    private Timer t;
    public AbsoluteEncoder()
    {
    	t = new Timer();
    	t.start();
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
    	
    }
    public double getCurrentValue()
    {
    	return encoder.get();
    }
    public boolean atUpperLimit()
    {
    	boolean armLimit = SmartDashboard.getBoolean(RobotMap.ArmLimitKey);
    	if(t.get()>114||armLimit)
    	return encoder.get()>=upperLimit;
    	else
    		return encoder.get()>=initialValue;
    }
    public boolean atLowerLimit()
    {
    	return encoder.get()<=lowerLimit;
    }
    public boolean atInitialValue()
    {
    	return encoder.get()<=initialValue+10&&encoder.get()>=initialValue-10;
    }
    public void resetEncoderArm()
    {
    	
    	lowerLimit = encoder.get();
    	initialValue = lowerLimit+lowerLimitDiff;
    	upperLimit = initialValue+upperLimitDiff;
    }
    public double getCorrectedValue()
    {
    	return encoder.get()-initialValue;
    }
    public void initializeEncoder()
    {
		lowerLimit = initialLowerLimit;
		initialValue = lowerLimit+lowerLimitDiff;
    	upperLimit = initialValue+upperLimitDiff;
    	
    }
}