package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.commands.ServoPosition;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveServo extends Subsystem {
    
	public static Servo servo1;
	public static Servo servo2;
	public static double maxAngle = 70;
	public static double minAngle = 0;
	public static double operateSpeed = -2000;
	public MoveServo()
	{
		servo1 = new Servo(0);
		servo2 = new Servo(3);
	}
    public void initDefaultCommand() 
    {
    	setDefaultCommand(new ServoPosition());
    }
    public boolean protectServo()
    //make sure that if the wheels are going in or we are less than 3500 rpm out
    //that we don't let the servo out so the ball can't break it
    {
    	boolean retract = Robot.shooter.rightWheel.getSpeed() > operateSpeed;
    	if (retract)
    	{
    		servo1.setAngle(maxAngle);
    		servo2.setAngle(minAngle);
    	}
    	return retract;
    }
    public void angle(Joystick stick)
    {
    	double angle=Robot.shooter.rightWheel.getSpeed() <= operateSpeed && Robot.oi.servoButton.get() ? minAngle : maxAngle;
       	servo1.setAngle(angle);
       	angle = Robot.shooter.rightWheel.getSpeed() <= operateSpeed && Robot.oi.servoButton.get() ? maxAngle : minAngle;
       	servo2.setAngle(angle);
    }
    public void push()
    {
    	if (!protectServo()) 
    	{
    		servo1.setAngle(minAngle);
    		servo2.setAngle(maxAngle);
    	}
    }
    public void idle()
    {
    	servo1.setAngle(maxAngle);
    	servo2.setAngle(minAngle);
    }
    public double getAngle()
    {
    	return servo1.getAngle();
    }
    public boolean servoExtended()
    {
    	return servo1.getAngle() == 0;
    }
    public void report()
    {
    	SmartDashboard.putNumber("Servo Value", getAngle());
    }
}