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

public class ThermostatActivity extends Activity {

    int vtemp = 22;
    TextView temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);


        ImageView bPlus = (ImageView)findViewById(R.id.overrideup);
        bPlus.setImageResource(R.drawable.doubleup);


        ImageView bMinus = (ImageView)findViewById(R.id.overridedown);
        temp = (TextView)findViewById(R.id.tempoverride);
        temp.setText(vtemp + " \u2103");
        Button weekOverview = (Button)findViewById(R.id.week_overview);

        weekOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WeekOverview.class);
                startActivity(intent);
            }
        });

        Button testingWS = (Button)findViewById(R.id.testing_ws);

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
                temp.setText(vtemp + " \u2103");
            }
        });
        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vtemp--;
                temp.setText(vtemp + " \u2103");
            }
        });
    }

    protected int getVtemp(){
        return vtemp;
    }
}
