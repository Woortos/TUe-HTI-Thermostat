package nl.tue.demothermostat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Switch;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.WeekProgram;

public class ThermostatActivity extends Activity {

    double vtemp = 20.0;
    double dayTemp = 22.0;
    double nightTemp = 16.0;
    String targetTempStr, tempCurrent, dayTempString, nightTempString,  vacationModeString;
    TextView currentTemp;
    TextView targetTemp;
    TextView dayTempText;
    TextView nightTempText;
    Switch vacationMode;
    Handler handler = new Handler();
    Runnable refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);

        HeatingSystem.BASE_ADDRESS = "http://wwwis.win.tue.nl/2id40-ws/3";
        HeatingSystem.WEEK_PROGRAM_ADDRESS = HeatingSystem.BASE_ADDRESS + "/weekProgram";

        ImageView bPlus = (ImageView) findViewById(R.id.overrideup);
        ImageView bPlus2 = (ImageView) findViewById(R.id.overrideup2);
        ImageView bMinus = (ImageView) findViewById(R.id.overridedown);
        ImageView bMinus2 = (ImageView) findViewById(R.id.overridedown2);
        ImageView dayPlus = (ImageView) findViewById(R.id.dayup);
        ImageView dayMinus = (ImageView) findViewById(R.id.daydown);
        ImageView nightPlus = (ImageView) findViewById(R.id.nightup);
        ImageView nightMinus = (ImageView) findViewById(R.id.nightdown);

        vacationMode = (Switch) findViewById(R.id.vacationmode);

        currentTemp = (TextView) findViewById(R.id.currenttemp);
        targetTemp = (TextView) findViewById(R.id.tempoverride);
        dayTempText = (TextView) findViewById(R.id.tempday);
        nightTempText = (TextView) findViewById(R.id.tempnight);


        //refresh current temperature
        refresh = new Runnable() {
            public void run() {
                // Do something
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tempCurrent = HeatingSystem.get("currentTemperature");
                            targetTempStr = HeatingSystem.get("targetTemperature");
                            vtemp = Double.parseDouble(targetTempStr);
                            currentTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    currentTemp.setText(tempCurrent + " \u2103");
                                }
                            });
                            targetTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    targetTemp.setText(targetTempStr + " \u2103");
                                }
                            });
                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
                handler.postDelayed(refresh, 500);
            }
        };
        handler.post(refresh);

        //Upon starting the app, retrieve the correct temps/vacationMode from the server
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    dayTempString = HeatingSystem.get("dayTemperature");
                    nightTempString = HeatingSystem.get("nightTemperature");
                    vacationModeString = HeatingSystem.get("weekProgramState");

                    dayTemp = Double.parseDouble(dayTempString);
                    nightTemp = Double.parseDouble(nightTempString);


                    // Get the week program
                   /* WeekProgram wpg = HeatingSystem.getWeekProgram();
                    // Set the week program to default
                    wpg.setDefault();
                    //Upload the updated program
                    HeatingSystem.setWeekProgram(wpg);*/

                    nightTempText.post(new Runnable() {
                        @Override
                        public void run() {
                            nightTempText.setText(nightTempString+ " \u2103");
                        }
                    });
                    dayTempText.post(new Runnable() {
                        @Override
                        public void run() {
                            dayTempText.setText(dayTempString + " \u2103");

                        }
                    });
                    vacationMode.post(new Runnable() {
                        @Override
                        public void run() {
                            if (vacationModeString.equals("on")){
                                vacationMode.setChecked(false);
                            } else {
                                vacationMode.setChecked(true);
                            }
                        }
                    });

                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();


        ImageButton weekOverview = (ImageButton) findViewById(R.id.week_overview);

        weekOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WeekOverview.class);
                startActivity(intent);
            }
        });


        ImageButton bar = (ImageButton) findViewById(R.id.bar);

        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ScheduleSetting.class);
                startActivity(intent);
            }
        });


        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vtemp < 30){
                    View b = findViewById(R.id.overrideup);
                    b.setVisibility(View.VISIBLE);
                    View c = findViewById(R.id.overridedown);
                    c.setVisibility(View.VISIBLE);
                    vtemp++;
                } else {
                    View b = findViewById(R.id.overrideup);
                    b.setVisibility(View.INVISIBLE);
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                                HeatingSystem.put("targetTemperature", Double.toString(vtemp));
                                targetTemp.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        targetTemp.setText(targetTempStr + " \u2103");
                                    }
                                });
                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
            }
        });

        bPlus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vtemp < 30){
                    View b = findViewById(R.id.overrideup);
                    b.setVisibility(View.VISIBLE);
                    View c = findViewById(R.id.overridedown);
                    c.setVisibility(View.VISIBLE);
                    vtemp = vtemp + 0.1;
                } else {
                    View b = findViewById(R.id.overrideup);
                    b.setVisibility(View.INVISIBLE);
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HeatingSystem.put("targetTemperature", Double.toString(vtemp));
                            targetTempStr = HeatingSystem.get("targetTemperature");
                            targetTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    targetTemp.setText(targetTempStr + " \u2103");
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
                if (vtemp > 5){
                    View b = findViewById(R.id.overrideup);
                    b.setVisibility(View.VISIBLE);
                    View c = findViewById(R.id.overridedown);
                    c.setVisibility(View.VISIBLE);
                    vtemp--;
                } else {
                    View b = findViewById(R.id.overridedown);
                    b.setVisibility(View.INVISIBLE);
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HeatingSystem.put("targetTemperature", Double.toString(vtemp));
                            targetTempStr = HeatingSystem.get("targetTemperature");
                            targetTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    targetTemp.setText(targetTempStr + " \u2103");
                                }
                            });
                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
            }
        });

        bMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vtemp > 5){
                    View b = findViewById(R.id.overrideup);
                    b.setVisibility(View.VISIBLE);
                    View c = findViewById(R.id.overridedown);
                    c.setVisibility(View.VISIBLE);
                    vtemp = vtemp - 0.1;
                } else {
                    View b = findViewById(R.id.overridedown);
                    b.setVisibility(View.INVISIBLE);
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HeatingSystem.put("targetTemperature", Double.toString(vtemp));
                            targetTempStr = HeatingSystem.get("targetTemperature");
                            targetTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    targetTemp.setText(targetTempStr + " \u2103");
                                }
                            });
                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
            }
        });

        dayPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayTemp < 30){
                    View b = findViewById(R.id.dayup);
                    b.setVisibility(View.VISIBLE);
                    View c = findViewById(R.id.daydown);
                    c.setVisibility(View.VISIBLE);
                    dayTemp++;
                } else {
                    View b = findViewById(R.id.dayup);
                    b.setVisibility(View.INVISIBLE);
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HeatingSystem.put("dayTemperature", Double.toString(dayTemp));
                            dayTempString = HeatingSystem.get("dayTemperature");
                            dayTempText.post(new Runnable() {
                                @Override
                                public void run() {
                                    dayTempText.setText(dayTempString + " \u2103");
                                }
                            });
                            targetTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    targetTemp.setText(targetTempStr + " \u2103");
                                }
                            });

                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
            }
        });

        dayMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayTemp > 5){
                    View b = findViewById(R.id.dayup);
                    b.setVisibility(View.VISIBLE);
                    View c = findViewById(R.id.daydown);
                    c.setVisibility(View.VISIBLE);
                    dayTemp--;
                } else {
                    View b = findViewById(R.id.daydown);
                    b.setVisibility(View.INVISIBLE);
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HeatingSystem.put("dayTemperature", Double.toString(dayTemp));
                            dayTempString = HeatingSystem.get("dayTemperature");
                            dayTempText.post(new Runnable() {
                                @Override
                                public void run() {
                                    dayTempText.setText(dayTempString + " \u2103");
                                }
                            });
                            targetTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    targetTemp.setText(targetTempStr + " \u2103");
                                }
                            });

                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
            }
        });


        nightPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nightTemp < 30){
                    View b = findViewById(R.id.nightup);
                    b.setVisibility(View.VISIBLE);
                    View c = findViewById(R.id.nightdown);
                    c.setVisibility(View.VISIBLE);
                    nightTemp++;
                } else {
                    View b = findViewById(R.id.nightup);
                    b.setVisibility(View.INVISIBLE);
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HeatingSystem.put("nightTemperature", Double.toString(nightTemp));
                            nightTempString = HeatingSystem.get("nightTemperature");
                            nightTempText.post(new Runnable() {
                                @Override
                                public void run() {
                                    nightTempText.setText(nightTempString + " \u2103");
                                }
                            });
                            targetTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    targetTemp.setText(targetTempStr + " \u2103");
                                }
                            });

                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
            }
        });

        nightMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nightTemp > 5){
                    View b = findViewById(R.id.nightup);
                    b.setVisibility(View.VISIBLE);
                    View c = findViewById(R.id.nightdown);
                    c.setVisibility(View.VISIBLE);
                    nightTemp--;
                } else {
                    View b = findViewById(R.id.nightdown);
                    b.setVisibility(View.INVISIBLE);
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HeatingSystem.put("nightTemperature", Double.toString(nightTemp));
                            nightTempString = HeatingSystem.get("nightTemperature");
                            nightTempText.post(new Runnable() {
                                @Override
                                public void run() {
                                    nightTempText.setText(nightTempString + " \u2103");
                                }
                            });
                            targetTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    targetTemp.setText(targetTempStr + " \u2103");
                                }
                            });

                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
            }
        });

        vacationMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (isChecked){
                            vacationModeString = "off";
                        } else {
                            vacationModeString = "on";
                        }
                        try {
                            HeatingSystem.put("weekProgramState", vacationModeString);
                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
            }
        });

    }


}
