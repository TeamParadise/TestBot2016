package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.commands.StopWinch;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem 
{
    
	public CANTalon winchTalon;
	public Winch()
	{
		winchTalon = new CANTalon(2);
		winchTalon.setInverted(true);
	}
    public void initDefaultCommand() 
    {
    	setDefaultCommand(new StopWinch());
    }
    public void moveWinchUp()
    {
    	//winchTalon.set(0.7285);
    	winchTalon.set(1.0);
    }
    public void stopWinch()
    {
    	winchTalon.set(0);
    }
    public void respoolWinch()
    {
    	winchTalon.set(-0.3141592691);
    }
}