package org.ftcTeam.configurations;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
public class Team8702Autonomous extends RobotConfiguration {
    //51.4 = 1 inch
    //motors
    public DcMotor motorR;
    public DcMotor motorL;
    public int EncoderValue;

    public ColorSensor mrColor1;
    /**
     * Factory method for this class
     *
     * @param hardwareMap
     * @param telemetryUtil
     * @return
     */
    public static Team8702Autonomous newConfig(HardwareMap hardwareMap, TelemetryUtil telemetryUtil) {

        Team8702Autonomous config = new Team8702Autonomous();
        config.init(hardwareMap, telemetryUtil);
        return config;

    }





    public void FORWARD (double inches)
    {
        inches = inches * 51.4;
    }

    public void BACKWARD (double inches)
    {
        inches = inches/51.4;
    }

    public void RIGHT (int degrees)
    {
      degrees = ((degrees/EncoderValue) * (90/1100));
    }

    public void LEFT (double degrees)
    {
        degrees = ((degrees/(EncoderValue*-1)) * (90/1100));
    }

    /**
     * Assign your class instance variables to the saved device names in the hardware map
     *
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
        mrColor1 = (ColorSensor) getHardwareOn("mrColor1", hardwareMap.colorSensor);
        mrColor1.enableLed(true);




    }

}
