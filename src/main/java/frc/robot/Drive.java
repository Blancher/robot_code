// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

/** Add your docs here. */
public class Drive {
    private TalonFX leftMotor;
    private CANCoder leftEncoder;
    
    public Drive() {
        leftMotor = new TalonFX(0, "rio");
        leftMotor.configFactoryDefault();
        leftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

        leftEncoder = new CANCoder(1, "rio");
        leftEncoder.configFactoryDefault();
        leftEncoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
        leftEncoder.configAbsoluteSensorRange(AbsoluteSensorRange.Signed_PlusMinus180);
        leftEncoder.configSensorDirection(true);
        leftEncoder.configMagnetOffset(90);
    }
}