package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BallShooter {

    public static BallShooter inst;

    TalonSRX RBallShooter = new TalonSRX(1);
    TalonSRX LBallShooter = new TalonSRX(4);

    int loops = 0;

    public BallShooter(){
        inst = this;
    }

    public void robotInit(){
        
        SmartDashboard.putNumber("Shooter RPM", 3000);
        
        //Resets the talons to avoid any issues with prior configurations
        RBallShooter.configFactoryDefault();
        LBallShooter.configFactoryDefault();

        //Configure the feedback sensor for each talon to be a mag encoder
        RBallShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        LBallShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

        //Set each encoder to not be inverted
        RBallShooter.setSensorPhase(false);
        LBallShooter.setSensorPhase(false);

        //Configure the min (nominal) and max (peak) percent outputs
        RBallShooter.configNominalOutputForward(0, Constants.kTimeoutMs);
        RBallShooter.configNominalOutputReverse(0, Constants.kTimeoutMs);
        RBallShooter.configPeakOutputForward(1, Constants.kTimeoutMs);
        RBallShooter.configPeakOutputReverse(-1, Constants.kTimeoutMs);

        LBallShooter.configNominalOutputForward(0, Constants.kTimeoutMs);
        LBallShooter.configNominalOutputReverse(0, Constants.kTimeoutMs);
        LBallShooter.configPeakOutputForward(1, Constants.kTimeoutMs);
        LBallShooter.configPeakOutputReverse(-1, Constants.kTimeoutMs);
        
        //PID Controller variables
        RBallShooter.config_kF(Constants.kPIDLoopIdx, Constants.kF, Constants.kTimeoutMs);
        RBallShooter.config_kP(Constants.kPIDLoopIdx, Constants.kP, Constants.kTimeoutMs);
        RBallShooter.config_kI(Constants.kPIDLoopIdx, Constants.kI, Constants.kTimeoutMs);
        RBallShooter.config_kD(Constants.kPIDLoopIdx, Constants.kD, Constants.kTimeoutMs);

        LBallShooter.config_kF(Constants.kPIDLoopIdx, Constants.kF, Constants.kTimeoutMs);
        LBallShooter.config_kP(Constants.kPIDLoopIdx, Constants.kP, Constants.kTimeoutMs);
        LBallShooter.config_kI(Constants.kPIDLoopIdx, Constants.kI, Constants.kTimeoutMs);
        LBallShooter.config_kD(Constants.kPIDLoopIdx, Constants.kD, Constants.kTimeoutMs);
    
    }

    public void teleopInit(){

    }

    public void update(){
        //If the A button is pressed, run the motors with a target velocity (using PID) else cut power
        if (Robot.stick.getRawButton(1)) {
            double targetVelocity_UnitsPer100ms = SmartDashboard.getNumber("Shooter RPM", 0) * 4096 / 600;

            RBallShooter.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
            LBallShooter.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);

            SmartDashboard.putNumber("Shooter Target Vel", targetVelocity_UnitsPer100ms);
        } 
        else {
            RBallShooter.set(ControlMode.PercentOutput, 0);
            LBallShooter.set(ControlMode.PercentOutput, 0);
            //RBallShooter.set(ControlMode.PercentOutput, leftYstick);
            //LBallShooter.set(ControlMode.PercentOutput, leftYstick);
        }
    }

    //Currently serves same purpose as update function but will be adapted to autonomous
    public void runShooter(){
        if (Robot.stick.getRawButton(1)) {
            double targetVelocity_UnitsPer100ms = SmartDashboard.getNumber("Shooter RPM", 0) * 4096 / 600;

            RBallShooter.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
            LBallShooter.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);

            SmartDashboard.putNumber("Shooter Target Vel", targetVelocity_UnitsPer100ms);
        } 
        else {
            RBallShooter.set(ControlMode.PercentOutput, 0);
            LBallShooter.set(ControlMode.PercentOutput, 0);
            //RBallShooter.set(ControlMode.PercentOutput, leftYstick);
            //LBallShooter.set(ControlMode.PercentOutput, leftYstick);
        }
    }
}