package nl.tue.demothermostat;

import android.app.Activity;
import android.os.Bundle;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.Switch;
import org.thermostatapp.util.WeekProgram;

import java.util.HashMap;


public class WeekOverview extends Activity {

    WeekProgram wpg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_overview);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Get the week program
                    wpg = HeatingSystem.getWeekProgram();
                    /*for (HashMap<String, String> map : wpg.get())
                        for (Entry<String, String> mapEntry : map.entrySet())
                        {
                            String key = mapEntry.getKey();
                            String value = mapEntry.getValue();
                        }*/
                    System.out.println(wpg.data.get("Monday").get(1));
                    wpg.data.get("Tuesday");
                    wpg.data.get("Wednesday");
                    wpg.data.get("Thursday");
                    wpg.data.get("Friday");
                    wpg.data.get("Saturday");
                    wpg.data.get("Sunday");
                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();

    }
}