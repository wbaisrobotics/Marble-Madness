package org.usfirst.frc.team4338.robot;

/**
 * Object representing the marble and the positions it could be in
 * @author orianleitersdorf
 *
 */
public class Marble {
	public static enum LOCATIONS {STILL_NOT_PLACED, AF, FB, BA, FINISHED}; /** The possible locations that the marble could be in (F representing between A and B stopping **/
	private LOCATIONS location; /** Object representing the marbles current location **/
	
	/**
	 * Initializes the marble in the STILL_NOT_PLACED position
	 */
	public Marble() {
		location = LOCATIONS.STILL_NOT_PLACED;
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
	
	
}
