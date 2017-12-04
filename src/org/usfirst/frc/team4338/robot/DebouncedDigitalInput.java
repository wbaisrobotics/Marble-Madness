package org.usfirst.frc.team4338.robot;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Debounces a digital input:
 * 	When the button is pressed, isNewPressed would only returns true once and then always false until the button is let go and then pressed again
 * @author Orian Leitersdorf
 *
 */
public class DebouncedDigitalInput extends DigitalInput {
	
	private DebouncedBoolean booleanDebouncer; /** Tracks the changes in the values **/

	/**
	 * Initializes the DebouncedDigitalInput with a given initial state and a port for the digital input
	 * @param originalState
	 * @param port
	 */
	public DebouncedDigitalInput(int port) {
		
		super (port); //Initialize the digital input
		
		this.booleanDebouncer = new DebouncedBoolean (super.get()); //Setup a DebouncedBoolean with the current state of the input
		
	}
	
	/**
	 * Returns true if the digital input is newly pressed
	 * @return
	 */
	public boolean isNewPressed() {
		return booleanDebouncer.isNewPressed(super.get()); // Calls isNewPressed with value from digital input
	}

}
