package org.usfirst.frc.team2537.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	CANTalon leftTalon;
	CANTalon rightTalon;
	Ultrasonic ultron;
	DigitalInput limitSwitch;
	
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		leftTalon = new CANTalon(Ports.LEFT_TALON);
		rightTalon = new CANTalon(Ports.RIGHT_TALON);
		ultron = new Ultrasonic(Ports.ULTRAONIC_TRIGGER, Ports.ULTRASONIC_ECHO);
		limitSwitch = new DigitalInput(Ports.LIMITSWITCH_ONE);
		ultron.setAutomaticMode(true);

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
		leftTalon.set(-.2);
		rightTalon.set(.2);
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
