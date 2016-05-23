package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAtHighGoal extends CommandGroup {
    
    public  ShootAtHighGoal() 
    {
    	//addSequential(new PositionRobotForShooting());
    	addSequential(new SpinShooterWheelsOut(-5000));//Gives right wheel negative rpm
    	addParallel(new PushBallToShooter(5));
    	//addSequential(new WaitCommand(0.5));
    	addSequential(new SetLinearActuatorSetpoint(0.9));
    }
}
