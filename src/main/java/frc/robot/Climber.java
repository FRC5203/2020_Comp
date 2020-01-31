package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * A class for the climbing arm and winch, with a talon for each
 */
public class Climber {

	public WPI_TalonSRX talon_arm = new WPI_TalonSRX(1);
	public WPI_TalonSRX talon_winch = new WPI_TalonSRX(2);

	/**
   * Singleton variable: the instance of the class, stored statically for easy access 
   */
	public static Climber inst;

	/**
   * Call only once to initialize the singleton (inst) variable
   */
	public Climber(){
		inst = this;
	}
	
	/**
	 * For any setup related to the climber
	 */
  public void init(){
		
	}
	
	/**
	 * Called in robotPeriodic, contains all input handling from controller and telemetry
	 */
	public void update(){
		if(Robot.stick.getRawButton(4)){
			talon_arm.set(0.1);
		}
		else if(Robot.stick.getRawButton(2)){
			talon_arm.set(0.1);
		}
		else{
			talon_arm.set(0);
		}

		if(Robot.stick.getRawButton(1)){
			//do winch climb
		}
	}

}