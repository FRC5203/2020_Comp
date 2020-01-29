package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive {

	static WPI_TalonSRX talon = new WPI_TalonSRX(1);
  static WPI_TalonSRX talon2 = new WPI_TalonSRX(2);
  static WPI_TalonSRX talon3 = new WPI_TalonSRX(3);
  static WPI_TalonSRX talon4 = new WPI_TalonSRX(4);

  static SpeedControllerGroup leftDrive = new SpeedControllerGroup(talon, talon2);
  static SpeedControllerGroup rightDrive = new SpeedControllerGroup(talon3, talon4);
  static DifferentialDrive diffDrive = new DifferentialDrive(leftDrive, rightDrive);

  public static void init(){
        
  }

  public static void update(){
		stickDrive();
  }

  public static void stickDrive(){
    diffDrive.arcadeDrive(Robot.stick.getY(), Robot.stick.getX());
	}
	
}