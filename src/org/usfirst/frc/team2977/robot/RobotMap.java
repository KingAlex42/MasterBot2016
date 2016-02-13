package org.usfirst.frc.team2977.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static int m1 = 1;
	public static int m2 = 2;
	public static int m3 = 3;
	public static int m4 = 4;
	public static int intaker = 5;
	public static int intakeLimit = 2;
	public static int gyroCoefficient = 42;
	public static int winchLimit = 1;
	public static int winch = 6;
	public static int motor = 5;
	public static int limit1 = 0;
	

    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
