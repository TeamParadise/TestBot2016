package org.usfirst.frc.team1165.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarAutonomous extends CommandGroup
{

	public LowBarAutonomous()
	{
		addSequential(new DriveStraight(0.75,0.4));
		addParallel(new  SetLinearActuatorSetpoint(4.5),1);
		addSequential(new PrepareForLowBar());
		addSequential(new DriveUnderLowBar());
	}
}