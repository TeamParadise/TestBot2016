package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveStraightAuto extends CommandGroup {
    
    public  DriveStraightAuto()
    {
    	addSequential(new DriveStraight(-0.95, 2));
    }
}
