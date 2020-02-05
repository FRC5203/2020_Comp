package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BallShooter {

    public static BallShooter inst;

    TalonSRX RBallShooter = new TalonSRX(1);
    TalonSRX LBallShooter = new TalonSRX(4);

    StringBuilder sb = new StringBuilder();

    int loops = 0;

    public BallShooter(){
        inst = this;
    }

    public void init(){
        RBallShooter.configFactoryDefault();
        LBallShooter.configFactoryDefault();

        RBallShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        LBallShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

        RBallShooter.setSensorPhase(false);
        LBallShooter.setSensorPhase(false);

        RBallShooter.configNominalOutputForward(0, Constants.kTimeoutMs);
        RBallShooter.configNominalOutputReverse(0, Constants.kTimeoutMs);
        RBallShooter.configPeakOutputForward(1, Constants.kTimeoutMs);
        RBallShooter.configPeakOutputReverse(-1, Constants.kTimeoutMs);

        LBallShooter.configNominalOutputForward(0, Constants.kTimeoutMs);
        LBallShooter.configNominalOutputReverse(0, Constants.kTimeoutMs);
        LBallShooter.configPeakOutputForward(1, Constants.kTimeoutMs);
        LBallShooter.configPeakOutputReverse(-1, Constants.kTimeoutMs);
        
        RBallShooter.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
        RBallShooter.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
        RBallShooter.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
        RBallShooter.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);

        LBallShooter.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
        LBallShooter.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
        LBallShooter.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
        LBallShooter.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);
    
    }

    public void update(){

        double motorOutputR = RBallShooter.getMotorOutputPercent();
        double motorOutputL = LBallShooter.getMotorOutputPercent();

        sb.append("\tout: R ");
        sb.append((int) (motorOutputR * 100));
        sb.append(" L ");
        sb.append((int) (motorOutputL * 100));

        sb.append("\tspd: R ");
        sb.append(RBallShooter.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
        sb.append("u L ");
        sb.append(LBallShooter.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
        sb.append("u");

        if (Robot.stick.getRawButton(1)) {
            double targetVelocity_UnitsPer100ms = 500.0 * 4096 / 600;

            RBallShooter.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
            LBallShooter.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);

            sb.append("\terr: R ");
            sb.append(RBallShooter.getClosedLoopError(Constants.kPIDLoopIdx));
            sb.append(" L ");
            sb.append(LBallShooter.getClosedLoopError(Constants.kPIDLoopIdx));
            sb.append("\ttrg");
            sb.append(targetVelocity_UnitsPer100ms);
        } 
        else {
            RBallShooter.set(ControlMode.PercentOutput, 0);
            LBallShooter.set(ControlMode.PercentOutput, 0);
            //RBallShooter.set(ControlMode.PercentOutput, leftYstick);
            //LBallShooter.set(ControlMode.PercentOutput, leftYstick);
        }

        if (++loops >= 10){
            loops = 0;
            SmartDashboard.putString("Shooter PID Out: ", sb.toString());
        }

        sb.setLength(0);

    }
}