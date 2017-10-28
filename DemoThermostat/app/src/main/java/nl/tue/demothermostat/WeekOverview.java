package nl.tue.demothermostat;

import android.app.Activity;
import android.os.Bundle;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.WeekProgram;


public class WeekOverview extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_overview);

 /*       new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    dayTempString = "";
                    nightTempString = HeatingSystem.get("nightTemperature");
                    // Get the week program
                    WeekProgram wpg = HeatingSystem.getWeekProgram();
                    // Set the week program to default
                    wpg.setDefault();
                    //Upload the updated program
                    HeatingSystem.setWeekProgram(wpg);
                    dayTempText.post(new Runnable() {

                        @Override
                        public void run() {

                            dayTempText.setText(dayTempString + " \u2103");
                            nightTempText.setText(nightTempString + " \u2103");
                            temp.setText(dayTempString+ " \u2103");

                        }
                    });
                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();
*/
    }
}