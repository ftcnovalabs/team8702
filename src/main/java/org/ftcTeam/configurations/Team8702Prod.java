package org.ftcTeam.configurations;

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
public class Team8702Prod extends RobotConfiguration {
    //51.4 = 1 inch
    //motors
    public DcMotor motorR;
    public DcMotor motorL;
    public DcMotor analogMotor;
    public DcMotor intakeMotor;
    public DcMotor conveyorBeltMotor;


    /**
     * Factory method for this class
     *
     * @param hardwareMap
     * @param telemetryUtil
     * @return
     */
    public static Team8702Prod newConfig(HardwareMap hardwareMap, TelemetryUtil telemetryUtil) {

        Team8702Prod config = new Team8702Prod();
        config.init(hardwareMap, telemetryUtil);
        return config;
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

          motorR = (DcMotor) getHardwareOn("motorR", hardwareMap.dcMotor);
          motorL = (DcMotor) getHardwareOn("motorL", hardwareMap.dcMotor);
          motorL.setDirection(DcMotor.Direction.REVERSE);
          analogMotor = (DcMotor) getHardwareOn("analogStick1", hardwareMap.dcMotor);
          analogMotor.setDirection(DcMotor.Direction.FORWARD);
          intakeMotor = (DcMotor) getHardwareOn("intakeMotor", hardwareMap.dcMotor);
          conveyorBeltMotor = (DcMotor) getHardwareOn("conveyorMotor", hardwareMap.dcMotor);

    }

    public void FORWARD (double inches)
    {
        inches = inches * 51.4;
    }

    public void BACKWARD (double inches)
    {
        inches = inches/51.4;
    }



}
