package org.ftcTeam.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.ftcbootstrap.components.operations.motors.MotorToEncoder;

/**
 * Created by dkim on 1/11/17.
 */

public class RobotAutonomousUtils {

    public static void pauseMotor(MotorToEncoder motorR, MotorToEncoder motorL) {
        try {
            motorR.stop();
            motorL.stop();
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
