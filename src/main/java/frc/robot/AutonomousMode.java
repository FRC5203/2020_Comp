package frc.robot;

public class AutonomousMode {
    
    AutoTarget[] targets;

    public boolean complete = false;
    public int index = 0;
    public double startAngle;

    //Resets all sensors, inverts right side of the drive train and inverts left side encoder
    public void init(){
        Drive.inst.gyro.reset();
        Drive.inst.encoderLeft.reset();
        Drive.inst.encoderRight.reset();

        Drive.inst.rightDrive.setInverted(true);
        Drive.inst.encoderLeft.setReverseDirection(true);
    }
    //function to move to the next target
    public void next(){
        //If already on final target index, auto is complete
        if(index == targets.length - 1){
            complete = true;
            Drive.inst.diffDrive.tankDrive(0,0);
            Drive.inst.encoderLeft.reset();
            Drive.inst.encoderRight.reset();

        }
        //Else move to next target index and reset vars, set starting angle in case of turning
        else{
            index++;
            startAngle = Drive.inst.getGyroRotation();
            Drive.inst.diffDrive.tankDrive(0,0);
            Drive.inst.encoderLeft.reset();
            Drive.inst.encoderRight.reset();
        }
    }
    //Continually runs to complete each auto target, does the driving and turning logic
    public void update(AutoTarget target){
        if(target != null){
            //If there is an angle, do turning
            if(target.angle != 0){
                if(Math.abs(Drive.inst.getGyroRotation() - startAngle) >= Math.abs(target.angle)){
                    next();
                }
                else{
                    Drive.inst.diffDrive.tankDrive(target.speedLeft, target.speedRight);
                }
            }
            //Else do driving
            else{
                //Generate left and right error, (error should be negative until target has been reached)
                int leftError = Drive.inst.encoderLeft.get() - target.countsLeft;
                int rightError = 0;
                //In cases where right side is moving in reverse, error should be inverted
                if(target.countsRight <= 0){
                    rightError = -(Drive.inst.encoderRight.get() - target.countsRight);
                }
                else{
                    rightError = Drive.inst.encoderRight.get() - target.countsRight;
                }
                //If error is non negative on each side, go to next target
                if(leftError >= 0 && rightError >= 0){
                    next();
                }
                //If 80% of drive is done, lower speed to prevent overshooting target distance
                else if((double)(Drive.inst.encoderLeft.get() / target.countsLeft) >= 0.8){
                    Drive.inst.diffDrive.tankDrive(0.25, 0.25);
                }
                else{
                    Drive.inst.diffDrive.tankDrive(target.speedLeft, target.speedRight);
                }
            }
        }
    }
}

//Holds are variable neccessary for a given robot maneuver (includes driving and turning)
class AutoTarget {

    public AutoTarget(int countsLeft, int countsRight, double speedLeft, double speedRight){
        this.countsLeft = countsLeft;
        this.countsRight = countsRight;
        this.speedLeft = speedLeft;
        this.speedRight = speedRight;
    }

    public AutoTarget(double angle, double speedLeft, double speedRight){
        this.angle = angle;
        this.speedLeft = speedLeft;
        this.speedRight = speedRight;
    }
    
    public double angle;
    public int countsLeft;
    public int countsRight;
    public double speedLeft;
    public double speedRight;

}