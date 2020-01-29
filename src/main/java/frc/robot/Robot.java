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

  //Do module initiation and generic robot starting stuff here
  public void robotInit() {
		Drive.init();
		Climber.init();
    CW_Spinner.init();
  }

  
  public void robotPeriodic() {

  }

  
  public void autonomousInit() {
    CW_Spinner.talon.setSelectedSensorPosition(0);
    hitTarget = false;
  }

  boolean hitTarget = false;
  
  public void autonomousPeriodic() {
    if(hitTarget || CW_Spinner.getRotations() >= 1 || CW_Spinner.getRotations() <= -1){
      hitTarget = true;
      CW_Spinner.talon.set(0);
      CW_Spinner.talon.setSelectedSensorPosition(0);
      return;
    }
    CW_Spinner.talon.set(0.1);
    CW_Spinner.telemetryUpdate();
  }

  @Override
  public void teleopPeriodic() {
		Drive.update();
		Climber.update();
		CW_Spinner.update();
  }

  @Override
  public void testPeriodic() {
  }
}
