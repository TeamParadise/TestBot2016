package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToDefense extends CommandGroup
{
    
    public  DriveToDefense()
    {
        addSequential(new DriveStraight(0.5,0.75));
    }
}
