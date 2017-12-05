package org.usfirst.frc.team4338.robot;

import org.usfirst.frc.team4338.robot.Marble.LOCATIONS;

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
	
	private Marble marble;

	private Lift lift; /** The system representing the Lift **/

	private Servo servo; /** The Servo motor used in the center **/

	private DebouncedDigitalInput start; /** The button which starts everything **/
	private DebouncedDigitalInput pointA; /** The button at point A **/
	private DebouncedDigitalInput pointB; /** The button at point B (Return) **/

	private Timer timer; /** General timer for printing out the time differences between the button clicks **/
	private double lastTime; /** Last time that a button was clicked **/

	private Timer MotorTimer; /** Timer used in order to determine when to start turning the motor **/
	private Timer ServoTimer; /** Timer used in order to automatically change the servo's position with time **/

	/**
	 * Sets up digital inputs, motors, controllers, and timers
	 */
	@Override
	public void robotInit() {
		
		marble = new Marble();

		lift = new Lift (new Victor (Constants.LIFT_MOTOR_PORT));

		servo = new Servo (Constants.SERVO_PORT);

		pointA = new DebouncedDigitalInput (Constants.A_BUTTON_PORT);
		pointB = new DebouncedDigitalInput (Constants.B_BUTTON_PORT);
		start = new DebouncedDigitalInput (Constants.START_BUTTON_PORT);

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

		marble.setLocation (LOCATIONS.STILL_NOT_PLACED); //Reset the marble's position

		setMotors(); //Change the motors accordingly

		lastTime = timer.get(); //Reset the main timer's last time

	}

	/**
	 * Checks the values of the digital inputs and prints out the results
	 */
	@Override
	public void teleopPeriodic() {

		double currentTime = timer.get();

		if (start.isNewPressed()) {

			logButtonPress ("Start", currentTime);
			lastTime = currentTime;

			marble.setLocation (LOCATIONS.AF);

			//When marble reaches point A, start the timer so that the motor waits a given amount of time and then start the motor
			MotorTimer.reset();
			MotorTimer.start();

		}
		else if (pointB.isNewPressed()) {

			logButtonPress ("B", currentTime);
			lastTime = currentTime;

			marble.setLocation (LOCATIONS.BA);

			//When marble reaches point B, start the timer so that the servo waits a given amount of time and then turns to a different location (to drop the marble)
			ServoTimer.reset();
			ServoTimer.start();

		}
		else if (pointA.isNewPressed()) {

			logButtonPress ("Finish", currentTime);
			lastTime = currentTime;

			marble.setLocation (LOCATIONS.FINISHED);


		}


		setMotors(); //Update motors

	}

	/**
	 * Sets the motors to the correct position based on the marble variable
	 * (Main motor and Servo)
	 */
	private void setMotors() {

		switch (marble.getLocation()) {

		case STILL_NOT_PLACED:
			lift.stop();
			servo.set(Constants.SERVO_INITIAL_POSITION);
			break;
		case AF:
			servo.set(Constants.SERVO_INITIAL_POSITION);
			if (MotorTimer.get() < Lift.MOTOR_AF_TIMEOUT) {
				lift.moveSlowly();
			}
			else {
				marble.setLocation (LOCATIONS.FB);
				MotorTimer.reset();
				MotorTimer.start();
			}
			break;
		case FB:
			servo.set(Constants.SERVO_INITIAL_POSITION);
			if (MotorTimer.get() < Lift.MOTOR_FB_TIMEOUT) {
				lift.stop();
			}
			else {
				lift.moveQuickly();
			}
			break;
		case BA:
			lift.stop();
			if (ServoTimer.get() < Constants.SERVO_BC_TIMEOUT) {
				servo.set(Constants.SERVO_INITIAL_POSITION);
			}
			else {
				servo.set(Constants.SERVO_FINAL_POSITION);
			}
			break;
		case FINISHED:
			lift.stop();
			servo.set(Constants.SERVO_INITIAL_POSITION);
		}

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

