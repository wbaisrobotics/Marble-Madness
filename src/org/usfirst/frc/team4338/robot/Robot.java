package org.usfirst.frc.team4338.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 * Practice Competition robot, can drive the motor using values from the controller and detect changes in the digital inputs (wired to the buttons)
 * 
 * Victor (motor) is controlled by the Right X Axis on the controller
 * 
 * Use the following wiring scheme:
 * 
 * 	PWM 0 --> Victor
 * 	PWM 1 --> Button Placed at point A
 *  	PWM 2 --> Button Placed at point B
 * 	PWM 3 --> Button Placed at point C
 * 	PWM 4 --> Button Placed at point D
 * 
 * Driver Station:
 * 
 * 	Port 0 --> Controller
 * 
 * 
 * @author orianleitersdorf
 *
 */
public class Robot extends IterativeRobot {
	
	private Victor motor;
	
	private JoystickController pilot;
	
	private DebouncedDigitalInput pointA;
	private DebouncedDigitalInput pointB;
	private DebouncedDigitalInput pointC;
	private DebouncedDigitalInput pointD;
	
	private Timer timer;
	
	private double lastTime;
	

	/**
	 * Sets up digital inputs, motors, controllers, and timer
	 */
	@Override
	public void robotInit() {
		
		motor = new Victor (0);
		
		pilot = new JoystickController (0);
		
		pointA = new DebouncedDigitalInput (1); //Initializes the A button at PWM 1
		pointB = new DebouncedDigitalInput (2); //Initializes the B button at PWM 2
		pointC = new DebouncedDigitalInput (3); //Initializes the C button at PWM 3
		pointD = new DebouncedDigitalInput (4); //Initializes the D button at PWM 4
	
		timer = new Timer();
		
		timer.reset();
		timer.start();
		
		lastTime = timer.get();
		
		
	}

	/**
	 * Checks the values of the digital inputs and prints out the results
	 */
	@Override
	public void teleopPeriodic() {
		
		motor.set(pilot.getRightJoyXAxis());
		
		double currentTime = timer.get();
		
		if (pointA.isNewPressed()) {
			System.out.println("A Button Pressed at: " + currentTime + " seconds from the start of the robot, " + (currentTime - lastTime) + " since last press of a button");
			lastTime = currentTime;
		}
		if (pointB.isNewPressed()) {
			System.out.println("B Button Pressed at: " + currentTime + " seconds from the start of the robot, " + (currentTime - lastTime) + " since last press of a button");
			lastTime = currentTime;
		}
		if (pointC.isNewPressed()) {
			System.out.println("C Button Pressed at: " + currentTime + " seconds from the start of the robot, " + (currentTime - lastTime) + " since last press of a button");
			lastTime = currentTime;
		}
		if (pointD.isNewPressed()) {
			System.out.println("D Button Pressed at: " + currentTime + " seconds from the start of the robot, " + (currentTime - lastTime) + " since last press of a button");
			lastTime = currentTime;
		}
		
	}

}

