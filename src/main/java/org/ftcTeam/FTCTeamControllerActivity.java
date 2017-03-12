package org.ftcTeam;

import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;



import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.ftcTeam.opmodes.test.test;


public class FTCTeamControllerActivity extends FtcRobotControllerActivity {

  @Override
  protected OpModeRegister createOpModeRegister() {

      return new test();
    // return new Production();
    // return new Prototype();
    // return new test();


  }

}
