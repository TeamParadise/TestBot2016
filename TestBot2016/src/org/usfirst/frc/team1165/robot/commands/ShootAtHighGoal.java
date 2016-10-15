package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAtHighGoal extends CommandGroup
{
    
    public  ShootAtHighGoal() 
    {
    	//addSequential(new PositionRobotForShooting());
    	//addSequential(new WaitCommand(0.5));
    	addSequential(new SetLinearActuatorSetpoint(0.9),1);
    	addParallel(new SpinShooterWheelsOut(-4500),3);//Gives right wheel negative rpm
    	addSequential(new PushBallToShooter(3), 3);
    }
}