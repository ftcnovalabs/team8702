package org.ftcTeam.opmodes.registrar1;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import org.ftcTeam.configurations.Team8702Auto;
        import org.ftcTeam.opmodes.AutoStepEncoder;
        import org.ftcTeam.opmodes.BeaconHitter;
        import org.ftcTeam.opmodes.ColorValue;
        import org.ftcTeam.opmodes.RobotAutonomousUtils;
        import org.ftcbootstrap.ActiveOpMode;
        import org.ftcbootstrap.components.operations.motors.MotorToEncoder;
        import org.ftcbootstrap.components.utils.MotorDirection;

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
public class AutoRedLauren extends ActiveOpMode {

    private Team8702Auto robot;

    private MotorToEncoder motorToEncoderR;
    private MotorToEncoder motorToEncoderL;
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
        motorToEncoderR = new MotorToEncoder(this, robot.motorR);
        motorToEncoderL = new MotorToEncoder(this, robot.motorL);
        motorToEncoderR.setName("motorR");
        motorToEncoderL.setName("motorL");
        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();
        firstBeacon = new BeaconHitter(getTelemetryUtil(), rainbowValue);
        secondBeacon = new BeaconHitter(getTelemetryUtil(), rainbowValue);



    }

    @Override
    protected void onStart() throws InterruptedException  {
        super.onStart();
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
                getTelemetryUtil().addData("Current Major Step: ", majorStep);

                while(!targetReached) {
                    getTelemetryUtil().addData("Running: ", "Running1: ");

                    motorToEncoderR.runToTarget(0.25, AutoStepEncoder.FIRST_BEACON_APPROACH_VALUE,
                            MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                    targetReached = motorToEncoderL.runToTarget(0.25, AutoStepEncoder.FIRST_BEACON_APPROACH_VALUE,
                            MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                }
                //getTelemetryUtil().addData("Running: ", "Running99: ");
                RobotAutonomousUtils.pauseMotor(robot.motorR, robot.motorL);
                majorStep++;

                break;
            case 2: //Turn left towards the beacon
                targetReached = false;
                getTelemetryUtil().addData("Current Major Step: ", majorStep);
                while(!targetReached) {
                    targetReached = motorToEncoderR.runToTarget(0.1, AutoStepEncoder.NINTY_ANGLE_TURN_VALUE,
                            MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                }
                getTelemetryUtil().addData("Current Encoder Position" , motorToEncoderR.motorCurrentPosition());
                RobotAutonomousUtils.pauseMotor(robot.motorR, robot.motorL);
                getTelemetryUtil().sendTelemetry();
                RobotAutonomousUtils.pauseMotor(robot.motorR, robot.motorL);
                majorStep++;
                break;
            case 3: //Go straight to in front of the beacon
                getTelemetryUtil().addData("Current Major Step: ", majorStep);
                while(!targetReached) {
                    motorToEncoderR.runToTarget(0.25, AutoStepEncoder.APPROACH_BEACON_VALUE,
                            MotorDirection.MOTOR_FORWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                    targetReached = motorToEncoderL.runToTarget(0.25, AutoStepEncoder.APPROACH_BEACON_VALUE,
                            MotorDirection.MOTOR_FORWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                }
                RobotAutonomousUtils.pauseMotor(robot.motorR, robot.motorL);
                majorStep++;
                break;
            case 4: // hit first beacon
               /*
               while(!done) {
                    done = firstBeacon.beaconHitter(colorSensorComponent, motorToEncoderR, motorToEncoderL, ColorValue.RED);
                }
                */
                RobotAutonomousUtils.pauseMotor(robot.motorR, robot.motorL);
                majorStep ++;
                break;
            case 5:
                //Press the beacon
                targetReached = motorToEncoderR.runToTarget(0.25,300,
                        MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                targetReached = motorToEncoderL.runToTarget(0.25, 300,
                        MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                if (targetReached) {
                    majorStep++;
                }
                break;
            case 6:
                targetReached = motorToEncoderL.runToTarget(0.25, 600,
                        MotorDirection.MOTOR_FORWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                if(targetReached){
                    majorStep ++;
                }
                break;
            case 7:
                targetReached = motorToEncoderR.runToTarget(0.25, 750,
                    MotorDirection.MOTOR_FORWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                targetReached = motorToEncoderL.runToTarget(0.25, 750,
                    MotorDirection.MOTOR_FORWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                if (targetReached) {
                    majorStep++;
                }
                break;
            case 8:
                targetReached = motorToEncoderR.runToTarget(0.25,800,
                    MotorDirection.MOTOR_FORWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                if (targetReached) {
                    majorStep++;
                }
                break;
            case 9:
                targetReached = motorToEncoderR.runToTarget(0.25, 500,
                    MotorDirection.MOTOR_FORWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                targetReached = motorToEncoderL.runToTarget(0.25, 500,
                        MotorDirection.MOTOR_FORWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                if (targetReached) {
                    majorStep++;
                }
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
