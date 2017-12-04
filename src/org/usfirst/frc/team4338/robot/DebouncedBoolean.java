package org.usfirst.frc.team4338.robot;

/**
 * Debounces a boolean so that isNewPressed only returns true when current state goes from false to true
 * @author Orian Leitersdorf
 *
 */
public class DebouncedBoolean {
	
	/** The previous state of the button **/
	private boolean previousState;
	
	/**
	 * You specify what the first state is going to be so that it can set previousState to that
	 * (For example: If your button starts in the on position, and you don't want isNewPressed to return true on the
	 *  first call, then you specify originalState as true here)
	 * @param originalState
	 */
	public DebouncedBoolean (boolean originalState) {
		previousState = originalState;
	}
	
	/**
	 * Returns true if {the new value is true and the previous is false}.  
	 * @param currentState
	 * @return
	 */
	public boolean isNewPressed (boolean currentState) {
		if (!previousState && currentState) {
			previousState = currentState;
			return true;
		}
		else {
			previousState = currentState;
			return false;
		}
	}
	

}
