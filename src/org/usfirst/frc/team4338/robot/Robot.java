package org.usfirst.frc.team4338.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
/**
 * Practice Competition robot, can drive the motor using values from the controller, move the servo according to the SmartDashboard,
 *  and detect changes in the digital inputs (wired to the buttons)
 * 
 * @author orianleitersdorf
 *
 */
public class Robot extends IterativeRobot {
	
	
	public static enum MARBLE_LOCATION {STILL_NOT_PLACED, AB, BC, CD, DE, EA};
	private MARBLE_LOCATION marble;
	
	
	private Victor motor;
	
	private Servo servo;
	
	private DebouncedDigitalInput pointA;
	private DebouncedDigitalInput pointB;
	private DebouncedDigitalInput pointC;
	private DebouncedDigitalInput pointD;
	private DebouncedDigitalInput pointE;
	
	private Timer timer;
	
	private double lastTime;
	
	private Timer MotorTimer;
	private Timer ServoTimer;

	/**
	 * Sets up digital inputs, motors, controllers, and timer
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
	
		timer = new Timer();
		
		timer.reset();
		timer.start();
		
		
		MotorTimer = new Timer();
		MotorTimer.reset();
		MotorTimer.start();
		
		ServoTimer = new Timer();
		ServoTimer.reset();
		ServoTimer.start();
		
	}
	
	/**
	 * Reset motors, marble position, etc once teleop starts
	 */
	public void teleopInit () {
		
		marble = MARBLE_LOCATION.STILL_NOT_PLACED;
		
		setMotors();
		
		lastTime = timer.get();
		
	}

	/**
	 * Checks the values of the digital inputs and prints out the results
	 */
	@Override
	public void teleopPeriodic() {

		double currentTime = timer.get();
		
		if (pointA.isNewPressed()) {
			System.out.println("A Button Pressed at: " + currentTime + " seconds from the start of the robot, " + (currentTime - lastTime) + " since last press of a button");
			lastTime = currentTime;
			
			marble = MARBLE_LOCATION.AB;
			MotorTimer.reset();
			MotorTimer.start();
		}
		if (pointB.isNewPressed()) {
			System.out.println("B Button Pressed at: " + currentTime + " seconds from the start of the robot, " + (currentTime - lastTime) + " since last press of a button");
			lastTime = currentTime;
			
			marble = MARBLE_LOCATION.BC;
			ServoTimer.reset();
			ServoTimer.start();
		}
		if (pointC.isNewPressed()) {
			System.out.println("C Button Pressed at: " + currentTime + " seconds from the start of the robot, " + (currentTime - lastTime) + " since last press of a button");
			lastTime = currentTime;
			
			marble = MARBLE_LOCATION.CD;
		}
		if (pointD.isNewPressed()) {
			System.out.println("D Button Pressed at: " + currentTime + " seconds from the start of the robot, " + (currentTime - lastTime) + " since last press of a button");
			lastTime = currentTime;
			
			marble = MARBLE_LOCATION.DE;
		}
		if (pointE.isNewPressed()) {
			System.out.println("E Button Pressed at: " + currentTime + " seconds from the start of the robot, " + (currentTime - lastTime) + " since last press of a button");
			lastTime = currentTime;
			
			marble = MARBLE_LOCATION.EA;
		}
		
		setMotors(); //Update motors
		
	}
	
	/**
	 * Sets the motors to the correct position based on the marble variable
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

}

