import josx.platform.rcx.Motor;
import josx.platform.rcx.Sensor;
import josx.platform.rcx.SensorListener;
import josx.platform.rcx.Sound;

public class Bumper implements SensorListener {
    private static final int SENSOR_S1_ID = Sensor.S1.getId();
    private static final int SENSOR_S3_ID = Sensor.S3.getId();

    private static final int HIT = 1;
    private static final int TURN_TIME = 500;

    public void stateChanged(Sensor sensor, int oldValue, int newValue) {
        if (sensor.getId() == SENSOR_S1_ID && newValue == HIT) {
            Motor.A.setPower(Constanten.MIN_POWER);
            Motor.A.backward();
            Motor.C.setPower(Constanten.MIN_POWER);
            Motor.C.backward();
            try {
                Thread.sleep(TURN_TIME);
            } catch (InterruptedException e) {
                Sound.buzz();
            }
            Motor.A.flt();
            try {
                Thread.sleep(TURN_TIME);
            } catch (InterruptedException e) {
                Sound.buzz();
            }
            Motor.A.forward();
            Motor.C.forward();
        } else if (sensor.getId() == SENSOR_S3_ID && newValue == HIT) {
            Motor.A.setPower(Constanten.MIN_POWER);
            Motor.A.forward();
            Motor.C.setPower(Constanten.MIN_POWER);
            Motor.C.forward();
            try {
                Thread.sleep(TURN_TIME);
            } catch (InterruptedException e) {
                Sound.buzz();
            }
            Motor.A.flt();
            try {
                Thread.sleep(TURN_TIME);
            } catch (InterruptedException e) {
                Sound.buzz();
            }
            Motor.A.backward();
            Motor.C.backward();
        }
    }
}