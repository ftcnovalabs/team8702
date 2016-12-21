package org.ftcTeam.opmodes.registrar1;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.ftcTeam.configurations.Team8702Prod;
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
public class AutoBLUE extends ActiveOpMode {

    private Team8702Prod robot;

    private MotorToEncoder motorToEncoderR;
    private MotorToEncoder motorToEncoderL;
    private int step;
    public ColorSensorComponent colorSensorComponent;
    boolean targetReached = false;
    ColorValue rainbowValue;



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


    }

    @Override
    protected void onStart() throws InterruptedException  {
        super.onStart();
        step = 1;
    }

    /**
     * Implement this method to define the code to run when the Start button is pressed on the Driver station.
     * This method will be called on each hardware cycle just as the loop() method is called for event based Opmodes
     *  @throws InterruptedException
     */
    @Override
    protected void activeLoop() throws InterruptedException {
        getTelemetryUtil().addData("step: " + step, "current");

        //send any telemetry that may have been added in the above operations
        getTelemetryUtil().sendTelemetry();
        if (rainbowValue != null) {
            getTelemetryUtil().addData("Color", rainbowValue.toString());
        }
        switch(step){
            case 1:
                rainbowValue = getColor();
                step++;
                break;
            case 2:
                if (rainbowValue == ColorValue.BLUE) {
                    targetReached = motorToEncoderR.runToTarget(0.1, 350,
                            MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                    getTelemetryUtil().addData("R Current Position 1: ", motorToEncoderR.motorCurrentPosition());
                } else if(rainbowValue == ColorValue.RED ) {
                    targetReached = motorToEncoderL.runToTarget(0.1, 350,
                        MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                    getTelemetryUtil().addData("L Current Position: ", motorToEncoderL.motorCurrentPosition());
                } else{
                //nada
                    step = 99;
                }
                if(targetReached) {
                    step++;
                }

                break;
            case 3:
                //backward
                if (rainbowValue == ColorValue.BLUE) {
                    targetReached = motorToEncoderR.runToTarget(-0.1, 350,
                            MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                    getTelemetryUtil().addData("R Current Position 1: ", motorToEncoderR.motorCurrentPosition());
                } else if(rainbowValue == ColorValue.RED ) {
                    targetReached = motorToEncoderL.runToTarget(-0.1, 350,
                            MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                    getTelemetryUtil().addData("L Current Position: ", motorToEncoderL.motorCurrentPosition());
                } else{
                    //nada
                    step = 99;
                }
                if(targetReached) {
                    step = 99;
                }
                break;
            case 99:
                setOperationsCompleted();
                break;

        }

    }


    public ColorValue getColor() {
        int Red = colorSensorComponent.getR();
        int Blue = colorSensorComponent.getB();
        int Green = colorSensorComponent.getG();

        if (colorSensorComponent.isBlue(Red, Blue, Green)) {
            return ColorValue.BLUE;

        } else if (colorSensorComponent.isRed(Red, Blue, Green)) {
            return ColorValue.RED;
        }
        return ColorValue.ZILCH;

    }
}


