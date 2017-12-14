package org.usfirst.frc.team2537.robot;

import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
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
	public static DriveSubsystem driveSys;
	
	@Override
	public void robotInit() {
		driveSys = new DriveSubsystem();
	    driveSys.initDefaultCommand();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		leftTalon = new CANTalon(Ports.LEFT_TALON);
		rightTalon = new CANTalon(Ports.RIGHT_TALON);
		ultron = new Ultrasonic(Ports.ULTRASONIC_TRIGGER, Ports.ULTRASONIC_ECHO);
		limitSwitch = new DigitalInput(Ports.LIMITSWITCH_ONE);
		ultron.setAutomaticMode(true);
		Encoder encoder = new Encoder(5, 6, true, EncodingType.k4X);
		
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
		Encoder encoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		encoder.reset();
		int count = encoder.get();
		limitSwitch.get();
		leftTalon.set(-.3);
		rightTalon.set(.3);
		// if (limitEngaged)
		if (range/25.3 <= 10){
			leftTalon.set(0);
			rightTalon.set(0);
		} else if(count == 45){
			leftTalon.set(.0);
			rightTalon.set(.3);
		} else if((count >= 65)  && (count <90)){
			leftTalon.set(-.3);
			rightTalon.set(.3);
		} else if(count == 90){
			leftTalon.set(-.6);
			rightTalon.set(.3);
		} else if((count >= 105) && (count < 135)){
			leftTalon.set(-.3);
			rightTalon.set(.3);
		} else if(count == 135){
			leftTalon.set(-.6);
			rightTalon.set(.3);
		} else if ((count >= 155) && (count < 175)){
			leftTalon.set(-.3);
			rightTalon.set(.3);
		} else if(count == 175){
			leftTalon.set(0);
			rightTalon.set(.3);
		} else if(( count >= 195) && (count < 205)){
			leftTalon.set(-.3);
			rightTalon.set(.3);
		} else if(count == 205){
			leftTalon.set(0);
			rightTalon.set(0);
		} else if(range == 0){
			limitEngaged = true;
		}
	
		
		
		
		
			

	}

	public boolean getLimit(){
		return limitSwitch.get();
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
/