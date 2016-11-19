package org.ftcTeam;

import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

import org.ftcTeam.opmodes.registrar1.Registrar1;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.ftcTeam.opmodes.registrar2.Registrar2;

public class FTCTeamControllerActivity extends FtcRobotControllerActivity {

  @Override
  protected OpModeRegister createOpModeRegister() {

      //return new Registrar1();
      return new Registrar2();
  }

}
