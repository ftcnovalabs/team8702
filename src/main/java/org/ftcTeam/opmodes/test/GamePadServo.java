package org.ftcTeam.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.ftcTeam.configurations.Team8702ServoRobot;
import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.operations.motors.GamePadTankDrive;

/**
 * Note:  It is assumed that the proper registry is used for this set of demos. To confirm please
 * search for "Enter your custom registry here"  in  {@link org.ftcTeam.FTCTeamControllerActivity}
 * <p/>
 * Summary:  Use an Operation class to perform a tank drive using the gamepad joysticks.
 * See: {@link GamePadTankDrive}
 */

@Autonomous
public class GamePadServo extends ActiveOpMode {

    private Team8702ServoRobot robot;
    private org.ftcbootstrap.components.operations.servos.GamePadServo servo1;

    /**
     * Implement this method to define the code to run when the Init button is pressed on the Driver station.
     */

    @Override
    protected void onStart() throws InterruptedException {
        super.onStart();
        double initialPosition = 0.0;
        servo1 = new org.ftcbootstrap.components.operations.servos.GamePadServo(this, gamepad1, robot.servo1, org.ftcbootstrap.components.operations.servos.GamePadServo.Control.Y_A, initialPosition);
    }

    @Override
    protected void onInit() {

        robot = Team8702ServoRobot.newConfig(hardwareMap, getTelemetryUtil());

        //Note The Telemetry Utility is designed to let you organize all telemetry data before sending it to
        //the Driver station via the sendTelemetry command
        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();

    }

    @Override
    protected void activeLoop() throws InterruptedException {

        //update the motors with the gamepad joystick values
        servo1.update();
        //send any telemetry that may have been added in the above operations
        getTelemetryUtil().sendTelemetry();

    }

}
