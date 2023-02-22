// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

/** Add your docs here. */
public class Drive {
    public TalonFX leftMotor;
    public CANCoder leftEncoder;
    public TalonFX rightMotor;
    public CANCoder rightEncoder;
    
    public Drive() {
        leftMotor = new TalonFX(0, "rio");
        leftMotor.configFactoryDefault(); //sets defaults
        leftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor); //chooses feedback sensor
        leftMotor.setNeutralMode(NeutralMode.Brake); //brake makes motors hard to move, coast makes them easy to move
        leftMotor.configVelocityMeasurementPeriod(SensorVelocityMeasPeriod.Period_100Ms); //gets velocity every 100ms
        leftMotor.configVelocityMeasurementWindow(26); //gets 26 sample every period
        leftMotor.configVoltageCompSaturation(11); //sets max voltage
        leftMotor.enableVoltageCompensation(true); //turns it on
        leftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General,20);
        leftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0,20);
        leftMotor.setInverted(true);

        leftEncoder = new CANCoder(1, "rio");
        leftEncoder.configFactoryDefault(); //sets defaults
        leftEncoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition); //boots to abs position
        leftEncoder.configAbsoluteSensorRange(AbsoluteSensorRange.Signed_PlusMinus180); //does 180 and -180
        leftEncoder.configSensorDirection(true); //reverses motor
        leftEncoder.configMagnetOffset(90); //sets offset

        rightMotor = new TalonFX(0, "rio");
        rightMotor.configFactoryDefault(); //sets defaults
        rightMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor); //chooses feedback sensor
        rightMotor.setNeutralMode(NeutralMode.Brake); //brake makes motors hard to move, coast makes them easy to move
        rightMotor.configVelocityMeasurementPeriod(SensorVelocityMeasPeriod.Period_100Ms); //gets velocity every 100ms
        rightMotor.configVelocityMeasurementWindow(26); //gets 26 sample every period
        rightMotor.configVoltageCompSaturation(11); //sets max voltage
        rightMotor.enableVoltageCompensation(true); //turns it on
        rightMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General,20);
        rightMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0,20);
        leftMotor.setInverted(false);

        rightEncoder = new CANCoder(1, "rio");
        rightEncoder.configFactoryDefault(); //sets defaults
        rightEncoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition); //boots to abs position
        rightEncoder.configAbsoluteSensorRange(AbsoluteSensorRange.Signed_PlusMinus180); //does 180 and -180
        rightEncoder.configSensorDirection(false); //reverses motor
        rightEncoder.configMagnetOffset(90); //sets offset

        setPosition(leftEncoder.getAbsolutePosition(), rightEncoder.getAbsolutePosition());
    }

    public double degreesToNative(double deg, double gearRatio) {
        return (deg * 2048 * gearRatio) / 360;
    }

    public void setPosition(double lDeg, double rDeg) {
        leftMotor.setSelectedSensorPosition(degreesToNative(lDeg, 420.69));
        rightMotor.setSelectedSensorPosition(degreesToNative(rDeg, 420.69));
    }

    public void move(double leftM, double rightM) {
        leftMotor.set(ControlMode.PercentOutput, leftM);
        rightMotor.set(ControlMode.PercentOutput, rightM);
    }
}