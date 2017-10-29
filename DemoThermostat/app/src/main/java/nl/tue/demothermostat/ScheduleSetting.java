package nl.tue.demothermostat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.Switch;
import org.thermostatapp.util.WeekProgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleSetting extends Activity {

    EditText setDay, startTime, removedayText, removetimeText;
    Button postSwitch, removebutton;
    String day, start, removedayString, removetimeString;
    RadioButton dayOn, nightOn;
    TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_setting);

        HeatingSystem.BASE_ADDRESS = "http://wwwis.win.tue.nl/2id40-ws/3";
        HeatingSystem.WEEK_PROGRAM_ADDRESS = HeatingSystem.BASE_ADDRESS + "/weekProgram";

        removedayText = (EditText) findViewById(R.id.removeday);
        removetimeText = (EditText) findViewById(R.id.removetime);
        setDay = (EditText) findViewById(R.id.setday);
        dayOn = (RadioButton) findViewById(R.id.radiobuttonday);
        nightOn = (RadioButton) findViewById(R.id.radiobuttonnight);


        postSwitch = (Button)findViewById(R.id.postswitch);
        removebutton = (Button) findViewById(R.id.removebutton);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        timePicker.setIs24HourView(true);

        postSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread(new Runnable() {
                    @Override

                    public void run() {
                        try {
                            day = setDay.getText().toString();
                            start = (timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute());
                            WeekProgram wpg = HeatingSystem.getWeekProgram();
                            boolean isDay = dayOn.isChecked();
                            boolean isNight = nightOn.isChecked();


                            if (isDay) {
                                for (int i = 5; i <= 9; i++) {
                                    boolean state = wpg.data.get(day).get(i).getState();
                                    if(!state) {

                                        wpg.data.get(day).set(i, new Switch("day", true, start));
                                        break;
                                    }
                                }
                            } else if (isNight) {
                                for (int i = 0; i <= 4; i++) {
                                    boolean state = wpg.data.get(day).get(i).getState();
                                    if(!state) {
                                        wpg.data.get(day).set(i, new Switch("night", true, start));
                                        break;
                                    } else {

                                    }
                                }
                            }



                            HeatingSystem.setWeekProgram(wpg);

                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();

            }
        });

        removebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            WeekProgram wpg = HeatingSystem.getWeekProgram();
                            removedayString = removedayText.getText().toString();
                            removetimeString = removetimeText.getText().toString();
                            for (int i = 0; i <= 9 ;i++ ) {
                                    if (wpg.data.get(removedayString).get(i).getTime().equals(removetimeString)) {
                                        wpg.RemoveSwitch(i, removedayString);
                                        break;
                                    }
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
