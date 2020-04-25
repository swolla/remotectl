import josx.platform.rcx.Button;
import josx.platform.rcx.Sensor;
import josx.platform.rcx.SensorConstants;
import josx.platform.rcx.Sound;
import josx.platform.rcx.remotecontrol.RemoteControlSensor;

public class Robot {
    public static void main(String[] args) {
        Afstandsdbediening rcxActiviteit = new Afstandsdbediening();
        RemoteControlSensor remoteControlSensor = new RemoteControlSensor();
        remoteControlSensor.addRemoteControlListener(rcxActiviteit);

        Bumper bumper = new Bumper();
        Sensor.S1.setTypeAndMode(SensorConstants.SENSOR_TYPE_TOUCH, SensorConstants.SENSOR_MODE_BOOL);
        Sensor.S1.addSensorListener(bumper);
        Sensor.S3.setTypeAndMode(SensorConstants.SENSOR_TYPE_TOUCH, SensorConstants.SENSOR_MODE_BOOL);
        Sensor.S3.addSensorListener(bumper);

        try {
            Button.RUN.waitForPressAndRelease();
        } catch (InterruptedException e) {
            Sound.buzz();
        }
    }
}
