package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Control wheel spinner class, using talon with encoder and rev color sensor
 */
public class CW_Spinner {

  public WPI_TalonSRX talon = new WPI_TalonSRX(4);
  public ColorSensorV3 color_sensor = new ColorSensorV3(I2C.Port.kOnboard);
  public ColorMatch color_match = new ColorMatch();

  public final Color color_yellow = ColorMatch.makeColor(0.3174,0.5515,0.1311);
  public final Color color_red = ColorMatch.makeColor(0.4978,0.3542,0.1479);
  public final Color color_green = ColorMatch.makeColor(0.1794,0.5573,0.2632);
  public final Color color_blue = ColorMatch.makeColor(0.1294,0.4219,0.4487);

  /**
  * Number of counts that equals one revolution of motor.
  * Counts = Encoder position
  */
  public static final int COUNTS_PER_REV = 4096;
  
  ///Singleton variable: the instance of the class, stored statically for easy access 
  public static CW_Spinner inst;

  ///Call only once to initialize the singleton (inst) variable
  public CW_Spinner(){
    inst = this;
  }
    
  ///For any setup related to the control wheel spinner
  public void robotInit(){
    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
    talon.setSelectedSensorPosition(0);

    //Yellow
    color_match.addColorMatch(color_yellow);
    //Red
    color_match.addColorMatch(color_red);
    //Green
    color_match.addColorMatch(color_green);
    //Blue
    color_match.addColorMatch(color_blue);
  }
  
  public void teleopInit(){
    
  }

  ///Called in robotPeriodic, contains all input handling from controller and telemetry
  public void update(){
		System.out.println("Rots: " + getRotations());
    System.out.println("Counts: " + talon.getSelectedSensorPosition());
    System.out.println("Vel: " + talon.getSelectedSensorVelocity());
    
    //SmartDashboard.putString("Color Vals: ", "R " + color_sensor.getColor().red + " G " + color_sensor.getColor().green + " B " + color_sensor.getColor().blue);
    SmartDashboard.putBoolean("Matches Yellow: ", checkColor(color_yellow));
    SmartDashboard.putBoolean("Matches Red: ", checkColor(color_red));
    SmartDashboard.putBoolean("Matches Green: ", checkColor(color_green));
    SmartDashboard.putBoolean("Matches Blue: ", checkColor(color_blue));
  }

  /**
   * Given a color, return if the match was successful
   * @param color
   * @return true or false for match
   */
  public boolean checkColor(Color color){
    ColorMatchResult result = color_match.matchClosestColor(color_sensor.getColor());
    if(result != null && result.color == color){
      return true;
    }
    else{
      return false;
    }
  }

  ///Returns the configured color in the color matcher that is closest to that of the color sensor
  public Color getColor(){
    return color_match.matchClosestColor(color_sensor.getColor()).color;
  }

  ///Returns number of times the control wheel spinner rotated (not distance travelled)
  public float getRotations(){
    return (float)talon.getSelectedSensorPosition() / (float)COUNTS_PER_REV;
  }
}