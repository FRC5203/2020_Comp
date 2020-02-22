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
  AutonomousShoot auto = new AutonomousShoot();

  /**
   * Do module initialization and generic robot starting stuff here.
   * Comment out both the "new Component()" and the "Component.inst.robotInit()" for all undesired modules
  */
  public void robotInit() {
    //new Drive();
    //Drive.inst.robotInit();
    new Climber();
    Climber.inst.robotInit();
    //new CW_Spinner();
    //CW_Spinner.inst.robotInit();
    //new BallShooter();
    //BallShooter.inst.robotInit();
  }

  public void robotPeriodic() {
    
  }
  
  public void autonomousInit() {
    auto.init();
  }

  public void autonomousPeriodic() {
    if(!auto.complete){
      auto.update();
    }
    else{
      Drive.inst.leftDrive.set(0);
      Drive.inst.rightDrive.set(0);
    }
    Drive.inst.sendTelemetry();
  }

  public void teleopInit(){
    if(Drive.inst != null){
      Drive.inst.teleopInit();
      Drive.inst.sendTelemetry();
    }
    if(Climber.inst != null){
      Climber.inst.teleopInit();
    }
		if(CW_Spinner.inst != null){
      CW_Spinner.inst.teleopInit();
    }
    if(BallShooter.inst != null){
      BallShooter.inst.teleopInit();
    }
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

  public void disabledPeriodic(){
    if(Drive.inst != null){
      Drive.inst.sendTelemetry();
    }
  }

  public void disabledInit(){
    auto.complete = false;
    auto.index = 0;
  }
}
