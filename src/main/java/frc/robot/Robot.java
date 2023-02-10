// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
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
  private TalonFX talonFXLeft = new TalonFX(1);
  private TalonFX talonFXRight = new TalonFX(2);
  private Timer timer = new Timer();
  public Sibling sib = new Sibling();


  @Override
  public void robotInit() {
    talonFXLeft.setInverted(true);
    talonFXRight.setInverted(false);
  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void autonomousInit() {
    timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    if(timer.get() < 5)
    {
      move(1,1);
    }
    else if(timer.get() < 10)
    {
      move(1,-1);
    }
    SmartDashboard.putNumber("theNumberOne", 1);
  }

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {
    move(-xbox.getRawAxis(1) + xbox.getRawAxis(0), -xbox.getRawAxis(1) + -xbox.getRawAxis(0));
    sib.hello=3;
  }
  @Override
  public void disabledInit() {
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

  public void move(double left, double right)
  {
    talonFXLeft.set(ControlMode.PercentOutput, left);
    talonFXRight.set(ControlMode.PercentOutput, right);
  }
}