package org.ftcTeam.configurations;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.ftcbootstrap.RobotConfiguration;
import org.ftcbootstrap.components.utils.TelemetryUtil;

/**
 * FTCTeamRobot Saved Configuration
 * <p/>
 * It is assumed that there is a configuration on the phone running the Robot Controller App with the same name as this class and
 * that  configuration is the one that is marked 'activated' on the phone.
 * It is also assumed that the device names in the 'init()' method below are the same  as the devices named for the
 * saved configuration on the phone.
 */
public class Team8702Test extends RobotConfiguration {

    //motors
    public DcMotor motorR;
    public DcMotor motorL;


    /**
     * Factory method for this class
     *
     * @param hardwareMap
     * @param telemetryUtil
     * @return
     */
    public static Team8702Test newConfig(HardwareMap hardwareMap, TelemetryUtil telemetryUtil) {

        Team8702Test config = new Team8702Test();
        config.init(hardwareMap, telemetryUtil);
        return config;

    }

    /**
     * Assign your class instance variables to the saved device names in the hardware map
     *
     * @param hardwareMap
     * @param telemetryUtil
     */
    @Override
    protected void init(HardwareMap hardwareMap, TelemetryUtil telemetryUtil) {

        setTelemetry(telemetryUtil);

        motorR = (DcMotor) getHardwareOn("motor1", hardwareMap.dcMotor);
        motorL = (DcMotor) getHardwareOn("motor2", hardwareMap.dcMotor);
        motorL.setDirection(DcMotor.Direction.REVERSE);


    }




}
