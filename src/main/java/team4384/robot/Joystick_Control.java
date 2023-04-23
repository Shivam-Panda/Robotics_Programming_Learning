package team4384.robot;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Joystick_Control {
    private int speed = 0;
    private final Joystick jstick = new Joystick(1);
    private final CANSparkMax motor;

    private final JoystickButton runner = new JoystickButton(jstick, 1);

    public Joystick_Control(CANSparkMax motor) {
        this.motor = motor;
    }

    public void run() {
        if(runner.getAsBoolean()) {
            set_speed(1);
        } else {
            set_speed(0);
        }
    }

    private void set_speed(int speed) {
        this.motor.set(speed);
    }

    private void set_speed_axis() {
        this.motor.set(jstick.getRawAxis(0));
    }
}
