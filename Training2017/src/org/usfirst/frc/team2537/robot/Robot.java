package org.usfirst.frc.team2537.robot;

import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	public static DriveSubsystem driveSys;
	
	
	@Override
	public void robotInit() {
		driveSys = new DriveSubsystem();
		driveSys.initDefaultCommand();
	}

	@Override
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		double range = ultron.getRangeMM();
		boolean limitEngaged = limitSwitch.get();
		leftTalon.set(-.3);
		rightTalon.set(.3);
		// if (limitEngaged)
		if (range/25.3 <= 10){
			leftTalon.set(0);
			rightTalon.set(0);
		}

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}
