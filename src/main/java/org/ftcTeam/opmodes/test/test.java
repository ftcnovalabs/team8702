package org.ftcTeam.opmodes.test;

import org.ftcTeam.opmodes.production.GamePadDriveOpMode;
import org.ftcbootstrap.BootstrapRegistrar;


/**
 * Register Op Modes
 */
public class test extends BootstrapRegistrar {


  protected Class[] getOpmodeClasses() {
    Class[] classes = {
            AutoBlueLauren.class,
            GamePadDriveOpMode.class,
            AutoODEncoder.class
    };

    return classes;

  }
}
