package csci.cs.twu.edu.coreskills;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**

 Assignment Notes: Add code here to make the accelerometer data on the screen update
 as the device is rotated.

 https://developer.android.com/guide/topics/sensors/sensors_overview#sensors-coords
 https://blog.mindorks.com/using-android-sensors-android-tutorial




 */

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    private Sensor mAccelerometer;

    TextView xTextView;
    TextView yTextView;
    TextView zTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        xTextView = (TextView)findViewById(R.id.xTextView);
        yTextView = (TextView)findViewById(R.id.yTextView);
        zTextView = (TextView)findViewById(R.id.zTextView);

        // Initialize your sensorManager and listeners here
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
        // Add the following line to unregister the Sensor Manager
        sensorManager.unregisterListener(this,mAccelerometer);
        super.onPause();
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No edits needed here.
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Add code here to update the screen
        xTextView.setText("X "+ String.valueOf(event.values[0]));
        yTextView.setText("Y "+ String.valueOf(event.values[1]));
        zTextView.setText("Z "+ String.valueOf(event.values[2]));
    }
}
