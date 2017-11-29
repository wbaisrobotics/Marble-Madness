Practice Competition robot, can drive the motor using values from the controller and detect changes in the digital inputs (wired to the buttons)

Victor (motor) is controlled by the Right X Axis on the controller

The system will print out to console any time that a button is newly pressed (with the name of the button), along with the time since the last press of any button.

For every button:
  Connect one side of the button to the voltage port on the roborio, the other to it's cooresponding PWM signal port
  Make sure that the buttons are connected so that current flows when the buttons are pressed.

Connections:

  Roborio:
    PWM 0 --> Victor
    PWM 1 --> Servo
    PWM 2 --> Button Placed at point A
    PWM 3 --> Button Placed at point B
    PWM 4 --> Button Placed at point C
    PWM 5 --> Button Placed at point D

  Driver Station:
    Port 0 --> Controller


@author orianleitersdorf
