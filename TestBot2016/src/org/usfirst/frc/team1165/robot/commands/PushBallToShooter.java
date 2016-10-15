package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class PushBallToShooter extends Command
{
	double timeout;
	double override_speed;
	public PushBallToShooter(double timeout, double override_speed) 
    {
    	super(timeout);
        // Use requires() here to declare subsystem dependencies
        requires(Robot.moveServo);
        this.override_speed = override_speed;
    }
    public PushBallToShooter(double timeout) 
    {
    	super(timeout);
        // Use requires() here to declare subsystem dependencies
        requires(Robot.moveServo);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	override_speed = -2000;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(Robot.shooter.rightWheel.getSpeed() <= override_speed || isTimedOut())
    	Robot.moveServo.push();
    	//Robot.moveServo.report();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end()
    {
    	Robot.moveServo.idle();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    }
}