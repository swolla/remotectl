import josx.platform.rcx.Motor;
import josx.platform.rcx.Sensor;
import josx.platform.rcx.Sound;
import josx.platform.rcx.remotecontrol.RemoteControlListener;

public class Afstandsdbediening implements RemoteControlListener {
    public void message1Pressed() {
        Motor.A.setPower(Constanten.MIN_POWER);
        Motor.A.stop();
    }

    public void message2Pressed() {
        Motor.A.setPower(Constanten.MIN_POWER);
        Motor.A.stop();
        Motor.C.setPower(Constanten.MIN_POWER);
        Motor.C.stop();
    }

    public void message3Pressed() {
        Motor.C.setPower(Constanten.MIN_POWER);
        Motor.C.stop();
    }

    public void program1Pressed() {
        Sound.playTone(Muziek.A2,50);
        Muziek.speelHoger(Muziek.A2, Muziek.GROTE_SECUNDE, 50);
        Muziek.speelHoger(Muziek.A2, Muziek.KLEINE_TERTS, 50);
        Sound.playTone(Muziek.D3,50);
        Sound.playTone(Muziek.E3,50);
        Muziek.speelLager(Muziek.A3, Muziek.GROTE_TERTS, 50);
        Muziek.speelLager(Muziek.A3, Muziek.KLEINE_SECUNDE, 50);
        Sound.playTone(Muziek.A3, 50);
        Muziek.speelHoger(Muziek.A3, Muziek.GROTE_SECUNDE, 50);
        Muziek.speelHoger(Muziek.A3, Muziek.KLEINE_TERTS, 50);
        Sound.playTone(Muziek.D4,50);
        Sound.playTone(Muziek.E4,50);
        Muziek.speelLager(Muziek.A4, Muziek.GROTE_TERTS, 50);
        Muziek.speelLager(Muziek.A4, Muziek.KLEINE_SECUNDE, 50);
        Sound.playTone(Muziek.A4, 50);
    }

    public void program2Pressed() {
        Sound.playTone(256,50); // C
        Sound.playTone(288,50); // D (+TOON)
        Sound.playTone(320,50); // E (+toon)
        Sound.playTone(341,50); // F (+half, te laag)
        Sound.playTone(360,50); // F# (zuiver)
        Sound.playTone(384,50); // G (C+kwint)
        Sound.playTone(432,50); // A (+TOON)
        Sound.playTone(480,50); // B (+toon)
        Sound.playTone(512,50); // C (+half)
    }

    public void program3Pressed() {
        Sound.twoBeeps();
    }

    public void program4Pressed() {
        Sound.systemSound(true, 3);
    }

    public void program5Pressed() {
        Sound.systemSound(true, 5);
    }

    public void motorDownPressed(Motor motor) {
        if (motor == Motor.A) {
            motorSlower(motor);
        } else if (motor == Motor.B) {
            motorSlower(Motor.A);
            motorSlower(Motor.C);
        } else {
            motorSlower(motor);
        }
    }

    public void motorUpPressed(Motor motor) {
        if (motor == Motor.A || motor == Motor.B) {
            motorFaster(Motor.A);
        }
        if (motor == Motor.C || motor == Motor.B) {
            motorFaster(Motor.C);
        }
    }

    public void motorSlower(Motor motor) {
        Sound.beep();
        int currentPower = motor.getPower();
        if (motor.isForward()) {
            if (currentPower == Constanten.MIN_POWER) {
                motor.flt();
            } else {
                motor.setPower(currentPower - 1);
            }
        } else if (motor.isBackward() && currentPower < Constanten.MAX_POWER) {
            motor.setPower(currentPower + 1);
        } else {
            motor.backward();
        }
    }

    public void motorFaster(Motor motor) {
        Sound.beep();
        int currentPower = motor.getPower();
        if (motor.isBackward()) {
            if (currentPower == Constanten.MIN_POWER) {
                motor.flt();
            } else {
                motor.setPower(currentPower - 1);
            }
        } else if (motor.isForward() && currentPower < Constanten.MAX_POWER) {
            motor.setPower(currentPower + 1);
        } else {
            motor.forward();
        }
    }

    public void soundPressed() {
        Sound.beepSequence();
    }

    public void stopPressed() {
        Sound.buzz();
        Motor.A.flt();
        Motor.A.setPower(Constanten.MIN_POWER);
        Motor.B.flt();
        Motor.B.setPower(Constanten.MIN_POWER);
        Motor.C.flt();
        Motor.C.setPower(Constanten.MIN_POWER);
        Sensor.S1.passivate();
        Sensor.S2.passivate();
        Sensor.S3.passivate();
    }
}
