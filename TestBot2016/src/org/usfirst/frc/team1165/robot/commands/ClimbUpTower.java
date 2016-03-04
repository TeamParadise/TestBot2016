package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ClimbUpTower extends CommandGroup
{

	public ClimbUpTower()
	{
		addParallel(new DriveStraight(0.5283,20));
		addSequential(new StartWinch());
	}
}