package team4384.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Joystick_Control {
    private int speed = 0;
    private final Joystick jstick = new Joystick(1);
    private final TalonFX motor_1, motor_2;

    private final JoystickButton runner = new JoystickButton(jstick, 1);

    public Joystick_Control(TalonFX motor_1, TalonFX motor_2) {
        this.motor_1 = motor_1;
        this.motor_2 = motor_2;
        this.motor_1.setInverted(true);
        this.motor_2.setInverted(true);
    }

    public void run() {
        if(runner.getAsBoolean()) {
            set_speed(20000);
        } else {
            set_speed(0);
        }
    }

    private void set_speed(int speed) {
        this.motor_1.set(TalonFXControlMode.Velocity, speed);
        this.motor_2.set(TalonFXControlMode.Velocity, speed);
    }
}
