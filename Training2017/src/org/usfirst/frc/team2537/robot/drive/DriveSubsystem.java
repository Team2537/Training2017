package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	CANTalon leftTalon;
	CANTalon rightTalon;
	Ultrasonic ultron;
	DigitalInput limitSwitch;
	Encoder encoder;
	double range;
	boolean limitEngaged;

	public DriveSubsystem() {
		leftTalon = new CANTalon(Ports.LEFT_TALON);
		rightTalon = new CANTalon(Ports.RIGHT_TALON);
		ultron = new Ultrasonic(Ports.ULTRASONIC_TRIGGER, Ports.ULTRASONIC_ECHO);
		limitSwitch = new DigitalInput(Ports.LIMITSWITCH_ONE);
		ultron.setAutomaticMode(true);
		encoder = new Encoder(5, 6, true, EncodingType.k4X);
	}

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new DriveCommand());
	}

	public void followPath() {
		double range = ultron.getRangeInches();
		limitEngaged = limitSwitch.get();
		encoder.reset();
		int count = encoder.get();
		limitSwitch.get();
		leftTalon.set(-.3);
		rightTalon.set(.3);
		// if (limitEngaged)
		if (range / 25.3 <= 10) {
			leftTalon.set(0);
			rightTalon.set(0);
		} else if (count == 45) {
			leftTalon.set(.0);
			rightTalon.set(.3);
		} else if ((count >= 65) && (count < 90)) {
			leftTalon.set(-.3);
			rightTalon.set(.3);
		} else if (count == 90) {
			leftTalon.set(-.6);
			rightTalon.set(.3);
		} else if ((count >= 105) && (count < 135)) {
			leftTalon.set(-.3);
			rightTalon.set(.3);
		} else if (count == 135) {
			leftTalon.set(-.6);
			rightTalon.set(.3);
		} else if ((count >= 155) && (count < 175)) {
			leftTalon.set(-.3);
			rightTalon.set(.3);
		} else if (count == 175) {
			leftTalon.set(0);
			rightTalon.set(.3);
		} else if ((count >= 195) && (count < 205)) {
			leftTalon.set(-.3);
			rightTalon.set(.3);
		} else if (count == 205) {
			leftTalon.set(0);
			rightTalon.set(0);
		} else if (range == 0) {
			limitEngaged = true;
		}
		}
	public void ultron(){
		if(ultron.getRangeInches()  <= 12  ){
			
		}
	}
	

}
