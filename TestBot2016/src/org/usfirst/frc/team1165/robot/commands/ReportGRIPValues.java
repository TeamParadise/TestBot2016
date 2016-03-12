package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReportGRIPValues extends Command {

    public ReportGRIPValues() 
    {
    	requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
		SmartDashboard.putNumber("CenterX", Robot.vision.getCenterX());
		SmartDashboard.putNumber("Current X", Robot.vision.getCurrentX());
		SmartDashboard.putNumber("Center Y",Robot.vision.getCenterY());
		SmartDashboard.putNumber("Current Y", Robot.vision.getCurrentY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}