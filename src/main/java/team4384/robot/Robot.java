// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package team4384.robot;

import com.ctre.phoenix.Logger;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
// Tank aBhipriy's Group
//    private final int RIGHT_2 = 34;
//    private final int RIGHT_1 = 31;
//    private final int LEFT_2 = 33;
//    private final int LEFT_1 = 32;
//
//    private final TalonSRX right_1 = new TalonSRX(RIGHT_1);
//    private final TalonSRX right_2 = new TalonSRX(RIGHT_2);
//    private final TalonSRX left_1 = new TalonSRX(LEFT_1);
//    private final TalonSRX left_2 = new TalonSRX(LEFT_2);

    // Tank Adi's Group


    private final int LEFT_1 = 42;
    private final int LEFT_2 = 36;
    private final int RIGHT_1 = 34;
    private final int RIGHT_2 = 37;

    private final WPI_TalonSRX left_1 = new WPI_TalonSRX(LEFT_1);
    private final WPI_TalonSRX left_2 = new WPI_TalonSRX(LEFT_2);
    private final WPI_TalonSRX right_1 = new WPI_TalonSRX(RIGHT_1);
    private final WPI_TalonSRX right_2 = new WPI_TalonSRX(RIGHT_2);

    private final Joystick drive = new Joystick(0);
    //private final JoystickButton in = new JoystickButton(drive, 4);
    // private final JoystickButton out = new JoystickButton(drive, 5);
    private final JoystickButton in = new JoystickButton(drive, 1);
    private final JoystickButton out = new JoystickButton(drive, 2);
    private final JoystickButton comp_on = new JoystickButton(drive, 3);



    private final TalonFX arm_mover = new TalonFX(45);
    private final TalonFX intake_mover = new TalonFX(47);

    //pneumatic
    private final Compressor comp = new Compressor(PneumaticsModuleType.CTREPCM);
    private final Solenoid solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 1);

    public boolean forward = false;

//    private DifferentialDrive robo;

    private boolean pos = true;



    @Override
    public void robotInit() {
        SmartDashboard.putBoolean("compressor", true);
//        robo = new DifferentialDrive(left_1, right_1);
    }

    @Override
    public void teleopPeriodic() {
        if(in.getAsBoolean()) {
            solenoid.set(false);
            SmartDashboard.putBoolean("solenoid button", false);
        } else if(out.getAsBoolean()) {
            solenoid.set(true);
            SmartDashboard.putBoolean("solenoid button", true);
        }

        if(comp_on.getAsBoolean()) {
            comp.disable();
            SmartDashboard.putBoolean("compressor", false);
        } else {
            comp.enableDigital();
            SmartDashboard.putBoolean("compressor", true);
        }


        SmartDashboard.putBoolean("Compressor", comp.getPressureSwitchValue());
        SmartDashboard.putBoolean("Solenoid", solenoid.get());
//        if(in.getAsBoolean()) {
//            arm_mover.setInverted(true);
//            arm_mover.set(TalonFXControlMode.PercentOutput, 1);
//        } else {
//            arm_mover.set(TalonFXControlMode.PercentOutput, 0);
//        }
//         if(out.getAsBoolean()) {
//             arm_mover.setInverted(false);
//             arm_mover.set(TalonFXControlMode.PercentOutput, 1);
//         } else {
//             arm_mover.set(TalonFXControlMode.PercentOutput, 0);
//         }
//        intake_mover.set(TalonFXControlMode.PercentOutput, 10);
//        arm_controller.set(TalonFXControlMode.PercentOutput, 30);
//        robo.arcadeDrive(-1*drive.getRawAxis(1), drive.getRawAxis(0));
//        if(jstick.getRawAxis(DRIVE_AXIS) > 0.01 || jstick.getRawAxis(DRIVE_AXIS) < -0.01) {
//            left_1.set(TalonSRXControlMode.PercentOutput, jstick.getRawAxis(DRIVE_AXIS) * 10);
//            right_1.set(TalonSRXControlMode.PercentOutput, jstick.getRawAxis(DRIVE_AXIS) * 10);
//        }
//       SmartDashboard.putNumber("Throttle", jstick.getRawAxis(DRIVE_AXIS));
//        if(jstick.getRawAxis(LEFT_TURN)>0.01 || jstick.getRawAxis(LEFT_TURN) < 0.01) {
//            left_1.set(TalonSRXControlMode.PercentOutput, jstick.getRawAxis(LEFT_TURN) * 10);
//        }
//        else if(jstick.getRawAxis(RIGHT_TURN)>0.01 || jstick.getRawAxis(RIGHT_TURN)<0.01) {
//            left_1.set(TalonSRXControlMode.PercentOutput, jstick.getRawAxis(RIGHT_TURN) * 10);
//        }
    }

    public static void main(String... args) {
        RobotBase.startRobot(Robot::new);
    }
}
