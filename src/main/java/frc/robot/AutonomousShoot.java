package frc.robot;

public class AutonomousShoot extends AutonomousMode {

    AutoTarget[] targets = {
        new AutoTarget(4074, 4074, 0.5, 0.5),
        new AutoTarget(90, 0.5, -0.5),
        new AutoTarget(4074, 4074, 0.5, 0.5),
        new AutoTarget(-90, 0.5, -0.5)
    };

    public int shootIndex = 3;

    public void update(){
        //Super class will handle driving/turning
        if(targets[index] != null){
            super.update(targets[index]);
        }
        else if(index == shootIndex && !shoot()){
            
        }
        else if(index == shootIndex && shoot()){
            if(index != targets.length - 1){
                index++;
            }
            else{
                complete = true;
            }
        }
    }

    ///Will return false when done shooting
    public boolean shoot(){
        if(BallShooter.inst != null){
            BallShooter.inst.runShooter();
        }
        return false;
    }

}