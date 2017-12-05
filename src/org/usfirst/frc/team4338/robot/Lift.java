package org.usfirst.frc.team4338.robot;

import edu.wpi.first.wpilibj.SpeedController;

public class Lift {
	
	public static final double MOTOR_AF_TIMEOUT = 0.3; //Before the timeout passes go at BEFORE_TIMEOUT, after timeout passes go at AFTER_TIMEOUT
	public static final double MOTOR_FB_TIMEOUT = 4.5; //Before the timeout passes go at BEFORE_TIMEOUT, after timeout passes go at AFTER_TIMEOUT
	private static final double MOTOR_STOP_SPEED = 0; //Don't turn until timeout ends
	private static final double MOTOR_SLOW_SPEED = -0.6;; //Turn slowly once timeout ends
	private static final double MOTOR_FAST_SPEED = -1.0; //Turn slowly once timeout ends
	
	private SpeedController liftMotor; //The motor which turns the lift
	
	/**
	 * Initializes with the given speed controller
	 * @param liftMotor
	 */
	public Lift( SpeedController liftMotor ) {
		this.liftMotor = liftMotor;
	}
	
	/**
	 * Turns the lift slowly
	 */
	public void moveSlowly() {
		liftMotor.set(MOTOR_SLOW_SPEED);
	}
	
	/**
	 * Turns the lift quickly
	 */
	public void moveQuickly() {
		liftMotor.set(MOTOR_FAST_SPEED);
	}
	
	/**
	 * Stops the lift
	 */
	public void stop() {
		liftMotor.set(MOTOR_STOP_SPEED);
	}

}
