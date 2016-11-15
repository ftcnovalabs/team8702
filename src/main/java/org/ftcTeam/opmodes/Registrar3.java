package org.ftcTeam.opmodes;

import org.ftcTeam.opmodes.registrar1.GamePadDriveOpMode;
import org.ftcTeam.opmodes.registrar1.GamePadDriveOpModeServoTest;
import org.ftcbootstrap.BootstrapRegistrar;
import org.ftcbootstrap.demos.TelemetryTest;


/**
 * Register Op Modes
 */
public class Registrar3 extends BootstrapRegistrar {


  protected Class[] getOpmodeClasses() {
    Class[] classes = {

            GamePadDriveOpModeServoTest.class,
            TelemetryTest.class

    };

    return classes;

  }
}
