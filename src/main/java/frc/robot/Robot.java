/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  public static Joystick stick = new Joystick(0);

  /**
   * Do module initialization and generic robot starting stuff here
   * Comment out both the "new Module()" and the "Module.inst.init()" for all undesired modules
  */
  public void robotInit() {

    //new Drive();
    //Drive.inst.init();
    //new Climber();
    //Climber.inst.init();
    //new CW_Spinner();
    //CW_Spinner.inst.init();
    new BallShooter();
    BallShooter.inst.init();
  }

  
  public void robotPeriodic() {
		
  }

  
  public void autonomousInit() {
  
  }

  public void autonomousPeriodic() {
  
  }

  /**
   * Call the update functions of all modules on the robot (each module is checked for existence before use,
   * so there is no reason to remove anything from here)
   */
  public void teleopPeriodic() {

    if(Drive.inst != null){
      Drive.inst.update();
    }
    if(Climber.inst != null){
      Climber.inst.update();
    }
		if(CW_Spinner.inst != null){
      CW_Spinner.inst.update();
    }
    if(BallShooter.inst != null){
      BallShooter.inst.update();
    }

  }

  public void testPeriodic() {

  }
}
