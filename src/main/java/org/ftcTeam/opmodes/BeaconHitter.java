package org.ftcTeam.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.ftcbootstrap.components.ColorSensorComponent;
import org.ftcbootstrap.components.operations.motors.MotorToEncoder;
import org.ftcbootstrap.components.utils.MotorDirection;

/**
 * Created by tylerkim on 12/21/16.
 */

public class BeaconHitter {

     int step = 1;
     ColorValue rainbowValue;


    public boolean beaconHitter(ColorSensorComponent colorSensorComponent, MotorToEncoder motorToEncoderR, MotorToEncoder motorToEncoderL) throws InterruptedException {
        boolean targetReached = false;

        switch(step){
            case 1:
                rainbowValue = ColorUtils.getColor(colorSensorComponent);
                step++;
                break;
            case 2:

                if (rainbowValue == ColorValue.BLUE) {
                    targetReached = motorToEncoderR.runToTarget(0.1, 350,
                            MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                } else if(rainbowValue == ColorValue.RED ) {
                    targetReached = motorToEncoderL.runToTarget(0.1, 350,
                            MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
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
                } else if(rainbowValue == ColorValue.RED ) {
                    targetReached = motorToEncoderL.runToTarget(-0.1, 350,
                            MotorDirection.MOTOR_BACKWARD, DcMotor.RunMode.RUN_USING_ENCODER);
                } else {
                    //nada
                    step = 99;
                }
                if(targetReached) {
                    step = 99;
                }
                break;
            case 99:
                return true;
        }
        return true;

    }
}
