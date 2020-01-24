package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Control wheel spinner class, using talon with encoder and rev color sensor
 */
public class CW_Spinner {

    public static WPI_TalonSRX talon = new WPI_TalonSRX(4);

    /**
    * Number of counts that equals one revolution of motor.
    * Counts = Encoder position
    */
    public static final int COUNTS_PER_REV = 4096;
    
    public static void init(){
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
        talon.setSelectedSensorPosition(0);
    }

    public static void telemetryUpdate(){
        System.out.println("Rots: " + getRotations());
        System.out.println("Counts: " + talon.getSelectedSensorPosition());
        System.out.println("Vel: " + talon.getSelectedSensorVelocity());
    }

    public static float getRotations(){
        return (float)talon.getSelectedSensorPosition() / (float)COUNTS_PER_REV;
    }
}