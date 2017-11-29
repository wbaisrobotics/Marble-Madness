package org.usfirst.frc.team4338.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class DebouncedDigitalInput extends DigitalInput {
	
	private DebouncedBoolean booleanDebouncer; /** Tracks the changes in the values **/

	/**
	 * Initializes the DebouncedDigitalInput with a given initial state and a port for the digital input
	 * @param originalState
	 * @param port
	 */
	public DebouncedDigitalInput(int port) {
		
		super (port);
		
		this.booleanDebouncer = new DebouncedBoolean (super.get());
		
	}
	
	/**
	 * Returns true if the digital input is newly pressed
	 * @return
	 */
	public boolean isNewPressed() {
		return booleanDebouncer.isNewPressed(super.get()); /** Calls isNewPressed with value from digital input **/
	}

}
