package org.usfirst.frc.team4338.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
/**
 * Practice Competition - Marble Game 
 * 
 * @author Orian Leitersdorf
 *
 * December 4th, 2017
 *
 */
public class Robot extends IterativeRobot {
	
	
	public static enum MARBLE_LOCATION {STILL_NOT_PLACED, AB, BC, CD, DE, EA}; /** The possible locations that the marble could be in **/
	private MARBLE_LOCATION marble; /** Object representing the marbles current location **/
	
	
	private Victor motor; /** The motor used between points A and B. (Connected through a Victor SP) **/
	
	private Servo servo; /** The servo motor used in the center **/
	
	private DebouncedDigitalInput pointA; /** The button at point A **/
	private DebouncedDigitalInput pointB; /** The button at point B **/
	private DebouncedDigitalInput pointC; /** The button at point C **/
	private DebouncedDigitalInput pointD; /** The button at point D **/
	private DebouncedDigitalInput pointE; /** The button at point E **/
	
	private Timer timer; /** General timer for printing out the time differences between the button clicks **/
	private double lastTime; /** Last time that a button was clicked **/
	
	private Timer MotorTimer; /** Timer used in order to determine when to start turning the motor **/
	private Timer ServoTimer; /** Timer used in order to automatically change the servo's position with time **/

	/**
	 * Sets up digital inputs, motors, controllers, and timers
	 */
	@Override
	public void robotInit() {
		
		motor = new Victor (Constants.LIFT_MOTOR_PORT);
		
		servo = new Servo (Constants.SERVO_PORT);
		
		pointA = new DebouncedDigitalInput (Constants.A_BUTTON_PORT);
		pointB = new DebouncedDigitalInput (Constants.B_BUTTON_PORT);
		pointC = new DebouncedDigitalInput (Constants.C_BUTTON_PORT);
		pointD = new DebouncedDigitalInput (Constants.D_BUTTON_PORT);
		pointE = new DebouncedDigitalInput (Constants.E_BUTTON_PORT);
	
		// Main timer
		timer = new Timer();
		timer.reset();
		timer.start();
		
		// Motor timer
		MotorTimer = new Timer();
		MotorTimer.reset();
		MotorTimer.start();
		
		// Servo timer
		ServoTimer = new Timer();
		ServoTimer.reset();
		ServoTimer.start();
		
	}
	
	/**
	 * Reset motors, marble position, etc once teleop starts
	 */
	public void teleopInit () {
		
		setMarbleLocation (MARBLE_LOCATION.STILL_NOT_PLACED); //Reset the marble's position
		
		setMotors(); //Change the motors accordingly
		
		lastTime = timer.get(); //Reset the main timer's last time
		
	}

	/**
	 * Checks the values of the digital inputs and prints out the results
	 */
	@Override
	public void teleopPeriodic() {

		double currentTime = timer.get();
		
		if (pointA.isNewPressed()) {
			
			logButtonPress ("A", currentTime);
			lastTime = currentTime;
			
			setMarbleLocation (MARBLE_LOCATION.AB);
			
			//When marble reaches point A, start the timer so that the motor waits a given amount of time and then start the motor
			MotorTimer.reset();
			MotorTimer.start();
			
		}
		else if (pointB.isNewPressed()) {
			
			logButtonPress ("B", currentTime);
			lastTime = currentTime;
			
			setMarbleLocation (MARBLE_LOCATION.BC);
			
			//When marble reaches point B, start the timer so that the servo waits a given amount of time and then turns to a different location (to drop the marble)
			ServoTimer.reset();
			ServoTimer.start();
			
		}
		else if (pointC.isNewPressed()) {
			
			logButtonPress ("C1", currentTime);
			lastTime = currentTime;
			
			setMarbleLocation (MARBLE_LOCATION.CD);
			
		}
		else if (pointD.isNewPressed()) {
			
			logButtonPress ("C2", currentTime);
			lastTime = currentTime;
			
			setMarbleLocation (MARBLE_LOCATION.DE);
			
		}
		else if (pointE.isNewPressed()) {
			
			logButtonPress ("D", currentTime);
			lastTime = currentTime;
			
			setMarbleLocation (MARBLE_LOCATION.EA);
			
		}
		
		setMotors(); //Update motors
		
	}
	
	/**
	 * Sets the motors to the correct position based on the marble variable
	 * (Main motor and Servo)
	 */
	private void setMotors() {
		
		switch (marble) {
		
		case STILL_NOT_PLACED:
			motor.set(Constants.MOTOR_AB_SPEED_BEFORE_TIMEOUT);
			servo.set(Constants.SERVO_AC_POSITION);
			break;
		case AB:
			servo.set(Constants.SERVO_AC_POSITION);
			if (MotorTimer.get() < Constants.MOTOR_AB_TIMEOUT) {
				motor.set(Constants.MOTOR_AB_SPEED_BEFORE_TIMEOUT);
			}
			else {
				motor.set(Constants.MOTOR_AB_SPEED_AFTER_TIMEOUT);
				MotorTimer.stop();
				MotorTimer.reset();
			}
			break;
		case BC:
			motor.set(Constants.MOTOR_BE_SPEED);
			if (ServoTimer.get() < Constants.SERVO_BC_TIMEOUT) {
				servo.set(Constants.SERVO_BC_POSITION_BEFORE_TIMEOUT);
			}
			else {
				servo.set(Constants.SERVO_BC_POSITION_AFTER_TIMEOUT);
				ServoTimer.stop();
				ServoTimer.reset();
			}
			break;
		case CD: case DE: case EA:
			motor.set(Constants.MOTOR_BE_SPEED);
			servo.set(Constants.SERVO_CE_POSITION);
			break;
			
		}
		
	}
	
	/**
	 * Returns the location of the marble
	 * @return
	 */
	public MARBLE_LOCATION getMarbleLocation() {
		return marble;
	}
	
	/**
	 * Sets the marble location to the new location
	 * @param newLocation
	 */
	public void setMarbleLocation (MARBLE_LOCATION newLocation) {
		marble = newLocation;
	}
	
	/**
	 * Logs the press of the given button at a given time
	 * @param buttonName
	 * @param currentTime
	 */
	public void logButtonPress (String buttonName, double currentTime) {
		System.out.println(buttonName + " Button Pressed at: " + currentTime + " seconds from the start of the robot, " + (currentTime - lastTime) + " since last press of a button");
	}

}

