package nl.tue.demothermostat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.Switch;
import org.thermostatapp.util.WeekProgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleSetting extends Activity {

    EditText setDay, startTime;
    Button postSwitch;
    String day, start, type;
    RadioButton dayOn, nightOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_setting);

        HeatingSystem.BASE_ADDRESS = "http://wwwis.win.tue.nl/2id40-ws/3";
        HeatingSystem.WEEK_PROGRAM_ADDRESS = HeatingSystem.BASE_ADDRESS + "/weekProgram";

        setDay= (EditText) findViewById(R.id.setday);
        startTime = (EditText) findViewById(R.id.starttime);
        dayOn = (RadioButton) findViewById(R.id.radiobuttonday);
        nightOn = (RadioButton) findViewById(R.id.radiobuttonnight);


        postSwitch = (Button)findViewById(R.id.postswitch);


         postSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread(new Runnable() {
                    @Override

                    public void run() {
                        try {
                            day = setDay.getText().toString();
                            start = startTime.getText().toString();
                            WeekProgram wpg = HeatingSystem.getWeekProgram();
                            boolean isDay = dayOn.isChecked();
                            boolean isNight = nightOn.isChecked();


                            if (isDay) {
                                for (int i = 5; i <= 9; i++) {
                                    if(!wpg.data.get(day).get(i).getState()) {
                                        wpg.data.get(day).set(i, new Switch(type, true, start));
                                        break;
                                    }
                                }
                            } else if (isNight) {
                                for (int i = 0; i <= 4; i++) {
                                    if(!wpg.data.get(day).get(i).getState()) {
                                        wpg.data.get(day).set(i, new Switch(type, true, start));
                                        break;
                                    }
                                }
                            } else {
                                View b = findViewById(R.id.setday);
                                b.setVisibility(View.INVISIBLE);
                            }



                            HeatingSystem.setWeekProgram(wpg);

                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();

            }
        });

    }
}
