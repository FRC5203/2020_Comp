package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drivetrain class, using differential drive for control, and encoders (left and right) for autonomous driving
 */
public class Drive {

	WPI_TalonSRX talon = new WPI_TalonSRX(1);
  WPI_TalonSRX talon2 = new WPI_TalonSRX(2);
  WPI_TalonSRX talon3 = new WPI_TalonSRX(3);
  WPI_TalonSRX talon4 = new WPI_TalonSRX(4);

  SpeedControllerGroup leftDrive = new SpeedControllerGroup(talon, talon2);
  SpeedControllerGroup rightDrive = new SpeedControllerGroup(talon3, talon4);
	DifferentialDrive diffDrive = new DifferentialDrive(leftDrive, rightDrive);
	
	public static final int COUNTS_PER_REV = 2048;
	Encoder leftEncoder = new Encoder(1,2);
  //static Encoder rightEncoder = new Encoder(5,6);

  /**
   * Singleton variable: the instance of the class, stored statically for easy access 
   */
  public static Drive inst;
  
  /**
   * Call only once to initialize the singleton (inst) variable
   */
  public Drive(){
    inst = this;
  }

  /**
   * For any setup related to drivetrain
   */
  public void init(){
    
  }

  /**
   * Called in robotPeriodic, contains all input handling from controller and telemetry
   */
  public void update(){
    stickDrive();
    SmartDashboard.putNumber("Rotations", getRotations());
  }

  /**
   * Drives with the controller (stick)
   */
  public void stickDrive(){
    diffDrive.arcadeDrive(Robot.stick.getY(), Robot.stick.getX());
	}

  /**
   * Returns number of times the wheels of the drivetrain rotated (not distance travelled)
   */
	public float getRotations(){
			return (float)leftEncoder.get()/(float)COUNTS_PER_REV;
	}
	
}