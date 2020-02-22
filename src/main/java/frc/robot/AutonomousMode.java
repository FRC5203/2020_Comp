package frc.robot;

public class AutonomousMode {
    
    AutoTarget[] targets;

    public boolean complete = false;
    public int index = 0;
    public double startAngle;
    
    public void init(){
        Drive.inst.gyro.reset();
        Drive.inst.encoderLeft.reset();
        Drive.inst.encoderRight.reset();
        Drive.inst.rightDrive.setInverted(true);
        Drive.inst.encoderLeft.setReverseDirection(true);
    }

    public void next(){
        if(index == targets.length - 1){
            complete = true;
            Drive.inst.diffDrive.tankDrive(0,0);
            Drive.inst.encoderLeft.reset();
            Drive.inst.encoderRight.reset();

        }
        else{
            index++;
            startAngle = Drive.inst.getGyroRotation();
            Drive.inst.diffDrive.tankDrive(0,0);
            Drive.inst.encoderLeft.reset();
            Drive.inst.encoderRight.reset();
            
        }
    }

    public void update(AutoTarget target){
        if(target != null){
            int leftError = Drive.inst.encoderLeft.get() - target.countsLeft;
            int rightError = 0;
            //If there is an angle, do turning
            if(target.angle != 0){
                if(Math.abs(Drive.inst.getGyroRotation()) - Math.abs(startAngle) >= Math.abs(target.angle)){
                    next();
                }
                else{
                    Drive.inst.diffDrive.tankDrive(target.speedLeft, target.speedRight);
                }
            }
            //Else do driving
            else{
                if(target.countsRight < 0){
                    rightError = -(Drive.inst.encoderRight.get() - target.countsRight);
                }
                else{
                    rightError = Drive.inst.encoderRight.get() - target.countsRight;
                }
                
                if(leftError >= 0 && rightError >= 0){
                    next();
                }
                else{
                    Drive.inst.diffDrive.tankDrive(target.speedLeft, target.speedRight);
                }
            }
        }
    }
}

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