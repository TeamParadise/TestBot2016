package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootAtLowGoal extends CommandGroup {
    
    public  ShootAtLowGoal() 
    {
    	addSequential(new SetLinearActuatorSetpoint(3.9),2);
    	//addSequential(new PositionRobotForShooting());
    	addParallel(new PushBallToShooter(5),3);
    	addSequential(new SpinShooterWheelsOut(-2500),3);//Gives right wheel negative rpm
    	//addSequential(new WaitCommand(0.5));
    	//addSequential(new SetLinearActuatorSetpoint(0.9));
    }
}
