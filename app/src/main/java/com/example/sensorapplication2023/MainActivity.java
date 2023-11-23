package com.example.sensorapplication2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView temp_output;
    SensorManager sensorManager;
    Sensor tempSensor;
    Boolean isTempAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temp_output = findViewById(R.id.temp_txt);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)!=null){
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            isTempAvailable = true;
        }else{
            temp_output.setText("Temp Sensor us unavailable");
            isTempAvailable= false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
    temp_output.setText(sensorEvent.values[0]+"(N/m2");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        super.onResume();
        if(isTempAvailable)
        {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
      if(isTempAvailable){
          sensorManager.registerListener(this,tempSensor,SensorManager.SENSOR_DELAY_NORMAL);

      }
    }
}