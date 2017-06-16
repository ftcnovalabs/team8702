package org.ftcTeam.opmodes.production;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.ftcTeam.configurations.Team8702Prod;
import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.operations.motors.GamePadMotor;
import org.ftcbootstrap.components.operations.motors.GamePadTankDrive;
import org.ftcbootstrap.components.operations.servos.GamePadServo;


/**
 * Note:  It is assumed that the proper registry is used for this set of demos. To confirm please
 * search for "Enter your custom registry here"  in  {@link org.ftcTeam.FTCTeamControllerActivity}
 * <p/>
 * Summary:  Use an Operation class to perform a tank drive using the gamepad joysticks.
 * See: {@link GamePadTankDrive}
 */

@TeleOp
public class GamePadDriveOpModeTeletest extends ActiveOpMode {

    private Team8702Prod robot;
    private GamePadTankDrive gamePadTankDrive;
    private GamePadMotor liftGamePad;
    private GamePadServo servoRGamePad;
    private GamePadServo servoLGamePad;

    /**
     * Implement this method to define the code to run when the Init button is pressed on the Driver station.
     */
    @Override
    protected void onInit() {

        robot = Team8702Prod.newConfig(hardwareMap, getTelemetryUtil());

        //Note The Telemetry Utility is designed to let you organize all telemetry data before sending it to
        //the Driver station via the sendTelemetry command
        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();

    }

    @Override
    protected void onStart() throws InterruptedException {
        super.onStart();

                      //create the operation  to perform a tank drive using the gamepad joysticks.
        gamePadTankDrive = new GamePadTankDrive(this, gamepad1, robot.motorR, robot.motorL);
        liftGamePad = new GamePadMotor(this, gamepad2, robot.liftMotor, GamePadMotor.Control.LEFT_STICK_Y);
        servoLGamePad = new GamePadServo(this, gamepad2, robot.leftServo, GamePadServo.Control.Y_A, 0.0);
        servoRGamePad = new GamePadServo(this, gamepad2, robot.rightServo, GamePadServo.Control.X_B, 0.0);

    }

    /**
     * Implement this method to define the code to run when the Start button is pressed on the Driver station.
     * This method will be called on each hardware cycle just as the loop() method is called for event based Opmodes
     *
     * @throws InterruptedException
     */
    @Override
    protected void activeLoop() throws InterruptedException {

        //update the motors with the gamepad joystick values
       gamePadTankDrive.update();
        liftGamePad.update();
        servoRGamePad.update();
        servoLGamePad.update();
        getTelemetryUtil().sendTelemetry();

    }
}
