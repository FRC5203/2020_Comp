package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Control wheel spinner class, using talon with encoder and rev color sensor
 */
public class CW_Spinner {

  public WPI_TalonSRX talon = new WPI_TalonSRX(4);
  public ColorSensorV3 color_sensor = new ColorSensorV3(Port.kMXP);
  public ColorMatch color_match = new ColorMatch();

  /**
  * Number of counts that equals one revolution of motor.
  * Counts = Encoder position
  */
  public static final int COUNTS_PER_REV = 4096;
  
  /**
   * Singleton variable: the instance of the class, stored statically for easy access 
   */
  public static CW_Spinner inst;

  /**
   * Call only once to initialize the singleton (inst) variable
   */
  public CW_Spinner(){
    inst = this;
  }
    
  /**
   * For any setup related to the control wheel spinner
   */
  public void init(){
    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
    talon.setSelectedSensorPosition(0);

    color_match.addColorMatch(Color.kYellow);
    color_match.addColorMatch(Color.kRed);
    color_match.addColorMatch(Color.kGreen);
    color_match.addColorMatch(Color.kBlue);
  }
  
  /**
   * Called in robotPeriodic, contains all input handling from controller and telemetry
   */
  public void update(){
		System.out.println("Rots: " + getRotations());
    System.out.println("Counts: " + talon.getSelectedSensorPosition());
    System.out.println("Vel: " + talon.getSelectedSensorVelocity());
  }

  /**
   * Given a color, return if the match was successful
   * @param color
   * @return
   */
  public boolean checkColor(Color color){
    ColorMatchResult result = color_match.matchColor(color);
    if(result.color == color){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * Returns number of times the control wheel spinner rotated (not distance travelled)
   */
  public float getRotations(){
    return (float)talon.getSelectedSensorPosition() / (float)COUNTS_PER_REV;
  }
}