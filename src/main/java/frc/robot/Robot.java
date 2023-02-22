// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  private XboxController xbox = new XboxController(0);

  private Drive drive = new Drive();
  
  private PneumaticHub bensNice = new PneumaticHub(2);
  private Pneumatics claw = new Pneumatics();

  @Override
  public void robotInit() {
    bensNice.enableCompressorAnalog(0, 120);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {
    if (drive.leftMotor.getSelectedSensorPosition() < 420.69 && drive.rightMotor.getSelectedSensorPosition() < 420.69) {
      drive.move(1, 1);
    } else {
      drive.move(0, 0);
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    drive.move(-xbox.getRawAxis(1) + xbox.getRawAxis(0), -xbox.getRawAxis(1) + -xbox.getRawAxis(0));
    
    if (xbox.getRawButton(1)) {
      claw.toggle();
    }

    SmartDashboard.putNumber("leftEncoderPosition", drive.leftEncoder.getAbsolutePosition());
    SmartDashboard.putNumber("rightEncoderPosition", drive.rightEncoder.getAbsolutePosition());
  }
  @Override
  public void disabledInit() {
    bensNice.disableCompressor();
    drive.leftMotor.setNeutralMode(NeutralMode.Coast);
    drive.rightMotor.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}