package org.ftcTeam.opmodes.registrar1;

import org.ftcTeam.opmodes.*;
import org.ftcTeam.opmodes.registrar1.GamePadDriveOpMode;
import org.ftcbootstrap.BootstrapRegistrar;
import org.ftcbootstrap.demos.TelemetryTest;
import org.ftcbootstrap.demos.demobot.opmodes.*;
import org.ftcbootstrap.demos.navbot.opmodes.EncoderMotorTest;


/**
 * Register Op Modes
 */
public class Registrar1 extends BootstrapRegistrar {


  protected Class[] getOpmodeClasses() {
    Class[] classes = {

            GamePadDriveOpMode.class,
            TelemetryTest.class,
            org.ftcTeam.opmodes.EncoderMotorOpMode.class



    };

    return classes;

  }
}
