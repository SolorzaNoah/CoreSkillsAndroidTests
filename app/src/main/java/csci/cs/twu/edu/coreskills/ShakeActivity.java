package csci.cs.twu.edu.coreskills;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**

 Assignment Notes: For this activity, every time you shake the device, the counter
 on the screen should go up.  Note that there is no specific "shake listener,"
 unless you build your own.  I chose to add a SensorEventListener to this Activity
 to listen for accelerometer movements.  You will have to determine what's hard enough
 a movement to warrant a "shake."



 */

public class ShakeActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    private Sensor mAccelerometer;
    private static final float SHAKE_THRESHOLD_GRAVITY = 1.25F;

    private int mShakeCount = 0;
    TextView shakeCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        shakeCountTextView = findViewById(R.id.shakeCountTextView);
        // Add code to intialize the sensorManager and accelerometer
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onResume() {
        super.onResume();
        // Add a line to register the Session Manager Listener
        sensorManager.registerListener(this,mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        // Add a line to unregister the Sensor Manager

        super.onPause();
        sensorManager.unregisterListener(this,mAccelerometer);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Nothing needs to be added here.

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        double acceleration = Math.sqrt(Math.pow(x, 2) +
                Math.pow(y, 2) +
                Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
        System.out.println("accelration is " + acceleration);
        if (acceleration > SHAKE_THRESHOLD_GRAVITY) {
            mShakeCount = mShakeCount + 1;
            shakeCountTextView.setText(String.valueOf(mShakeCount));
        }


    }
}