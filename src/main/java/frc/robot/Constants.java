package frc.robot;

class Gains {
     
    public final double kP;
    public final double kI;
    public final double kD;
    public final double kF;
    public final double kIzone;
    public final double kPeakOutput;

    public Gains(double _kP, double _kI, double _kD, double _kF, int _kIzone, double _kPeakOutput){
        kP = _kP;
        kI = _kI;
        kD = _kD;
        kF = _kF;
        kIzone = _kIzone;
        kPeakOutput = _kPeakOutput;
    }

}

class Constants {

    public static final int kSlotIdx = 0;

    public static final int kPIDLoopIdx = 0;

    public static final int kTimeoutMs = 30;

    public final static Gains kGains_Velocit = new Gains(0.05, 0.001, 0, 1023.0/7200.0,  300, 1.00);
    
}