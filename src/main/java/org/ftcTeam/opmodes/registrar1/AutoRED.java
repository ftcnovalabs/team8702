package org.ftcTeam.opmodes.registrar1;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.ftcTeam.configurations.Team8702Prod;
import org.ftcTeam.opmodes.BeaconHitter;
import org.ftcTeam.opmodes.ColorUtils;
import org.ftcTeam.opmodes.ColorValue;
import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.ColorSensorComponent;
import org.ftcbootstrap.components.operations.motors.GamePadTankDrive;
import org.ftcbootstrap.components.operations.motors.MotorToEncoder;
import org.ftcbootstrap.components.utils.MotorDirection;


/**
 * Note: This Exercise assumes that you have used your Robot Controller App to "scan" your hardware and
 * saved the configuration named: "DemoBot" and creating a class by the same name: {@link Team8702Prod}.
 * <p/>
 * Note:  It is assumed that the proper registry is used for this set of demos. To confirm please
 * search for "Enter your custom registry here"  in  {@link org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;}
 * <p/>
 * Summary:
 * <p/>
 * Opmode demonstrates running a motor from and encoder
 */


@Autonomous
public class AutoRED extends ActiveOpMode {

    private Team8702Prod robot;

    private MotorToEncoder motorToEncoderR;
    private MotorToEncoder motorToEncoderL;
    private int step;
    private int majorStep;
    private GamePadTankDrive gamePadTankDrive;
    String blue = "blue";
    String red = "red";
    String green = "green";
    public ColorSensorComponent colorSensorComponent;
    ColorValue rainbowValue;
    boolean targetReached = false;
    BeaconHitter firstBeacon;
    BeaconHitter secondBeacon;


    /**
     * Implement this method to define the code to run when the Init button is pressed on the Driver station.
     */
    @Override
    protected void onInit() {

        //specify configuration name save from scan operation
        robot = Team8702Prod.newConfig(hardwareMap, getTelemetryUtil());
        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();
        colorSensorComponent = new ColorSensorComponent(this, robot.mrColor1, ColorSensorComponent.ColorSensorDevice.MODERN_ROBOTICS_I2C);
        colorSensorComponent.enableLed(false);
        motorToEncoderR = new MotorToEncoder(this, robot.motorR);
        motorToEncoderL = new MotorToEncoder(this, robot.motorL);
        motorToEncoderR.setName("motorR");
        motorToEncoderL.setName("motorL");
        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();
        firstBeacon = new BeaconHitter();
        secondBeacon = new BeaconHitter();



    }

    @Override
    protected void onStart() throws InterruptedException  {
        super.onStart();
        step = 1;
        majorStep = 1;

    }

    /**
     * Implement this method to define the code to run when the Start button is pressed on the Driver station.
     * This method will be called on each hardware cycle just as the loop() method is called for event based Opmodes
     *  @throws InterruptedException
     */
    @Override
    protected void activeLoop() throws InterruptedException {
        getTelemetryUtil().addData("step: " + step, "current");
        boolean targetReached = false;
        boolean done = false;

        getTelemetryUtil().addData("red", Integer.toString(robot.mrColor1.red()));
        getTelemetryUtil().addData("blue", Integer.toString(robot.mrColor1.blue()));
        getTelemetryUtil().addData("green", Integer.toString(robot.mrColor1.green()));
        getTelemetryUtil().addData("clear", Integer.toString(robot.mrColor1.alpha()));
        //send any telemetry that may have been added in the above operations
        getTelemetryUtil().sendTelemetry();
        getTelemetryUtil().addData("Color", ColorUtils.getColor(colorSensorComponent).toString());

        switch(majorStep) {

            case 1:
                // move to first beacon part 1
                majorStep ++;
                break;
            case 2:
                //move to first beacon part 2
                majorStep ++;
                break;
            case 3:
                // hit first beacon
                while(!done) {
                    done = firstBeacon.beaconHitter(colorSensorComponent, motorToEncoderR, motorToEncoderL);
                }
                majorStep ++;

                break;
            case 4:
                //move to second beacon part1
                majorStep ++;
                break;
            case 5:
                //move to second beacon part2
                majorStep ++;
                break;
            case 6:
                //hit second beacon
                while(!done) {
                    done = secondBeacon.beaconHitter(colorSensorComponent, motorToEncoderR, motorToEncoderL);
                }
                majorStep ++;
                break;
            case 7:
                //got station part
                majorStep ++;
                break;
            case 8:
                //go to station part 2
                majorStep ++;
                break;
            case 99:
                setOperationsCompleted();
                break;
        }

    }

}




