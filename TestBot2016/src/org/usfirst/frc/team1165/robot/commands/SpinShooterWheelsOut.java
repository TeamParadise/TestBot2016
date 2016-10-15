package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinShooterWheelsOut extends Command {

	double rpm;
    public SpinShooterWheelsOut(double rpm) 
    {
    	this.rpm = rpm;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.shooter.setRpm(rpm);
    	Robot.shooter.report();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return !Robot.oi.lowGoal.get() && !Robot.oi.highGoal.get();
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.shooter.stopThrottle();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}