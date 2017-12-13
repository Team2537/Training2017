package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	Talon leftTalon;
	Talon rightTalon;
	Ultrasonic ultrasensor;
	DigitalInput limitSwitch;
	Encoder leftEncoder;
	Encoder rightEncoder;
	int step;
	double range;
	int rightCount;
	int leftCount;
	double diameter;
	double circumference;
	
	public DriveSubsystem() {
		leftTalon = new Talon(Ports.LEFT_TALON);
		rightTalon = new Talon(Ports.RIGHT_TALON);
		ultrasensor = new Ultrasonic(Ports.UNTRASONIC_TRIGGER, Ports.ULTRASONIC_ECHO);
		limitSwitch = new DigitalInput(Ports.LIMIT_SWITCH);
		leftEncoder = new Encoder(Ports.LEFTENCODERONE, Ports.LEFTENCODERTWO, false, Encoder.EncodingType.k4X);
		rightEncoder = new Encoder(Ports.RIGHTENCODERONE, Ports.RIGHTENCODERTWO, false, Encoder.EncodingType.k4X);
		ultrasensor.setAutomaticMode(true);
		diameter = 8;
		circumference = diameter*Math.PI;
		step = 1;
	}

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new DriveCommand());

	}
	
	public void followPath() {
		rightCount = rightEncoder.get();
		leftCount = leftEncoder.get();
		if(step == 1)
			moveForward(0.5);
		else if(step == 2)
			ultrasonicStop(12);
		else if(step == 3)
			turnRight(.5);
//		else if(step == 4)
//			moveForward(0.5);
//		else if(step == 5)
//			ultrasonicStop(12);
//		else if(step == 6)
//			turnLeft(.5);
//		else if(step == 7)
//			moveForward(.5);
//		else if(step == 8)
//			ultrasonicStop(12);
//		else if(step == 9)
//			turnLeft(.5);
//		else if(step == 10)
//			moveDistance(48);
//		else if(step == 11)
//			turnRight(.5);
	}
	/**
	 * stops the robot at a certain distance from what the ultrasonic senses
	 * @param stopRange the range at where the robot stops
	 */
	public void ultrasonicStop(double stopRange) {		
		range = ultrasensor.getRangeInches();
		if(range <= stopRange) {
			stopRobot();
			step++;
			resetEncoders();
		}
	}
	
	/**
	 * moves forward at a certain speed
	 * @param speed speed at which the robot moves
	 */
	public void moveForward(double speed){
		leftTalon.set(speed);
		rightTalon.set(-speed);
		step++;
	}
	
	/**
	 * turns the robot right
	 */
	public void turnRight(double speed) {
		rightTalon.set(speed);
		leftTalon.set(speed);
		if(rightCount >= 45) {
			stopRobot();
			step++;
			resetEncoders();
		}
	}
	
	/**
	 * turns the robot left
	 */
	public void turnLeft(double speed) {
		rightTalon.set(-speed);
		leftTalon.set(-speed);
		if(rightCount >= 45) {
			stopRobot();
			step++;
			resetEncoders();
		}	
	}
	
	/**
	 * stops the robot
	 */
	public void stopRobot() {
		rightTalon.set(0);
		leftTalon.set(0);
	}
	
	/**
	 * moves the robot forward a certain distance
	 * @param forwardDistance the distance the robot moves
	 */
	public void moveDistance(double forwardDistance){
		rightCount = rightEncoder.get();
		moveForward(0.3);
		if((rightCount/360)*circumference >= forwardDistance) {
			stopRobot();
			step++;
			resetEncoders();
		}
	}
	
	/**
	 * stops the robot when the limit switch is hit
	 */
	public void limitSwitchStop() {
		if(limitSwitch.get()) {
			stopRobot();
			step++;
		}
	}
	
	/**
	 * resets the encoders
	 */
	public void resetEncoders() {
		rightEncoder.reset();
		leftEncoder.reset();
	}	
}
