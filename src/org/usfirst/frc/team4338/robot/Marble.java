package org.usfirst.frc.team4338.robot;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Object representing the marble and the positions it could be in
 * @author orianleitersdorf
 *
 */
public class Marble {
	
	/** The possible locations that the marble could be in (F representing between A and B stopping) (E representing between B and A at Servo) **/
	public static enum LOCATIONS {STILL_NOT_PLACED, AF, F, FB, BE, EA, FINISHED}; 
	/** Object representing the marbles current location **/
	private LOCATIONS location; 
	
	private static final long LIFT_AF_LENGTH = 300;
	private static final long LIFT_F_TIMEOUT = 450;
	private static final long SERVO_TIMEOUT_BA = 900;
	
	/** Last time that a button was clicked **/
	private long startTime;
	private long lastTime; 
	
	private Timer timer;
	
	/**
	 * Sets up all the timers, should be called in teleopInit
	 */
	public Marble() {
		
		location = LOCATIONS.STILL_NOT_PLACED;
		
		startTime = System.currentTimeMillis();
		lastTime = System.currentTimeMillis();
		
		timer = new Timer();

		
	}
	
	/**
	 * Should be called when the start button is pressed, only call once
	 */
	public void startPressed() {
		
		long currentTime = System.currentTimeMillis();
		long currentTimeFromStart = currentTime - startTime;
		
		logButtonPress ("Start", currentTimeFromStart);
		lastTime = currentTime;

		setLocation (LOCATIONS.AF);

		timer.schedule(new TimerTask() {

			public void run() {AFDrivingComplete();}
			
		}, LIFT_AF_LENGTH);
		
	}
	
	/**
	 * Should be called when the B button is pressed, only call once
	 */
	public void bPressed() {
		
		long currentTime = System.currentTimeMillis();
		long currentTimeFromStart = currentTime - startTime;
		
		logButtonPress ("B", currentTimeFromStart);
		lastTime = currentTime;

		setLocation (LOCATIONS.BE);
		
		timer.schedule(new TimerTask() {

			public void run() {ServoTimeoutComplete();}
			
		}, SERVO_TIMEOUT_BA);

	}
	
	/**
	 * Should be called when the A (return) button is pressed, only call once
	 */
	public void aPressed() {
		
		long currentTime = System.currentTimeMillis();
		long currentTimeFromStart = currentTime - startTime;
		
		logButtonPress ("Finish", currentTimeFromStart);
		lastTime = currentTime;

		setLocation (LOCATIONS.FINISHED);
		
	}
	
	/**
	 * Called by timer when driving from A to F is complete
	 */
	private void AFDrivingComplete() {
		
		setLocation (LOCATIONS.F);
		
		System.out.println("Starting wait between A and B");
		
		timer.schedule(new TimerTask() {

			public void run() {FTimeoutComplete();}
			
		}, LIFT_F_TIMEOUT);
		
	}
	
	/**
	 * Called by timer when the timeout at F is complete
	 */
	private void FTimeoutComplete() {
		
		setLocation (LOCATIONS.FB);
		
	}
	
	/**
	 * Called by timer when the delay after B is complete
	 */
	private void ServoTimeoutComplete() {
		
		setLocation (LOCATIONS.EA);
		
	}
	
	/**
	 * Returns the current location of the marble
	 * @return
	 */
	public LOCATIONS getLocation() {
		return location;
	}

	/**
	 * Sets the current location of the marble to a new location
	 * @param newLocation
	 */
	public void setLocation(LOCATIONS newLocation) {
		location = newLocation;
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
