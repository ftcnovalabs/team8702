package org.ftcTeam.opmodes.registrar1;

import org.ftcbootstrap.BootstrapRegistrar;


/**
 * Register Op Modes
 */
public class Registrar1 extends BootstrapRegistrar {


  protected Class[] getOpmodeClasses() {
    Class[] classes = {
            AutoBlueLauren.class,
            GamePadDriveOpMode.class
    };

    return classes;

  }
}
