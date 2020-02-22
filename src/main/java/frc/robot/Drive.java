package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.*;

///Drivetrain class, using differential drive for control, and encoders (left and right) for autonomous driving
public class Drive {

	WPI_TalonSRX talon = new WPI_TalonSRX(1);
  WPI_TalonSRX talon2 = new WPI_TalonSRX(2);
  WPI_TalonSRX talon3 = new WPI_TalonSRX(3);
  WPI_TalonSRX talon4 = new WPI_TalonSRX(4);

  SpeedControllerGroup leftDrive = new SpeedControllerGroup(talon, talon2);
  SpeedControllerGroup rightDrive = new SpeedControllerGroup(talon3, talon4);
	DifferentialDrive diffDrive = new DifferentialDrive(leftDrive, rightDrive);
	
  public static final int COUNTS_PER_REV = 2048;
  //Width between the wheels in inches
  public static final int WHEEL_BASE = 22;

  Encoder encoderLeft = new Encoder(5,6);
  Encoder encoderRight = new Encoder(1,2);

  AHRS gyro = new AHRS(Port.kMXP);

  ///Singleton variable: the instance of the class, stored statically for easy access 
  public static Drive inst;
  
  ///Call only once to initialize the singleton (inst) variable
  public Drive(){
    inst = this;
  }

  public void robotInit(){
    SmartDashboard.putNumber("Controller X Exponent: ", 1);
    SmartDashboard.putNumber("Controller Y Exponent: ", 1);
    gyro.calibrate();
  }

  ///For any setup related to drivetrain
  public void teleopInit(){
    encoderLeft.setReverseDirection(true);
    rightDrive.setInverted(false);
  }

  ///Called in robotPeriodic, contains all input handling from controller and telemetry
  public void update(){
    stickDrive((int)SmartDashboard.getNumber("Controller X Exponent: ", 1), (int)SmartDashboard.getNumber("Controller Y Exponent: ", 1));
    if(Robot.stick.getRawButton(1)){
      encoderLeft.reset();
      encoderRight.reset();
    }
    sendTelemetry();
  }

  ///Drives with the controller (stick)
  public void stickDrive(int exponentX, int exponentY){
    diffDrive.arcadeDrive(-Math.pow(Robot.stick.getY(), exponentY), Math.pow(Robot.stick.getX(), exponentX));
	}

  ///Returns number of times the wheels of the drivetrain rotated (not distance travelled)
	public float getRotations(){
		return (float)encoderLeft.get()/(float)COUNTS_PER_REV;
  }

  //Prints useful drive train sensor data
  public void sendTelemetry(){
    SmartDashboard.putNumber("Left Encoder Counts: ", encoderLeft.get());
    SmartDashboard.putNumber("Right Encoder Counts", encoderRight.get());
    SmartDashboard.putNumber("Encoder Rotations: ", getRotations());
    SmartDashboard.putNumber("Gyro Rotation: ", getGyroRotation());
  }

  //Gets the total change in rotation (in degrees) of the robot i.e. value will not reset when passing zero degree mark
  public double getGyroRotation(){
    return gyro.getAngle();
  }
	
}