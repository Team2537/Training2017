package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
	CANTalon leftTalon;
	CANTalon rightTalon;
	Ultrasonic ultron;
	DigitalInput limitSwitch;
	Joystick leftjoystick;
	public DriveSubsystem () {
		leftTalon = new CANTalon(Ports.LEFT_TALON);
		rightTalon = new CANTalon(Ports.RIGHT_TALON);
		ultron = new Ultrasonic(Ports.ULTRAONIC_TRIGGER, Ports.ULTRASONIC_ECHO);
		limitSwitch = new DigitalInput(Ports.LIMITSWITCH_ONE);
		leftjoystick = new Joystick(Ports.LEFT_JOYSTICK);
		ultron.setAutomaticMode(true);
	}
	
	
    public void initDefaultCommand() {
       this.setDefaultCommand(new DriveCommand());
    }
}

