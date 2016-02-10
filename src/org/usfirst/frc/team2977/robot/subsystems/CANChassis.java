package org.usfirst.frc.team2977.robot.subsystems;

import org.usfirst.frc.team2977.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CANChassis extends Subsystem {
	
	CANTalon m1 = new CANTalon(1);  //front Right
	CANTalon m2 = new CANTalon(2);  //front Left
	CANTalon m3 = new CANTalon(3);  //back Right
	CANTalon m4 = new CANTalon(4);  //back Left
	AnalogGyro gyro = new AnalogGyro(1); 
	Accelerometer accel = new BuiltInAccelerometer();
	double accelX;
	double accelY;
	double accelZ;
	double adjust;  
	double angle; // not degrees	
	double constant = .25; //motor speed
	double factor = .75; 
	
	public CANChassis() {
		m1.enableControl();
		m2.enableControl();
		m3.enableControl();
		m4.enableControl();
		m1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		m2.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		m3.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		m4.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		gyro.reset();
		gyro.calibrate();
	}
	//--------------------Accelerometer--------------------------------------//
	public double getX() {
		accelX = accel.getX();
		SmartDashboard.putNumber("X", accelX);
		return accelX;
	}
	
	public double getY() {
		accelY = accel.getY();
		SmartDashboard.putNumber("Y", accelY);
		return accelY; 
	}

	public double getZ() {
		accelZ = accel.getZ();
		SmartDashboard.putNumber("Z", accelZ);
		return accelZ;
	}

	
	
	//------------------- Gyro Driving -------------------------------------//
	
	public void Reset() { 
			gyro.reset();
			SmartDashboard.putBoolean("Resetted", true);
	  }
	    
    public void GyroDrive(double turnAngle) {   //Drives straight using feedback from a gyro
		getX();
		getY();
		getZ();
    	angle = gyro.getAngle();
    	adjust = Math.abs(constant * (factor * angle));
    
    	SmartDashboard.putNumber("Angle", angle);
    	SmartDashboard.putNumber("Adjust", adjust);
    	
    	if (angle < turnAngle -.1){  // Robot tilting right
    		AdjustRightSide();
    	}
    		
    	else if (angle > turnAngle + .1){  // Robot tilting left
    		AdjustLeftSide();
    	}
    		
    	else {
    		SetEqual();   //Within acceptable threshold
    	}
    	
    }
    	
    	void AdjustRightSide() {
    		m3.set(-(constant + adjust));  // right side
       		m1.set(-(constant + adjust));
    	}
    
       	void AdjustLeftSide() {
   			m4.set(constant + adjust);	 // left side
   			m2.set(constant + adjust);
    		//Robot.memeBase.meme1.set(dank);
    	}
       	void SetEqual() {
       		m1.set(-constant);
       		m2.set(constant);
       		m3.set(-constant);
       		m4.set(constant);

       	}
       	
       	public double GyroAngle() {
       		return gyro.getAngle();
       	}
    // ---------------------------------------------------------------------//
       	//--Standard Drive--//
       	
        public void Drive (double speedL, double speedR) {//Drive with manual value input
          	m1.set(-speedR);
           	m3.set(-speedR);
        	m2.set(speedL);
        	m4.set(speedL);
    		SmartDashboard.putNumber("Angle", GyroAngle());
    		getZ();
    		getX();
    		getY();
        }


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveCommand());
    }
}

