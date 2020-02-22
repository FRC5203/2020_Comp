package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A class for the climbing arm and winch, with a
 */
public class Climber {

	public WPI_TalonSRX talonArm = new WPI_TalonSRX(1);

	public WPI_TalonSRX talonWinch = new WPI_TalonSRX(2);
	public WPI_TalonSRX talonWinch2 = new WPI_TalonSRX(3);
	///Combines both winch talons into a group for controlling them simultaneously
	public SpeedControllerGroup winchGroup = new SpeedControllerGroup(talonWinch, talonWinch2);

   	///Singleton variable: the instance of the class, stored statically for easy access 
	public static Climber inst;

	///Call only once to initialize the singleton (inst) variable
	public Climber(){
		inst = this;
	}
	
	///For any setup related to the climber
	public void robotInit(){
		SmartDashboard.putNumber("Climber Arm Speed: ", 0.5);
		SmartDashboard.putNumber("Climber Winch Speed: ", 0.25);
	}

	public void teleopInit(){

	}
	
	///Called in robotPeriodic, contains all input handling from controller and telemetry
	public void update(){
		//Arm up
		if(Robot.stick.getRawButton(4)){
			talonArm.set(SmartDashboard.getNumber("Climber Arm Speed: ", 0.5));
			winchGroup.set(-SmartDashboard.getNumber("Climber Winch Speed: ", 0.25));
		}
		//Arm down
		else if(Robot.stick.getRawButton(2)){
			talonArm.set(-SmartDashboard.getNumber("Climber Arm Speed: ", 0.5));
		}
		else{
			talonArm.set(0);
		}

		if(Robot.stick.getRawButton(1)){
			//do winch climb
			winchGroup.set(SmartDashboard.getNumber("Climber Winch Speed: ", 0.25));
		}
	}
}