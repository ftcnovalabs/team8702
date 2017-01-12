package org.ftcTeam.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by dkim on 1/11/17.
 */

public class RobotAutonomousUtils {

    public static void pauseMotor(DcMotor motorR, DcMotor motorL) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
