/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;


import java.util.Arrays;
import java.util.List;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Limelight {

    private NetworkTable limelightTable;
    private NetworkTableEntry ledMode, pipeline, camMode, stream;

    public Limelight() {
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        ledMode = limelightTable.getEntry("ledMode");
        pipeline = limelightTable.getEntry("pipeline");
        camMode = limelightTable.getEntry("camMode");
        stream = limelightTable.getEntry("stream");
        pipeline.setNumber(3);
    }

    public double getHorizontalOffset() {
        return limelightTable.getEntry("tx").getDouble(0);
    }

    public double getTargetArea() {
        return limelightTable.getEntry("ta").getDouble(0);
    }

    public boolean isTargetVisible() {
        return limelightTable.getEntry("tv").getBoolean(false);
    }

    public void ledsOn(boolean on){
        if(ledMode.getDouble(1) != 0 && on) {
            ledMode.setNumber(0);
        } else if(ledMode.getDouble(0) != 1 && !on) {
            ledMode.setNumber(1);
        }
    }

    public void setVisionMode() {
        camMode.setNumber(0);     
    }

    public void setDriverMode() {
        camMode.setNumber(1);
    }
}
