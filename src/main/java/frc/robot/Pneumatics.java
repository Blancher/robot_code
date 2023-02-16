// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

/** Add your docs here. */
public class Pneumatics {
    private Solenoid bensEvenNicer;
    
    public Pneumatics() {
        bensEvenNicer = new Solenoid(2, PneumaticsModuleType.REVPH, 15);
    }

    public void toggle() {
        bensEvenNicer.toggle();
    }

    public void extend() {
        bensEvenNicer.set(true);
    }

    public void retract() {
        bensEvenNicer.set(false);
    }
}