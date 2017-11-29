package org.usfirst.frc.team4338.robot;

import edu.wpi.first.wpilibj.Joystick;
/**
 * Controls all of the data coming from the controllers
 * @author Orian Leitersdorf
 *
 */
public class JoystickController extends Joystick {
	
	// Identifiers for sensitive controls
	
	public static final int LEFT_JOY_X_IDENTIFIER = 0; // Left Joy X
	public static final int LEFT_JOY_Y_IDENTIFIER = 1; // Left Joy Y
	public static final int RIGHT_JOY_X_IDENTIFIER = 4; // Right Joy X
	public static final int RIGHT_JOY_Y_IDENTIFIER = 5; // Right Joy Y
	public static final int LEFT_TRIGGER_IDENTIFIER = 2; // Left Trigger
	public static final int RIGHT_TRIGGER_IDENTIFIER = 3; // Right Trigger
	
	// Identifiers for buttons
	
	public static final int A_BUTTON_IDENTIFIER = 1;	 // A
	public static final int B_BUTTON_IDENTIFIER = 2;	 // B
	public static final int X_BUTTON_IDENTIFIER = 3; // X
	public static final int Y_BUTTON_IDENTIFIER = 4; // Y
	public static final int LB_BUTTON_IDENTIFIER = 5; // Left Bumper
	public static final int RB_BUTTON_IDENTIFIER = 6; // Right Bumper
	public static final int LS_BUTTON_IDENTIFIER = 9; // Left Stick
	public static final int RS_BUTTON_IDENTIFIER = 10; // Right Stick
	public static final int BACK_BUTTON_IDENTIFIER = 7; // Back
	public static final int START_BUTTON_IDENTIFIER = 8; // Start

	// POV Constants
	
	public static final int POV_NORTH = 0; // North
	public static final int POV_NORTHEAST = 45; // Northeast
	public static final int POV_EAST = 90; // East
	public static final int POV_SOUTHEAST = 135; // Southeast
	public static final int POV_SOUTH = 180; // South
	public static final int POV_SOUTHWEST = 225; // Southwest
	public static final int POV_WEST = 270; // West
	public static final int POV_NORTHWEST = 315; // Northwest
	public static final int POV_NO_SELECTION = -1; // No Selection
	
	public static enum POV_DIRECTIONS {NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST, NOSELECTION, ERROR};
	
	
	
	/**
	 * Initializes the joystick controller using the given port
	 * @param port
	 */
	public JoystickController(int port) {
		super(port);
	}
	
	
	
	/**
	 * Returns the value of the leftJoyXAxis
	 * @return
	 */
	public double getLeftJoyXAxis() {
		return super.getRawAxis(LEFT_JOY_X_IDENTIFIER);
	}
	
	/**
	 * Returns the value of the leftJoyYAxis
	 * @return
	 */
	public double getLeftJoyYAxis() {
		return super.getRawAxis(LEFT_JOY_Y_IDENTIFIER);
	}
	
	/**
	 * Returns the value of the rightJoyXAxis
	 * @return
	 */
	public double getRightJoyXAxis() {
		return super.getRawAxis(RIGHT_JOY_X_IDENTIFIER);
	}
	
	/**
	 * Returns the value of the rightJoyYAxis
	 * @return
	 */
	public double getRightJoyYAxis() {
		return super.getRawAxis(RIGHT_JOY_Y_IDENTIFIER);
	}
	
	/**
	 * Returns the value of the leftTrigger
	 * @return
	 */
	public double getLeftTrigger() {
		return super.getRawAxis(LEFT_TRIGGER_IDENTIFIER);
	}
	
	/**
	 * Returns the value of the rightTrigger
	 * @return
	 */
	public double getRightTrigger() {
		return super.getRawAxis(RIGHT_TRIGGER_IDENTIFIER);
	}
	
	
	
	/**
	 * Returns the status of the button
	 * @return
	 */
	public boolean getXButton() {
		return super.getRawButton(X_BUTTON_IDENTIFIER);
	}
	
	/**
	 * Returns the status of the button
	 * @return
	 */
	public boolean getAButton() {
		return super.getRawButton(A_BUTTON_IDENTIFIER);
	}
	
	/**
	 * Returns the status of the button
	 * @return
	 */
	public boolean getYButton() {
		return super.getRawButton(Y_BUTTON_IDENTIFIER);
	}
	
	/**
	 * Returns the status of the button
	 * @return
	 */
	public boolean getBButton() {
		return super.getRawButton(B_BUTTON_IDENTIFIER);
	}
	
	/**
	 * Returns the status of the button
	 * @return
	 */
	public boolean getLBButton() {
		return super.getRawButton(LB_BUTTON_IDENTIFIER);
	}
	
	/**
	 * Returns the status of the button
	 * @return
	 */
	public boolean getRBButton() {
		return super.getRawButton(RB_BUTTON_IDENTIFIER);
	}
	
	/**
	 * Returns the status of the button
	 * @return
	 */
	public boolean getLSButton() {
		return super.getRawButton(LS_BUTTON_IDENTIFIER);
	}
	
	/**
	 * Returns the status of the button
	 * @return
	 */
	public boolean getRSButton() {
		return super.getRawButton(RS_BUTTON_IDENTIFIER);
	}
	
	/**
	 * Returns the status of the button
	 * @return
	 */
	public boolean getBackButton() {
		return super.getRawButton(BACK_BUTTON_IDENTIFIER);
	}
	
	/**
	 * Returns the status of the button
	 * @return
	 */
	public boolean getStartButton() {
		return super.getRawButton(START_BUTTON_IDENTIFIER);
	}

	/**
	 * Returns the current state of POV in the enum POV format. Note there is a difference between no selection and error.
	 * @return
	 */
	public POV_DIRECTIONS getPOVEnum() {
		
		switch(super.getPOV()) {
		
		case POV_NORTH:
			return POV_DIRECTIONS.NORTH;
			
		case POV_NORTHEAST:
			return POV_DIRECTIONS.NORTHEAST;
			
		case POV_EAST:
			return POV_DIRECTIONS.EAST;
			
		case POV_SOUTHEAST:
			return POV_DIRECTIONS.SOUTHEAST;
			
		case POV_SOUTH:
			return POV_DIRECTIONS.SOUTH;
			
		case POV_SOUTHWEST:
			return POV_DIRECTIONS.SOUTHWEST;
			
		case POV_WEST:
			return POV_DIRECTIONS.WEST;
			
		case POV_NORTHWEST:
			return POV_DIRECTIONS.NORTHWEST;
			
		case POV_NO_SELECTION:
			return POV_DIRECTIONS.NOSELECTION;
			
		default:
			return POV_DIRECTIONS.ERROR;
			
		}
		
		
	}
	

}
