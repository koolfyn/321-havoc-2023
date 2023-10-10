/* (C) Robolancers 2024 */
package org.robolancers321;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import org.robolancers321.subsystems.arm.InverseArmKinematics;
import org.robolancers321.util.*;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static final class Arm {
    public static final class Anchor {
      public static final int kAnchorPort = 0;
      public static final int kAnchorEncoderPort = 0;

      public static final boolean kInverted = false;
      public static final double kAnchorLength = 0;
      public static final double kZeroPosition = 0;
      public static final double kMinAngle = Double.NEGATIVE_INFINITY;
      public static final double kMaxAngle = Double.POSITIVE_INFINITY;
      public static final boolean kEnableSoftLimit = false;
      public static final double kMaxOutput = 0.5; // going up
      public static final double kMinOutput = -0.4; // going down
      public static final int kCurrentLimit = 50; // 40 to 60

      public static final double kTolerance = 2.0; // error within 2 degrees

      public static final class PID {
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final int kSlot = 0;
      }

      public static final class FF {
        // change to final when done tuning
        public static double kS = 0;
        public static double kG = 0; // gravity FF most likely only tune this gain
        public static final double kV = 0;
        public static final double kA = 0;
        public static final ArmFeedforward ANCHOR_FEEDFORWARD = new ArmFeedforward(kS, kG, kV, kA);
      }

      public static final class MP {
        public static final double maxVel = 2.0;
        public static final double maxAccel = 1.0;
        public static final TrapezoidProfile.Constraints ANCHOR_CONSTRAINTS =
            new TrapezoidProfile.Constraints(maxVel, maxAccel);
      }

      public static final class Conversions {
        /*
        velocity - motorRot/s
        motorRot/s * mechRot/motorRot * meters/mechRot = meters/s
        */

        public static final double kGearRatio = 64; // mechRot/motorRot. TODO check if correct or 1
        public static final double kGearRadius = 0; // m
        public static final double kDistPerRot = kGearRatio * (2 * kGearRadius * Math.PI);

        /*
        Position - motorRot
        motorRot * mechRot/motorRot * deg/mechRot = deg
         */

        public static final double kDegPerRot = kGearRatio * 360;
      }
    }

    public static final class Floating {
      public static final int kFloatingPort = 0;
      public static final int kFloatingEncoderPort = 0;

      public static final boolean kInverted = false;
      public static final double kFloatingLength = 0;
      public static final double kZeroPosition = 0;
      public static final double kMinAngle = Double.NEGATIVE_INFINITY;
      public static final double kMaxAngle = Double.POSITIVE_INFINITY;
      public static final boolean kEnableSoftLimit = false;
      public static final double kMaxOutput = 0.5; // going up
      public static final double kMinOutput = -0.5; // going down
      public static final int kCurrentLimit = 50; // 40 to 60

      public static final double kTolerance = 2.0; // error within 2 degrees

      public static final class PID {
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final int kSlot = 0;
      }

      public static final class FF {
        // change to final when done tuning
        public static double kS = 0;
        public static double kG = 0; // gravity FF most likely only tune this gain
        public static final double kV = 0;
        public static final double kA = 0;
        public static final ArmFeedforward FLOATING_FEEDFORWARD =
            new ArmFeedforward(kS, kG, kV, kA);
      }

      public static final class MP {
        public static final double maxVel = 1.0;
        public static final double maxAccel = 1.0;
        public static final TrapezoidProfile.Constraints FLOATING_CONSTRAINTS =
            new TrapezoidProfile.Constraints(maxVel, maxAccel);
      }

      public static final class Conversions {
        public static final double kGearRatio = 25; // mechRot/motorRot. TODO check if correct or 1
        public static final double kGearRadius = 0; // m
        public static final double kDistPerRot = kGearRatio * (2 * kGearRadius * Math.PI);
        public static final double kDegPerRot = kGearRatio * 360;
      }
    }

    public enum ArmSetpoints {
      TEST(0, 0);

      public double anchor;
      public double floating;

      ArmSetpoints(double y, double z) {
        InverseArmKinematics.Output angles = InverseArmKinematics.calculate(y, z);

        this.anchor = angles.anchor;
        this.floating = angles.floating;
      }
    }
  }
}