package frc.robot;

///The constant variables used for PID control for the ball shooter
class Constants {

    public static final int kSlotIdx = 0;

    public static final int kPIDLoopIdx = 0;

    public static final int kTimeoutMs = 30;

    public static final double kP = 0.03;

    public static final double kI = 0.001;

    public static final double kD = 0;

    public static final double kF = 1023.0/7200.0;

    public static final double kIzone = 300;

    public static final double kPeakOutput = 0.5;

}