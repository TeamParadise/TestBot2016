package org.usfirst.frc.team1165.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarAutonomous extends CommandGroup
{

	public LowBarAutonomous()
	{
		addParallel(new  SetLinearActuatorSetpoint(5.0),1);
		addSequential(new PrepareForLowBar());
		addSequential(new DriveToDefense());
		addSequential(new DriveUnderLowBar());
	}
}