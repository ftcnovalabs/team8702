package org.ftcTeam.opmodes.registrar1;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.ftcTeam.configurations.Team8702Auto;
import org.ftcTeam.opmodes.AutoStepEncoder;
import org.ftcTeam.opmodes.BeaconHitter;
import org.ftcTeam.opmodes.ColorValue;
import org.ftcTeam.opmodes.RobotAutonomousUtils;
import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.operations.motors.TankDriveToEncoder;
import org.ftcbootstrap.components.operations.motors.TankDriveToODS;
import org.ftcbootstrap.components.utils.DriveDirection;

/**
 * Note: This Exercise assumes that you have used your Robot Controller App to "scan" your hardware and
 * saved the configuration named: "DemoBot" and creating a class by the same name: {@link Team8702Auto}.
 * <p/>
 * Note:  It is assumed that the proper registry is used for this set of demos. To confirm please
 * search for "Enter your custom registry here"  in  {@link org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;}
 * <p/>
 * Summary:
 * <p/>
 * Opmode demonstrates running a motor from and encoder
 */

@Autonomous
public class AutoODEncoder extends ActiveOpMode {

    private Team8702Auto robot;
    private TankDriveToODS tankDriveToODS;
    private TankDriveToEncoder tankDriveToEncoder;
    private int majorStep;
    ColorValue rainbowValue;

    BeaconHitter firstBeacon;
    BeaconHitter secondBeacon;
    boolean targetReached = false;

    /**
     * Implement this method to define the code to run when the Init button is pressed on the Driver station.
     */
    @Override
    protected void onInit() {

        //specify configuration name save from scan operation
        robot = Team8702Auto.newConfig(hardwareMap, getTelemetryUtil());
        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();
        tankDriveToEncoder = new TankDriveToEncoder(this, robot.motorL, robot.motorR);
        tankDriveToODS = new TankDriveToODS( this, robot.ods, robot.motorL, robot.motorR );
        getTelemetryUtil().sendTelemetry();
        //firstBeacon = new BeaconHitter(getTelemetryUtil(), rainbowValue);
        //secondBeacon = new BeaconHitter(getTelemetryUtil(), rainbowValue);



    }

    @Override
    protected void onStart() throws InterruptedException  {
        super.onStart();
        tankDriveToODS.setName("driving to white line");
        majorStep = 1;

    }

    /**
     * Implement this method to define the code to run when the Start button is pressed on the Driver station.
     * This method will be called on each hardware cycle just as the loop() method is called for event based Opmodes
     *  @throws InterruptedException
     */
    @Override
    protected void activeLoop() throws InterruptedException {
        getTelemetryUtil().addData("Current Major Step: ", majorStep);


        //send any telemetry that may have been added in the above operations
        switch(majorStep) {

            case 1: // Go straight towards the beacon
                targetReached = false;
                boolean reachedDestination = false;
                getTelemetryUtil().addData("Current Major Step: ", majorStep);

               // while(!reachedDestination && !targetReached) {

                    reachedDestination =  tankDriveToODS.runToTarget(0.2, 0.5, DriveDirection.DRIVE_FORWARD);
                    targetReached  = tankDriveToEncoder.runToTarget(0.2, 2000 ,
                            DriveDirection.DRIVE_FORWARD,DcMotor.RunMode.RUN_TO_POSITION);
                //}
                if( reachedDestination || targetReached) {
                    majorStep++;
                    getTelemetryUtil().addData("Current Major Step: ", majorStep);
                    getTelemetryUtil().sendTelemetry();
                    //RobotAutonomousUtils.pauseMotor(robot.motorR, robot.motorL);
                }
                break;
            case 2: //Turn left towards the beacon
                targetReached = false;
                getTelemetryUtil().addData("Current Major Step: ", majorStep);
                while(!targetReached) {
                    targetReached  = tankDriveToEncoder.runToTarget(0.2, AutoStepEncoder.NINTY_ANGLE_TURN_VALUE ,
                            DriveDirection.SPIN_LEFT,DcMotor.RunMode.RUN_USING_ENCODER);
                }
                RobotAutonomousUtils.pauseMotor(robot.motorR, robot.motorL);
                getTelemetryUtil().sendTelemetry();
                majorStep++;

                break;
            case 3: //Go straight to in front of the beacon
                targetReached = false;
                double power = 0.1;
                //brightness assumes fixed distance from the target
                //i.e. line follow or stop on white line
                double targetBrightness = 0.5;
                double targetTime = 5;  //seconds
                getTelemetryUtil().addData("Current Major Step: ", majorStep);
                while(!targetReached) {
                    if (tankDriveToODS.lineFollowForTime( power, targetBrightness, targetTime, DriveDirection.PIVOT_FORWARD_RIGHT)) {
                 //       step++;
                    }
                }
                //majorStep++;
                majorStep = 99;
                break;
            case 4: // hit first beacon

                majorStep++;
                break;
            case 5:
                //Backitup


                break;
            case 6:
                //turn to second beecon

                break;
            case 7:
                //go to it
                targetReached = false;

                majorStep++;
                break;
            case 8:
                //turn to IT 2

                majorStep++;
                break;
            case 9:
                // go to it
                targetReached = false;

                majorStep++;
                break;
            case 10:
                /**
                while(!done) {
                    done = secondBeacon.beaconHitter(colorSensorComponent, motorToEncoderR, motorToEncoderL, ColorValue.RED);
                 }
                 **/
                majorStep = 99;
                break;
            case 99:
                getTelemetryUtil().addData("Running: ", "99: ");
                robot.motorR.setPower(0);
                robot.motorL.setPower(0);
                break;
        }
        getTelemetryUtil().sendTelemetry();
    }
}
