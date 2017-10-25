package nl.tue.demothermostat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.WeekProgram;

public class ThermostatActivity extends Activity {

    int vtemp = 22;
    String overriddenTemp, tempCurrent;
    TextView currentTemp;
    TextView temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);


        ImageView bPlus = (ImageView) findViewById(R.id.overrideup);
        bPlus.setImageResource(R.drawable.doubleup);


        ImageView bMinus = (ImageView) findViewById(R.id.overridedown);
        temp = (TextView) findViewById(R.id.tempoverride);
        temp.setText(vtemp + " \u2103");
        Button weekOverview = (Button) findViewById(R.id.week_overview);

        currentTemp = (TextView) findViewById(R.id.currenttemp);



        weekOverview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WeekOverview.class);
                startActivity(intent);

            }
    });




        Button testingWS = (Button) findViewById(R.id.testing_ws);

        testingWS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TestingWS.class);
                startActivity(intent);
            }
        });


        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vtemp++;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            HeatingSystem.put("targetTemperature", Integer.toString(vtemp));
                            overriddenTemp = HeatingSystem.get("targetTemperature");
                            temp.post(new Runnable() {
                                @Override
                                public void run() {
                                    temp.setText(overriddenTemp);
                                }
                            });
                            tempCurrent = HeatingSystem.get("currentTemperature");
                            currentTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    currentTemp.setText(tempCurrent);
                                }
                            });
                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
            }
        });
        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vtemp--;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HeatingSystem.put("targetTemperature", Integer.toString(vtemp));
                            overriddenTemp = HeatingSystem.get("targetTemperature");
                            temp.post(new Runnable() {
                                @Override
                                public void run() {
                                    temp.setText(overriddenTemp);
                                }
                            });
                            tempCurrent = HeatingSystem.get("currentTemperature");
                            currentTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    currentTemp.setText(tempCurrent);
                                }
                            });
                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
            }
        });
    }
}
