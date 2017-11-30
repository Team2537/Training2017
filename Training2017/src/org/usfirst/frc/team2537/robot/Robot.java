package org.usfirst.frc.team2537.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	
	
	@Override
	public void robotInit() {
		
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
