package org.usfirst.frc.team4338.robot;

/**
 * Class that maps the wiring of the robot
 * @author orianleitersdorf
 *
 */
public class Constants {

	/** PWM **/
	public static final int LIFT_MOTOR_PORT = 0;
	public static final int SERVO_PORT = 1;
	
	/** DIO **/
	public static final int A_BUTTON_PORT = 0;
	public static final int B_BUTTON_PORT = 1;
	public static final int C_BUTTON_PORT = 2;
	public static final int D_BUTTON_PORT = 3;
	public static final int E_BUTTON_PORT = 4;
	
	
	/** Desired Values - Main Motor **/
	public static final double MOTOR_START_SPEED = 0;
	
	public static final double MOTOR_AB_TIMEOUT = 4.5; //Before the timeout passes go at BEFORE_TIMEOUT, after timeout passes go at AFTER_TIMEOUT
	public static final double MOTOR_AB_SPEED_BEFORE_TIMEOUT = 0; //Don't turn until timeout ends
	public static final double MOTOR_AB_SPEED_AFTER_TIMEOUT = 0.1; //Turn slowly once timeout ends
	
	public static final double MOTOR_BE_SPEED = 0;
	
	/** Desired Values - Servo Motor **/
	public static final double SERVO_AC_POSITION = 0;
	
	public static final double SERVO_BC_TIMEOUT = 9; //Before the timeout passes go at BEFORE_TIMEOUT, after timeout passes go at AFTER_TIMEOUT
	public static final double SERVO_BC_POSITION_BEFORE_TIMEOUT = 0;
	public static final double SERVO_BC_POSITION_AFTER_TIMEOUT = 0.5;
	public static final double SERVO_CE_POSITION = 1;
	

}
