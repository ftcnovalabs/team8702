package org.ftcTeam.opmodes.registrar1;

import org.ftcbootstrap.BootstrapRegistrar;
import org.ftcbootstrap.demos.TelemetryTest;


/**
 * Register Op Modes
 */
public class Registrar1 extends BootstrapRegistrar {


  protected Class[] getOpmodeClasses() {
    Class[] classes = {
            GamePadDriveOpMode.class,
            TelemetryTest.class,
            GamePadDriveOpModeServoTest.class,
            EncoderMotorOpMode.class

    };

    return classes;

  }
}
